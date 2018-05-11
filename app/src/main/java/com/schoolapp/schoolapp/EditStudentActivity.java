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

import java.util.List;

import Classes.Class;
import Classes.Student;

/**
 * Created by loren on 11.05.2018.
 */

public class EditStudentActivity extends AppCompatActivity{
    private EditText txtFirstName;
    private EditText txtLastName;
    private EditText txtAddress;
    private int id;
    public Student etudiant = new Student();
    private Spinner spinner;
    private List<Class> classes;
    private Class c = new Class();
    private int idClass;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(getResources().getString(R.string.edit));

        txtFirstName = (EditText) findViewById(R.id.txtEditFirstName);
        txtLastName = (EditText) findViewById(R.id.txtEditLastName);
        txtAddress = (EditText) findViewById(R.id.txtEditAddress);


        txtLastName.setText(getIntent().getStringExtra("lastName"));
        txtFirstName.setText(getIntent().getStringExtra("firstName"));
        txtAddress.setText(getIntent().getStringExtra("address"));

        id = getIntent().getIntExtra("id", -1);


        idClass = getIntent().getIntExtra("idClass", -1);



        spinner = (Spinner) findViewById(R.id.all_classes);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<Class> adapter = new ArrayAdapter<Class>(this, android.R.layout.simple_spinner_item, classes);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        /*
        c = MainActivity.studentDB.classDAO().loadClassById(idClass);
        int index = getIndex(spinner, c.getName());
        spinner.setSelection(index);
        */
    }

    //getting the index of the correct class name
    public int getIndex(Spinner spinner, String myString) {

        int index = 0;

        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equals(myString)) {
                index = i;
            }
        }
        return index;
    }

    //creating the menu
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_settings, menu);
        inflater.inflate(R.menu.menu_save, menu);
        inflater.inflate(R.menu.menu_delete, menu);
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
            case R.id.action_delete:
                MainActivity.mDatabaseReference.child("Students").child(getIntent().getStringExtra("id")).removeValue();
                //confirmation for the user
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.deletesuccess), Toast.LENGTH_LONG).show();
                Intent intent3 = new Intent(EditStudentActivity.this,
                        ListOfStudentsActivity.class);
                startActivity(intent3);

                break;
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
                    c = (Class) spinner.getSelectedItem();
                    etudiant.setLastName(txtLastName.getText().toString());
                    etudiant.setFirstName(txtFirstName.getText().toString());
                    etudiant.setAddress(txtAddress.getText().toString());
                    //etudiant.setIdclass(c.getIdclass());

                    //confirmation for the user
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.saved), Toast.LENGTH_LONG).show();
                    Intent myIntent = new Intent(EditStudentActivity.this,
                            DetailStudentActivity.class);
                    myIntent.putExtra(getResources().getString(R.string.lastName), txtLastName.getText().toString());
                    myIntent.putExtra(getResources().getString(R.string.firstName), txtFirstName.getText().toString());
                    myIntent.putExtra(getResources().getString(R.string.address), txtAddress.getText().toString());
                    myIntent.putExtra("id", etudiant.getUid());
                    //myIntent.putExtra("idClasse", c.getIdclass());
                    startActivity(myIntent);
                }
                break;


            default:
                break;
        }

        return true;
    }

    //finish the activity
    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
