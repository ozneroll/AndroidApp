package com.schoolapp.schoolapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import Classes.Student;

public class DetailClassActivity extends AppCompatActivity {

    private TextView txtName;
    private ListView listStudent;
    private int idClass;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_class);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(getResources().getString(R.string.details));
        idClass = getIntent().getIntExtra("id", 1);

        txtName = (TextView) findViewById(R.id.txtName);
        listStudent = (ListView) findViewById(R.id.listStudent);

        txtName.setText(getIntent().getStringExtra(getResources().getString(R.string.name)));

        //list of students in the class
        List<Student> students = MainActivity.studentDB.sdtDao().getAllListForOneClass(idClass);
        ArrayAdapter<Student> adapter = new ArrayAdapter<Student>(DetailClassActivity.this,
                R.layout.textview, students);
        listStudent.setAdapter(adapter);

    }

    //creating the menu
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_settings, menu);
        inflater.inflate(R.menu.menu_edit, menu);

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
            case R.id.btnEdit:
                Intent intent3 = new Intent(this, EditClassActivity.class);
                intent3.putExtra(getResources().getString(R.string.lastName), txtName.getText());
                intent3.putExtra("idClass", idClass);
                startActivity(intent3);

            default:
                break;
        }

        return true;
    }

    //back to the list, finish the activity
    @Override
    public void onBackPressed() {
        Intent myIntent = new Intent(DetailClassActivity.this,
                ListOfClassesActivity.class);
        startActivity(myIntent);
        finish();
    }


}
