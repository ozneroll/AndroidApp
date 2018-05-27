package com.schoolapp.schoolapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.schoolapp.schoolapp.MainActivity.mDatabaseReference;

/**
 * Created by loren on 12.05.2018.
 */

public class EditClassActivity extends AppCompatActivity {

    private EditText txtClassName;
    private String id;
    private String name;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_class);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(getResources().getString(R.string.edit));

        txtClassName = (EditText) findViewById(R.id.txtEditClassName);

        id = getIntent().getStringExtra("id");
        name = getIntent().getStringExtra("name");

        txtClassName.setText(name);


    }

    //creating the menu
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_settings, menu);
        inflater.inflate(R.menu.menu_save, menu);
        inflater.inflate(R.menu.menu_delete, menu);
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
                //deleting the class also deletes the students in it
                mDatabaseReference.child("Classes").child(id).child("listOfStudents").addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        List<String> idsOfStudents = new ArrayList<>();
                        for (DataSnapshot postSchnapshot : dataSnapshot.getChildren()) {
                            String str = postSchnapshot.getKey();
                            idsOfStudents.add(str);
                        }

                        //remove each student in list of class
                        for (String idOfStudent : idsOfStudents) {
                            mDatabaseReference.child("Students").child(idOfStudent).removeValue();
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });
                //remove the class
                MainActivity.mDatabaseReference.child("Classes").child(id).removeValue();
                //remove reference
                MainActivity.mDatabaseReference.child("ClassNameToId").child(name).removeValue();

                //confirmation for the user
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.deletesuccess), Toast.LENGTH_LONG).show();
                Intent intent3 = new Intent(EditClassActivity.this,
                        ListOfClassesActivity.class);

                startActivity(intent3);

                break;
            case R.id.btnSave:
                int error = 0;
                if (txtClassName.getText().toString().length() == 0) {
                    txtClassName.setError(getResources().getString(R.string.NomClasseRequis));
                    error = 1;
                }

                if (error == 0) {
                    //change name of the class
                    mDatabaseReference.child("Classes").child(id).child("name").setValue(txtClassName.getText().toString());
                    //change name of the reference by removing node and adding new one
                    mDatabaseReference.child("ClassNameToId").child(name).removeValue();
                    mDatabaseReference.child("ClassNameToId").child(txtClassName.getText().toString()).setValue(id);

                    //for each student, change name of the class
                    mDatabaseReference.child("Classes").child(id).child("listOfStudents").addListenerForSingleValueEvent(new ValueEventListener() {

                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            List<String> idsOfStudents = new ArrayList<>();
                            for (DataSnapshot postSchnapshot : dataSnapshot.getChildren()) {
                                //get the id of the student
                                String str = postSchnapshot.getKey();
                                idsOfStudents.add(str);
                            }

                            //change value of the class for each student
                            for (String idOfStudent : idsOfStudents) {
                                mDatabaseReference.child("Students").child(idOfStudent).child("classe").setValue(txtClassName.getText().toString());
                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }

                    });


                    //confirmation for the user
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.saved), Toast.LENGTH_LONG).show();
                    Intent myIntent = new Intent(EditClassActivity.this,
                            DetailClassActivity.class);

                    //give infos to DetailClassActivity
                    myIntent.putExtra("name", txtClassName.getText().toString());
                    myIntent.putExtra("id", id);
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
    protected void onStop() {
        super.onStop();
        finish();
    }
}
