package com.schoolapp.schoolapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import Classes.Class;
import Classes.Student;

public class AddStudentActivity extends AppCompatActivity {

    private EditText txtFirstName;
    private EditText txtLastName;
    private EditText txtAddress;
    private Spinner spinner;
    private List<Class> classes;
    private Class c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_add_student);


       Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       setSupportActionBar(toolbar);

       getSupportActionBar().setTitle(getResources().getString(R.string.add));

       classes = MainActivity.studentDB.classDAO().getAllAsList();

       txtFirstName = (EditText) findViewById(R.id.txtaddFirstName);
       txtLastName = (EditText) findViewById(R.id.txtaddLastName);
       txtAddress = (EditText) findViewById(R.id.txtaddAddress);

       spinner = (Spinner)findViewById(R.id.all_classes);
       // Create an ArrayAdapter using the string array and a default spinner layout
       ArrayAdapter<Class> adapter = new ArrayAdapter<Class>(this,android.R.layout.simple_spinner_item,classes);
        // Specify the layout to use when the list of choices appears
       adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
       spinner.setAdapter(adapter);
       c = (Class) spinner.getSelectedItem();


    }



    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_settings, menu);
        inflater.inflate(R.menu.menu_save, menu);
        MenuItem itemSearch = menu.findItem(R.id.action_search);
        itemSearch.setVisible(false);
        return true;
    }


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
            case R.id.btnSave:
                int error = 0;
                if( txtFirstName.getText().toString().length() == 0 ) {
                    txtFirstName.setError("First name is required!");
                    error =1;
                }
                if( txtLastName.getText().toString().length() == 0 ) {
                    txtLastName.setError("Last name is required!");
                    error =1;
                }

                if( txtAddress.getText().toString().length() == 0 ) {
                    txtAddress.setError("Address is required!");
                    error =1;
                }
                if (error == 0) {
                    MainActivity.studentDB.sdtDao().insertAll(new Student(txtLastName.getText().toString(), txtFirstName.getText().toString(), txtAddress.getText().toString(),  c.getIdclass()));
                    Intent myIntent = new Intent(AddStudentActivity.this,
                            ListOfStudentsActivity.class);
                    startActivity(myIntent);
                }
                break;


            default:
                break;
        }

        return true;
    }




}
