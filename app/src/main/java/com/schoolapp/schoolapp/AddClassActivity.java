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

import Classes.Class;
import Classes.Teacher;

public class AddClassActivity extends AppCompatActivity {


    private EditText txtClassName;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(getResources().getString(R.string.add));

        txtClassName = (EditText) findViewById(R.id.txtaddName);

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

            //check if values are filled, then validate and redirect to list
            case R.id.btnSave:
                int error = 0;
                if (txtClassName.getText().toString().length() == 0) {
                    txtClassName.setError(getResources().getString(R.string.NomClasseRequis));
                    error = 1;
                }

                if (error == 0) {
                    MainActivity.studentDB.classDAO().insertAll(new Class(txtClassName.getText().toString()));
                    //confirmation for the user
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.ajout), Toast.LENGTH_LONG).show();
                    Intent myIntent = new Intent(AddClassActivity.this,
                            ListOfClassesActivity.class);
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
