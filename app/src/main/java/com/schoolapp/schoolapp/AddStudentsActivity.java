package com.schoolapp.schoolapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import Classes.Class;
import Classes.Student;

import static com.schoolapp.schoolapp.MainActivity.mDatabaseReference;

/**
 * Created by loren on 10.05.2018.
 */

public class AddStudentsActivity extends AppCompatActivity {
    private EditText txtFirstName;
    private EditText txtLastName;
    private EditText txtAddress;
    private Spinner spinner;
    private Toolbar toolbar;
    ArrayAdapter<String> adapter;
    List<String> listOfClasses = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.add));

        txtFirstName = (EditText) findViewById(R.id.txtaddFirstName);
        txtLastName = (EditText) findViewById(R.id.txtaddLastName);
        txtAddress = (EditText) findViewById(R.id.txtaddAddress);

        spinner = (Spinner) findViewById(R.id.all_classes);

        mDatabaseReference.child("Classes").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (listOfClasses.size() > 0)
                    listOfClasses.clear();
                for (DataSnapshot postSchnapshot : dataSnapshot.getChildren()) {
                    Class classe = postSchnapshot.getValue(Class.class);
                    listOfClasses.add(classe.toString());
                }

                // Create an ArrayAdapter using the string array and a default spinner layout
                adapter = new ArrayAdapter<String>(AddStudentsActivity.this, android.R.layout.simple_spinner_item, listOfClasses);

                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spinner.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

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

                if (txtAddress.getText().toString().length() == 0) {
                    txtAddress.setError(getResources().getString(R.string.adresseRequise));
                    error = 1;
                }
                if (error == 0) {
                   //confirmation for the user
                    createStudent();
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.ajout), Toast.LENGTH_LONG).show();
                    Intent myIntent = new Intent(AddStudentsActivity.this,
                            ListOfStudentsActivity.class);
                    startActivity(myIntent);
                }
                break;

            default:
                break;
        }

        return true;
    }

    private void createStudent(){
        String randomID= UUID.randomUUID().toString();
        Student student = new Student (randomID, txtLastName.getText().toString(), txtFirstName.getText().toString(),txtAddress.getText().toString(), spinner.getSelectedItem().toString());
        MainActivity.mDatabaseReference.child("Students").child(randomID).setValue(student);
    }
    //finish the activity
    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}


