package com.schoolapp.schoolapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class DetailStudentActivity extends AppCompatActivity {

    private TextView txtFirstName;
    private TextView txtLastName;
    private TextView txtAddress;
    private TextView txtClass;
    private int id;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_detail_student);

       Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       setSupportActionBar(toolbar);

       getSupportActionBar().setTitle(getResources().getString(R.string.details));

       txtFirstName = (TextView) findViewById(R.id.txtFirstName);
       txtLastName = (TextView) findViewById(R.id.txtLastName);
       txtAddress = (TextView) findViewById(R.id.txtAddress);
     //  txtClass = (TextView) findViewById(R.id.txtClass);

       txtLastName.setText(getIntent().getStringExtra(getResources().getString(R.string.lastName)));
       txtFirstName.setText(getIntent().getStringExtra(getResources().getString(R.string.firstName)));
       txtAddress.setText(getIntent().getStringExtra(getResources().getString(R.string.address)));
       id = getIntent().getIntExtra("id",1);

    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_settings, menu);
        inflater.inflate(R.menu.menu_edit, menu);
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

            case R.id.btnEdit:

                Intent intent3 = new Intent(this, EditStudentActivity.class);
                intent3.putExtra(getResources().getString(R.string.lastName), txtLastName.getText());
                intent3.putExtra(getResources().getString(R.string.firstName), txtFirstName.getText());
                intent3.putExtra(getResources().getString(R.string.address),txtAddress.getText());
                intent3.putExtra("id",id);
                startActivity(intent3);

            default:
                break;
        }

        return true;
    }




}
