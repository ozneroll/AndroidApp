package com.schoolapp.schoolapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import Classes.Course;

import static com.schoolapp.schoolapp.MainActivity.mDatabaseReference;


/**
 * Project : AndroidAppFirebase
 * Created by Célia Ahmad & Lorenzo Lamberti
 * on 11.05.2018.
 */

public class DetailModuleActivity extends AppCompatActivity {
    private ListView listCourses;
    private TextView txtModule;
    private Toolbar toolbar;
    List<Course> listOfCourses = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_class);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listCourses = (ListView) findViewById(R.id.listStudent);
        getSupportActionBar().setTitle(getResources().getString(R.string.details));

        txtModule = (TextView) findViewById(R.id.txtName);

        txtModule.setText(getIntent().getStringExtra("name"));

        getAllCourses();

    }

    //creating the menu
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_settings, menu);
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

    //back to the list, finish the activity
    @Override
    public void onBackPressed() {
        Intent myIntent = new Intent(DetailModuleActivity.this,
                ListOfClassesActivity.class);
        startActivity(myIntent);
        finish();
    }

    protected void getAllCourses() {

   //     db.restaurants.find( { "borough" : "Brooklyn" } );
        mDatabaseReference.child("Courses").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (listOfCourses.size() > 0)
                    listOfCourses.clear();
                for (DataSnapshot postSchnapshot : dataSnapshot.getChildren()) {
                    Course course = postSchnapshot.getValue(Course.class);
                    listOfCourses.add(course);

                }

                ArrayAdapter<Course> adapter = new ArrayAdapter<Course>(DetailModuleActivity.this,
                        R.layout.textview, listOfCourses);
                listCourses.setAdapter(adapter);
                listCourses.setAdapter(adapter);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });


    }


}