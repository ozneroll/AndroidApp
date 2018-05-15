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

import Classes.Student;

import static com.schoolapp.schoolapp.MainActivity.mDatabaseReference;


/**
 * Project : AndroidAppFirebase
 * Created by Célia Ahmad & Lorenzo Lamberti
 * on 11.05.2018.
 */

public class DetailModuleActivity extends AppCompatActivity {
    private ListView listStudent;
    private TextView txtClass;
    private String id;
    private Toolbar toolbar;
    List<Student> listOfStudents = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_class);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listStudent = (ListView) findViewById(R.id.listStudent);
        getSupportActionBar().setTitle(getResources().getString(R.string.details));

        txtClass = (TextView) findViewById(R.id.txtName);

        txtClass.setText(getIntent().getStringExtra("name"));
        id = getIntent().getStringExtra("id");

        getAllStudents();

    }

    //creating the menu
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_settings, menu);
        inflater.inflate(R.menu.menu_edit, menu);
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

            case R.id.btnEdit:
                Intent intent3 = new Intent(this, EditStudentActivity.class);
                intent3.putExtra("name", txtClass.getText());
                intent3.putExtra("id", id);
                startActivity(intent3);

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

    protected void getAllStudents() {

   //     db.restaurants.find( { "borough" : "Brooklyn" } );
        mDatabaseReference.child("Students").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (listOfStudents.size() > 0)
                    listOfStudents.clear();
                for (DataSnapshot postSchnapshot : dataSnapshot.getChildren()) {
                    Student student = postSchnapshot.getValue(Student.class);
                    listOfStudents.add(student);

                }

                ArrayAdapter<Student> adapter = new ArrayAdapter<Student>(DetailModuleActivity.this,
                        R.layout.textview, listOfStudents);
                listStudent.setAdapter(adapter);
                listStudent.setAdapter(adapter);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });
        // Create an ArrayAdapter using the string array and a default spinner layout






    }


}