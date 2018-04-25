package com.schoolapp.schoolapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;


import Classes.Teacher;

public class AddTeacherActivity extends AppCompatActivity {

    private EditText txtFirstName;
    private EditText txtLastName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_teacher);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(getResources().getString(R.string.add));

        txtFirstName = (EditText) findViewById(R.id.txtaddFirstName);
        txtLastName = (EditText) findViewById(R.id.txtaddLastName);
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

            //save the teacher, but checking the values first
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
                    MainActivity.studentDB.teacherDAO().insertAll(new Teacher(txtLastName.getText().toString(), txtFirstName.getText().toString()));
                    //confirmation for the user
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.ajout), Toast.LENGTH_LONG).show();
                    Intent myIntent = new Intent(AddTeacherActivity.this,
                            ListOfTeachersActivity.class);
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
