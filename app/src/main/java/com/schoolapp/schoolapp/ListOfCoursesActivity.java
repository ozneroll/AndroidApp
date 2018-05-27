package com.schoolapp.schoolapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Classes.Course;
import Classes.Module;


/**
 * Created by loren on 11.05.2018.
 */

public class ListOfCoursesActivity extends AppCompatActivity {
    private ListView listCourses;
    private MaterialSearchView searchView;
    private List<Course> courseList = new ArrayList<>();
    private ArrayAdapter<Course> adapter;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_courses);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.course));

        initFirebase();
        addEventFirebaseListener();

        listCourses = (ListView) findViewById(R.id.listitem);

        listCourses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Course _temp = (Course) adapterView.getItemAtPosition(i);
                Intent myIntent = new Intent(ListOfCoursesActivity.this,
                        DetailCourseActivity.class);
                myIntent.putExtra("name", _temp.getName());
                myIntent.putExtra("module", _temp.getModule());


                startActivity(myIntent);
            }
        });


        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                searchView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onSearchViewClosed() {

                listCourses = (ListView) findViewById(R.id.listitem);

                ArrayAdapter adapter = new ArrayAdapter(ListOfCoursesActivity.this, android.R.layout.simple_list_item_1, courseList);
                listCourses.setAdapter(adapter);
            }
        });

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText != null && !newText.isEmpty()) {
                    List<Course> lstFound = new ArrayList<Course>();
                    for (Course item : courseList) {
                        if ((item.getName().toLowerCase().contains(newText)))
                            lstFound.add(item);
                    }
                    ArrayAdapter adapter = new ArrayAdapter(ListOfCoursesActivity.this, android.R.layout.simple_list_item_1, lstFound);
                    listCourses.setAdapter(adapter);
                } else {

                    ArrayAdapter adapter = new ArrayAdapter(ListOfCoursesActivity.this, android.R.layout.simple_list_item_1, courseList);
                    listCourses.setAdapter(adapter);

                }
                return true;
            }

        });


    }

    //creating the menu
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_settings, menu);
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return true;
    }


    //actions on the menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent(this, LanguageActivity.class);
                startActivity(intent);
                break;
            case R.id.action_about:
                Intent intent2 = new Intent(this, AboutActivity.class);
                startActivity(intent2);
                break;

            default:
                break;
        }

        return true;
    }


    private void addEventFirebaseListener() {
        mDatabaseReference.child("Courses").orderByChild("name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (courseList.size() > 0)
                    courseList.clear();
                for (DataSnapshot postSchnapshot : dataSnapshot.getChildren()) {
                    Course course = postSchnapshot.getValue(Course.class);
                    courseList.add(course);

                    registerForContextMenu(listCourses);

                }

                Collections.sort(courseList, new ComparatorNames());
                ArrayAdapter adapter = new ArrayAdapter(ListOfCoursesActivity.this, android.R.layout.simple_list_item_1, courseList);
                listCourses.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });
    }

    private void initFirebase() {

        FirebaseApp.initializeApp(this);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference();

    }


    //redirect to MainActivity when back button is pressed
    //finish
    @Override
    public void onBackPressed() {
        Intent myIntent = new Intent(ListOfCoursesActivity.this,
                MainActivity.class);
        startActivity(myIntent);
        finish();
    }


}
