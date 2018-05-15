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

import Classes.Teacher;

import static com.schoolapp.schoolapp.MainActivity.mDatabaseReference;

/**
 * Created by loren on 12.05.2018.
 */

public class EditTeacherActivity extends AppCompatActivity {
    private EditText txtFirstName;
    private EditText txtLastName;
    private String id;
    public Teacher teacher = new Teacher();
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_teacher);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(getResources().getString(R.string.edit));

        txtFirstName = (EditText) findViewById(R.id.txtEditFirstName);
        txtLastName = (EditText) findViewById(R.id.txtEditLastName);

        //set the infos
        txtLastName.setText(getIntent().getStringExtra("lastName"));
        txtFirstName.setText(getIntent().getStringExtra("firstName"));
        id = getIntent().getStringExtra("id");


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
                //delete the teacher
                mDatabaseReference.child("Teachers").child(getIntent().getStringExtra("id")).removeValue();
                //confirmation for the user
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.deletesuccess), Toast.LENGTH_LONG).show();
                Intent intent3 = new Intent(EditTeacherActivity.this,
                        ListOfTeachersActivity.class);
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

                if (error == 0) {
                    teacher.setLastName(txtLastName.getText().toString());
                    teacher.setFirstName(txtFirstName.getText().toString());
                    mDatabaseReference.child("Teachers").child(id).child("lastName").setValue(txtLastName.getText().toString());
                    mDatabaseReference.child("Teachers").child(id).child("firstName").setValue(txtFirstName.getText().toString());

                    //confirmation for the user
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.saved), Toast.LENGTH_LONG).show();
                    Intent myIntent = new Intent(EditTeacherActivity.this,
                            DetailTeacherActivity.class);

                    //give infos to DetailTeacherActivity
                    myIntent.putExtra("lastName", txtLastName.getText().toString());
                    myIntent.putExtra("firstName", txtFirstName.getText().toString());
                    myIntent.putExtra("id", id);
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
