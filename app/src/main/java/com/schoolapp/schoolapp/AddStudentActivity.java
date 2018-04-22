package com.schoolapp.schoolapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddStudentActivity extends AppCompatActivity {

    private EditText txtFirstName;
    private EditText txtLastName;
    private EditText txtAddress;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_add_student);


       Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       setSupportActionBar(toolbar);

       getSupportActionBar().setTitle(getResources().getString(R.string.add));

       txtFirstName = (EditText) findViewById(R.id.txtaddFirstName);
       txtLastName = (EditText) findViewById(R.id.txtaddLastName);
       txtAddress = (EditText) findViewById(R.id.txtaddAddress);

    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_settings, menu);
        inflater.inflate(R.menu.menu_save, menu);
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

            case R.id.action_save:
             /*   if(!txtFirstName.getText().toString().equals("") && !txtLastName.getText().toString().equals("") &&
                        !txtAddress.getText().toString().equals("")) {
                        personDB.insertPerson(txtFirstName.getText().toString(), txtLastName.getText().toString(),
                                txtAddress.getText().toString());
                        finish();
                        overridePendingTransition(0, 0);
                        Intent i = new Intent(getApplicationContext(), ProfileActivity.class);
                        i.putExtra("idPerson", USER_CONNECTED.getId());
                        startActivity(i);
                        overridePendingTransition(0, 0);

                } else
                    Toast.makeText(getApplicationContext(), R.string.errorEmptyField, Toast.LENGTH_SHORT).show();
*/

            default:
                break;
        }

        return true;
    }




}
