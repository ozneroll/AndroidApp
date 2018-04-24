package com.schoolapp.schoolapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import Classes.Student;
import Classes.Teacher;

public class EditTeacherActivity extends AppCompatActivity {


    private EditText txtFirstName;
    private EditText txtLastName;
    private EditText txtAddress;
    private int id;
    public Teacher teacher = new Teacher() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_teacher);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(getResources().getString(R.string.edit));

        txtFirstName = (EditText) findViewById(R.id.txtEditFirstName);
        txtLastName = (EditText) findViewById(R.id.txtEditLastName);
        //  txtClass = (TextView) findViewById(R.id.txtClass);

        txtLastName.setText(getIntent().getStringExtra(getResources().getString(R.string.lastName)));
        txtFirstName.setText(getIntent().getStringExtra(getResources().getString(R.string.firstName)));
        id = getIntent().getIntExtra("id",-1);
        teacher = MainActivity.studentDB.teacherDAO().loadTeacherById(id);

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
                MainActivity.studentDB.teacherDAO().delete(teacher);
                Intent intent3 = new Intent(EditTeacherActivity.this,
                        ListOfTeachersActivity.class);
                startActivity(intent3);

                break;
            case R.id.btnSave:
                int error = 0;
                if( txtFirstName.getText().toString().length() == 0 ) {
                    txtFirstName.setError(getResources().getString(R.string.prenomRequis));
                    error =1;
                }
                if( txtLastName.getText().toString().length() == 0 ) {
                    txtLastName.setError(getResources().getString(R.string.nomRequis));
                    error =1;
                }

                if (error == 0) {
                    teacher.setLastName(txtLastName.getText().toString());
                    teacher.setFirstName(txtFirstName.getText().toString());
                    MainActivity.studentDB.teacherDAO().update(teacher);

                    Intent myIntent = new Intent(EditTeacherActivity.this,
                            DetailTeacherActivity.class);
                    myIntent.putExtra(getResources().getString(R.string.lastName), txtLastName.getText().toString());
                    myIntent.putExtra(getResources().getString(R.string.firstName), txtFirstName.getText().toString());
                    myIntent.putExtra("id", teacher.getUid());
                    startActivity(myIntent);
                }
                break;


            default:
                break;
        }

        return true;
    }
}
