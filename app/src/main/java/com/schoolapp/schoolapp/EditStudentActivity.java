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
import java.util.Map;

import Classes.Class;
import Classes.Student;

import static com.schoolapp.schoolapp.MainActivity.mDatabaseReference;

/**
 * Created by loren on 11.05.2018.
 */

public class EditStudentActivity extends AppCompatActivity {
    private EditText txtFirstName;
    private EditText txtLastName;
    private EditText txtAddress;
    private int id;
    ArrayAdapter<String> adapter;
    private Spinner spinner;
    private int idClass;
    private Toolbar toolbar;
    List<String> listOfClasses = new ArrayList<>();

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

        idClass = getIntent().getIntExtra("idClass", -1);

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
                adapter = new ArrayAdapter<String>(EditStudentActivity.this, android.R.layout.simple_spinner_item, listOfClasses);

                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spinner.setAdapter(adapter);

                int index = getIndex(spinner, getIntent().getStringExtra("classe"));

                spinner.setSelection(index);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

    }

   //getting the index of the correct class name
    public int getIndex(Spinner spinner, String myString) {

        int index = 0;

        for (int i = 0; i < spinner.getCount(); i++) {

            if (spinner.getItemAtPosition(i).equals(myString)) {
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
                mDatabaseReference.child("Students").child(getIntent().getStringExtra("id")).removeValue();
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

                    mDatabaseReference.child("Students").child(getIntent().getStringExtra("id")).child("lastName").setValue(txtLastName.getText().toString());
                    mDatabaseReference.child("Students").child(getIntent().getStringExtra("id")).child("firstName").setValue(txtFirstName.getText().toString());
                    mDatabaseReference.child("Students").child(getIntent().getStringExtra("id")).child("address").setValue(txtAddress.getText().toString());
                    mDatabaseReference.child("Students").child(getIntent().getStringExtra("id")).child("classe").setValue(spinner.getSelectedItem().toString());

                    //confirmation for the user
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.saved), Toast.LENGTH_LONG).show();
                    Intent myIntent = new Intent(EditStudentActivity.this,
                            DetailStudentActivity.class);
                    myIntent.putExtra("lastName", txtLastName.getText().toString());
                    myIntent.putExtra("firstName", txtFirstName.getText().toString());
                    myIntent.putExtra("address", txtAddress.getText().toString());
                    myIntent.putExtra("classe", spinner.getSelectedItem().toString());
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
