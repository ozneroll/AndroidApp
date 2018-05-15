package com.schoolapp.schoolapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.UUID;

import Classes.Class;
import Classes.Student;
import Classes.Teacher;

public class AddTeachersActivity extends AppCompatActivity {
    private EditText txtFirstName;
    private EditText txtLastName;
    private EditText txtAddress;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_teacher);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(getResources().getString(R.string.add));

        txtFirstName = (EditText) findViewById(R.id.txtaddFirstName);
        txtLastName = (EditText) findViewById(R.id.txtaddLastName);


    }

    //creating the menu
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_settings, menu);
        inflater.inflate(R.menu.menu_save, menu);
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

            //check the values, then save the entry
            case R.id.btnSave:
                int error = 0;
                if (txtFirstName.getText().toString().length() == 0) {
                    txtFirstName.setError(getResources().getString(R.string.prenomRequis));
                    error = 1;
                }
                if (txtLastName.getText().toString().length() == 0) {
                    txtLastName.setError(getResources().getString(R.string.nomRequis));
                    error = 1;
                }

                if (error == 0) {
                    //call createTeacher() method
                    createTeacher();
                    //confirmation for the user
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.ajout), Toast.LENGTH_LONG).show();
                    Intent myIntent = new Intent(AddTeachersActivity.this,
                            ListOfTeachersActivity.class);
                    startActivity(myIntent);
                }
                break;

            default:
                break;
        }

        return true;
    }

    private void createTeacher() {
        //random id
        String randomID = UUID.randomUUID().toString();
        //new teacher
        Teacher teacher = new Teacher(randomID, txtLastName.getText().toString(), txtFirstName.getText().toString());
        //add values of teacher in DB
        MainActivity.mDatabaseReference.child("Teachers").child(randomID).setValue(teacher);
    }

    //finish the activity
    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}


