package com.schoolapp.schoolapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import Classes.Class;

/**
 * Created by loren on 11.05.2018.
 */

public class DetailStudentActivity extends AppCompatActivity {
    private TextView txtFirstName;
    private TextView txtLastName;
    private TextView txtAddress;
    private TextView txtClass;
    private String id;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_student);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(getResources().getString(R.string.details));

        txtFirstName = (TextView) findViewById(R.id.txtFirstName);
        txtLastName = (TextView) findViewById(R.id.txtLastName);
        txtAddress = (TextView) findViewById(R.id.txtAddress);
        txtClass = (TextView) findViewById(R.id.txtClass);
        //get the infos
        txtLastName.setText(getIntent().getStringExtra("lastName"));
        txtFirstName.setText(getIntent().getStringExtra("firstName"));
        txtAddress.setText(getIntent().getStringExtra("address"));
        txtClass.setText(getIntent().getStringExtra("classe"));
        id = getIntent().getStringExtra("id");
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
                //give the infos to next activity
                Intent intent3 = new Intent(this, EditStudentActivity.class);
                intent3.putExtra("lastName", txtLastName.getText());
                intent3.putExtra("firstName", txtFirstName.getText());
                intent3.putExtra("address", txtAddress.getText());
                intent3.putExtra("classe", txtClass.getText());
                intent3.putExtra("id", id);
                startActivity(intent3);
            default:
                break;
        }
        return true;
    }

    //back to the list, finish the activity
    @Override
    public void onBackPressed() {
        Intent myIntent = new Intent(DetailStudentActivity.this,
                ListOfStudentsActivity.class);
        startActivity(myIntent);
        finish();
    }
}
