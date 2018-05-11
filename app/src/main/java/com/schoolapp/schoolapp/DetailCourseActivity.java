package com.schoolapp.schoolapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
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

public class DetailCourseActivity extends AppCompatActivity {
    private TextView txtCourse;
    private TextView txtModule;
    private Toolbar toolbar;
    List<Course> listOfCourses = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_course);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(getResources().getString(R.string.details));

        txtCourse = (TextView) findViewById(R.id.txtName);
        txtCourse.setText(getIntent().getStringExtra("name"));
        txtModule = (TextView) findViewById(R.id.txtModule) ;
        txtModule.setText (getIntent().getStringExtra("module"));


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
        Intent myIntent = new Intent(DetailCourseActivity.this,
                ListOfClassesActivity.class);
        startActivity(myIntent);
        finish();
    }



}