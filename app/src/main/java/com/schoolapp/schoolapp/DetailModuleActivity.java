package com.schoolapp.schoolapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import Classes.Course;

public class DetailModuleActivity extends AppCompatActivity {

    private TextView txtName;
    private ListView listCourses;
    private int id;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_module);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(getResources().getString(R.string.details));
        id = getIntent().getIntExtra("idModule", 1);

        txtName = (TextView) findViewById(R.id.txtName);
        listCourses = (ListView) findViewById(R.id.listCourses);
        txtName.setText(getIntent().getStringExtra("nomModule"));
        //get list of courses
        List<Course> courses = MainActivity.studentDB.courseDAO().getAllListForOneModule(id);

        ArrayAdapter<Course> adapter = new ArrayAdapter<Course>(DetailModuleActivity.this,
                R.layout.textview, courses);
        listCourses.setAdapter(adapter);

    }

    //creating the menu
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_settings, menu);
        MenuItem itemSearch = menu.findItem(R.id.action_search);
        itemSearch.setVisible(false);
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
                ListOfModulesActivity.class);
        startActivity(myIntent);
        finish();
    }
}
