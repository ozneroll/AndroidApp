package com.schoolapp.schoolapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import Classes.Student;

public class EditStudentActivity extends AppCompatActivity {


    private EditText txtFirstName;
    private EditText txtLastName;
    private EditText txtAddress;
    private int id;
    public Student etudiant = new Student() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(getResources().getString(R.string.edit));

        txtFirstName = (EditText) findViewById(R.id.txtEditFirstName);
        txtLastName = (EditText) findViewById(R.id.txtEditLastName);
        txtAddress = (EditText) findViewById(R.id.txtEditAddress);
        //  txtClass = (TextView) findViewById(R.id.txtClass);

        txtLastName.setText(getIntent().getStringExtra(getResources().getString(R.string.lastName)));
        txtFirstName.setText(getIntent().getStringExtra(getResources().getString(R.string.firstName)));
        txtAddress.setText(getIntent().getStringExtra(getResources().getString(R.string.address)));
        id = getIntent().getIntExtra("id",-1);
        etudiant = MainActivity.studentDB.sdtDao().loadStudentById(id);

    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_settings, menu);
        inflater.inflate(R.menu.menu_save, menu);
        inflater.inflate(R.menu.menu_delete, menu);
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
            case R.id.action_delete:
                MainActivity.studentDB.sdtDao().delete(etudiant);
                Intent intent3 = new Intent(EditStudentActivity.this,
                        ListOfStudentsActivity.class);
                startActivity(intent3);

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
                    etudiant.setLastName(txtLastName.getText().toString());
                    etudiant.setFirstName(txtFirstName.getText().toString());
                    etudiant.setAddress(txtAddress.getText().toString());
                    MainActivity.studentDB.sdtDao().update(etudiant);

                    Intent myIntent = new Intent(EditStudentActivity.this,
                            DetailStudentActivity.class);
                    myIntent.putExtra(getResources().getString(R.string.lastName), txtLastName.getText().toString());
                    myIntent.putExtra(getResources().getString(R.string.firstName), txtFirstName.getText().toString());
                    myIntent.putExtra(getResources().getString(R.string.address),txtAddress.getText().toString());
                    startActivity(myIntent);
                }
                break;


            default:
                break;
        }

        return true;
    }
}
