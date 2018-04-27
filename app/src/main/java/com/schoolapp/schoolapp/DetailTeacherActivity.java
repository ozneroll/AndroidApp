package com.schoolapp.schoolapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class DetailTeacherActivity extends AppCompatActivity {

    private TextView txtFirstName;
    private TextView txtLastName;
    private TextView txtAddress;
    private int id;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_teacher);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(getResources().getString(R.string.details));

        txtFirstName = (TextView) findViewById(R.id.txtFirstName);
        txtLastName = (TextView) findViewById(R.id.txtLastName);

        txtFirstName.setText(getIntent().getStringExtra(getResources().getString(R.string.firstName)));
        txtLastName.setText(getIntent().getStringExtra(getResources().getString(R.string.lastName)));
        id = getIntent().getIntExtra("id", 1);

    }

    //creating the menu
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_settings, menu);
        inflater.inflate(R.menu.menu_edit, menu);
        MenuItem itemSearch = menu.findItem(R.id.action_search);
        itemSearch.setVisible(false);
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

                Intent intent3 = new Intent(this, EditTeacherActivity.class);
                intent3.putExtra(getResources().getString(R.string.lastName), txtLastName.getText());
                intent3.putExtra(getResources().getString(R.string.firstName), txtFirstName.getText());
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
        Intent myIntent = new Intent(DetailTeacherActivity.this,
                ListOfTeachersActivity.class);
        startActivity(myIntent);
        finish();
    }

}
