package com.schoolapp.schoolapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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

import Classes.Teacher;
import io.reactivex.disposables.CompositeDisposable;


/**
 * Created by loren on 11.05.2018.
 */

public class ListOfTeachersActivity extends AppCompatActivity {
    private ListView listTeachers;
    private MaterialSearchView searchView;
    private FloatingActionButton fab;
    private List<Teacher> teacherList = new ArrayList<>();
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_teachers);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.teachers));

        initFirebase();
        addEventFirebaseListener();

        listTeachers = (ListView) findViewById(R.id.listitem);


        listTeachers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Teacher _temp = (Teacher) adapterView.getItemAtPosition(i);

                Intent myIntent = new Intent(ListOfTeachersActivity.this,
                        DetailTeacherActivity.class);
                myIntent.putExtra("firstName", _temp.getFirstName());
                myIntent.putExtra("lastName", _temp.getLastName());
                myIntent.putExtra("id", _temp.getUid());


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

                listTeachers = (ListView) findViewById(R.id.listitem);

                ArrayAdapter adapter = new ArrayAdapter(ListOfTeachersActivity.this, android.R.layout.simple_list_item_1, teacherList);
                listTeachers.setAdapter(adapter);
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
                    List<Teacher> lstFound = new ArrayList<Teacher>();
                    for (Teacher item : teacherList) {
                        if ((item.getLastName().toLowerCase().contains(newText) || item.getFirstName().toLowerCase().contains(newText)) || (item.getLastName().toUpperCase().contains(newText) || item.getFirstName().toUpperCase().contains(newText)))
                            lstFound.add(item);
                    }
                    ArrayAdapter adapter = new ArrayAdapter(ListOfTeachersActivity.this, android.R.layout.simple_list_item_1, lstFound);
                    listTeachers.setAdapter(adapter);
                } else {

                    ArrayAdapter adapter = new ArrayAdapter(ListOfTeachersActivity.this, android.R.layout.simple_list_item_1, teacherList);
                    listTeachers.setAdapter(adapter);

                }
                return true;
            }

        });


        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            /*  public void onClick(View view) {
                  MainActivity.studentDB.sdtDao().insertAll(new Student("Célia", "Ahmad", "Rive des Nombieux 33"));
                  MainActivity.studentDB.sdtDao().loadAllByIds(new int[1]);
                  loadData();
  */
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(ListOfTeachersActivity.this,
                        AddTeachersActivity.class);
                startActivity(myIntent)
                ;
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

    private void addEventFirebaseListener() {
        mDatabaseReference.child("Teachers").orderByChild("lastname").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (teacherList.size() > 0)
                    teacherList.clear();
                for (DataSnapshot postSchnapshot : dataSnapshot.getChildren()) {
                    Teacher teacher = postSchnapshot.getValue(Teacher.class);
                    teacherList.add(teacher);

                    registerForContextMenu(listTeachers);

                }

                Collections.sort(teacherList, new ComparatorNames());
                ArrayAdapter adapter = new ArrayAdapter(ListOfTeachersActivity.this, android.R.layout.simple_list_item_1, teacherList);
                listTeachers.setAdapter(adapter);
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


    //redirect to MainActivity when back button is pressed
    //finish
    @Override
    public void onBackPressed() {
        Intent myIntent = new Intent(ListOfTeachersActivity.this,
                MainActivity.class);
        startActivity(myIntent);
        finish();
    }


}
