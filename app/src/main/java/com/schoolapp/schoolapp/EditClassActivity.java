package com.schoolapp.schoolapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import Classes.Class;
import Classes.Teacher;

public class EditClassActivity extends AppCompatActivity {


    private EditText txtClassName;
    private int idClass;
    public Class classe = new Class() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_class);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(getResources().getString(R.string.edit));

        txtClassName = (EditText) findViewById(R.id.txtEditClassName);


        txtClassName.setText(getIntent().getStringExtra(getResources().getString(R.string.lastName)));
        idClass = getIntent().getIntExtra("idClass",-1);
        classe = MainActivity.studentDB.classDAO().loadClassById(idClass);

    }

    //creating the menu
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_settings, menu);
        inflater.inflate(R.menu.menu_save, menu);
        inflater.inflate(R.menu.menu_delete, menu);
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
            case R.id.action_delete:
                MainActivity.studentDB.classDAO().delete(classe);
                Intent intent3 = new Intent(EditClassActivity.this,
                        ListOfClassesActivity.class);
                startActivity(intent3);

                break;
            case R.id.btnSave:
                int error = 0;
                if( txtClassName.getText().toString().length() == 0 ) {
                    txtClassName.setError(getResources().getString(R.string.NomClasseRequis));
                    error =1;
                }

                if (error == 0) {
                    classe.setName(txtClassName.getText().toString());
                    MainActivity.studentDB.classDAO().update(classe);

                    Intent myIntent = new Intent(EditClassActivity.this,
                            DetailClassActivity.class);
                    myIntent.putExtra(getResources().getString(R.string.name), txtClassName.getText().toString());
                    myIntent.putExtra("id",classe.getIdclass());
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
    protected void onStop()
    {
        super.onStop();
        finish();
    }
}
