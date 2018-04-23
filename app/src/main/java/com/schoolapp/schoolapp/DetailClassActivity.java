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
    private TextView txtEt;
    private ListView listStudent;
    private int id;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_detail_class);

       Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       setSupportActionBar(toolbar);

       getSupportActionBar().setTitle(getResources().getString(R.string.classe));
       id  =getIntent().getIntExtra("id",1);

       txtName = (TextView) findViewById(R.id.txtName);
       listStudent = (ListView)findViewById(R.id.listStudent);


       txtName.setText(getIntent().getStringExtra(getResources().getString(R.string.name)));
       List<Student> students = MainActivity.studentDB.sdtDao().getAllListForOneClass(id);


       ArrayAdapter<Student> adapter = new ArrayAdapter<Student>(DetailClassActivity.this,
               android.R.layout.simple_list_item_1, students);
       listStudent.setAdapter(adapter);

    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_settings, menu);
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


            default:
                break;
        }

        return true;
    }




}
