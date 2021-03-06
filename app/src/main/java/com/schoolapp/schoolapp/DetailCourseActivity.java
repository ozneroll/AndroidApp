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

import Classes.Module;

public class DetailCourseActivity extends AppCompatActivity {

    private TextView txtName;
    private TextView txtModule;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_course);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(getResources().getString(R.string.details));

        txtName = (TextView) findViewById(R.id.txtName);
        txtModule = (TextView) findViewById(R.id.txtModule);

        txtName.setText(getIntent().getStringExtra("Cours"));
        int id = getIntent().getIntExtra("IdModule", -1);
        List<Module> m = MainActivity.studentDB.moduleDAO().loadAllByIds(new int[]{id});
        txtModule.setText(m.get(0).getName());

    }
    //creating the menu
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_settings, menu);
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

            default:
                break;
        }

        return true;
    }

    //back to the list, finish the activity
    @Override
    public void onBackPressed() {
        Intent myIntent = new Intent(DetailCourseActivity.this,
                ListOfCoursesActivity.class);
        startActivity(myIntent);
        finish();
    }


}
