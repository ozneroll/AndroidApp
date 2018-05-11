package com.schoolapp.schoolapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

import Classes.Class;
import Classes.Student;


/**
 * Created by loren on 11.05.2018.
 */

public class ListOfClassesActivity extends AppCompatActivity {
    private ListView listClasses;
    private MaterialSearchView searchView;
    private FloatingActionButton fab;
    private List<Class> classeList = new ArrayList<>();
    private ArrayAdapter<Class> adapter;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_classes);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.classes));

        initFirebase();
        addEventFirebaseListener();

        listClasses = (ListView) findViewById(R.id.listitem);

        listClasses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Class _temp = (Class) adapterView.getItemAtPosition(i);

                Intent myIntent = new Intent(ListOfClassesActivity.this,
                        DetailClassActivity.class);
                myIntent.putExtra("name",_temp.getName());
                myIntent.putExtra("id", _temp.getUid());

                startActivity(myIntent);

            }
        });


        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                searchView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onSearchViewClosed() {

                listClasses = (ListView) findViewById(R.id.listitem);

                ArrayAdapter adapter = new ArrayAdapter(ListOfClassesActivity.this, android.R.layout.simple_list_item_1, classeList);
                listClasses.setAdapter(adapter);
            }
        });

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText != null && !newText.isEmpty()) {
                    List<Class> lstFound = new ArrayList<Class>();
                    for (Class item : classeList) {
                        if ((item.getName().toLowerCase().contains(newText) || item.getName().toUpperCase().contains(newText)))
                            lstFound.add(item);
                    }
                    ArrayAdapter adapter = new ArrayAdapter(ListOfClassesActivity.this, android.R.layout.simple_list_item_1, lstFound);
                    listClasses.setAdapter(adapter);
                } else {

                    ArrayAdapter adapter = new ArrayAdapter(ListOfClassesActivity.this, android.R.layout.simple_list_item_1, classeList);
                    listClasses.setAdapter(adapter);

                }
                return true;
            }

        });


        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            /*  public void onClick(View view) {
                  MainActivity.studentDB.sdtDao().insertAll(new Student("Célia", "Ahmad", "Rive des Nombieux 33"));
                  MainActivity.studentDB.sdtDao().loadAllByIds(new int[1]);
                  loadData();
  */
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(ListOfClassesActivity.this,
                        AddClassActivity.class);
                startActivity(myIntent)
                ;
            }

        });


    }

    //creating the menu
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_settings, menu);
        inflater.inflate(R.menu.menu_search,menu );
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return true;
    }
    private void addEventFirebaseListener() {
        mDatabaseReference.child("Classes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(classeList.size()>0 )
                    classeList.clear();
                for(DataSnapshot postSchnapshot : dataSnapshot.getChildren()){
                    Class classe = postSchnapshot.getValue(Class.class);
                    classeList.add(classe);

                    registerForContextMenu(listClasses);

                }
                ArrayAdapter adapter = new ArrayAdapter(ListOfClassesActivity.this, android.R.layout.simple_list_item_1, classeList);
                listClasses.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });
    }

    private void initFirebase() {

        FirebaseApp.initializeApp(this);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference();

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

    //redirect to MainActivity when back button is pressed
    //finish
    @Override
    public void onBackPressed() {
        Intent myIntent = new Intent(ListOfClassesActivity.this,
                MainActivity.class);
        startActivity(myIntent);
        finish();
    }


}
