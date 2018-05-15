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

import static com.schoolapp.schoolapp.MainActivity.mDatabaseReference;


/**
 * Project : AndroidAppFirebase
 * Created by Célia Ahmad & Lorenzo Lamberti
 * on 11.05.2018.
 */

public class DetailModuleActivity extends AppCompatActivity {
    private ListView listCourses;
    private TextView txtClass;
    private String id;
    private Toolbar toolbar;
    List<String> listOfCourses = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_module);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listCourses = (ListView) findViewById(R.id.listCourses);
        getSupportActionBar().setTitle(getResources().getString(R.string.details));

        txtClass = (TextView) findViewById(R.id.txtName);

        txtClass.setText(getIntent().getStringExtra("name"));
        id = getIntent().getStringExtra("id");

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
                ListOfModulesActivity.class);
        startActivity(myIntent);
        finish();
    }


    //get all the courses on the class
    protected void getAllCourses() {


        mDatabaseReference.child("Modules").child(id).child("listOfCourses").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (listOfCourses.size() > 0)
                    listOfCourses.clear();
                for (DataSnapshot postSchnapshot : dataSnapshot.getChildren()) {
                    String str = postSchnapshot.getValue(String.class);
                    listOfCourses.add(str);

                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(DetailModuleActivity.this,
                        R.layout.textview, listOfCourses);
                listCourses.setAdapter(adapter);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });


    }


}