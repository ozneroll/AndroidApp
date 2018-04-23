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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_course);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(getResources().getString(R.string.details));

        txtName = (TextView) findViewById(R.id.txtName);
        txtModule = (TextView) findViewById(R.id.txtModule);
        
        //  txtClass = (TextView) findViewById(R.id.txtClass);

        txtName.setText(getIntent().getStringExtra("Cours"));
        int id = getIntent().getIntExtra("IdModule", -1);
        System.out.print("--------------------------------------------------------"+id);
        List<Module> m = MainActivity.studentDB.moduleDAO().loadAllByIds(new int[]{id});

        txtModule.setText(m.get(0).getName());

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
                Intent intent3 = new Intent(this, EditText.class);
                startActivity(intent3);

            default:
                break;
        }

        return true;
    }

}
