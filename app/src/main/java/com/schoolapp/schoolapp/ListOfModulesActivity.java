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

import Classes.Module;


/**
 * Created by loren on 11.05.2018.
 */

public class ListOfModulesActivity extends AppCompatActivity {
    private ListView listModules;
    private MaterialSearchView searchView;
    private List<Module> moduleList = new ArrayList<>();
    private ArrayAdapter<Module> adapter;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_modules);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.modules));

        initFirebase();
        addEventFirebaseListener();

        listModules = (ListView) findViewById(R.id.listitem);



        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                searchView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onSearchViewClosed() {

                listModules = (ListView) findViewById(R.id.listitem);

                ArrayAdapter adapter = new ArrayAdapter(ListOfModulesActivity.this, android.R.layout.simple_list_item_1, moduleList);
                listModules.setAdapter(adapter);
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
                    List<Module> lstFound = new ArrayList<Module>();
                    for (Module item : moduleList) {
                        if ((item.getName().toLowerCase().contains(newText) ))
                            lstFound.add(item);
                    }
                    ArrayAdapter adapter = new ArrayAdapter(ListOfModulesActivity.this, android.R.layout.simple_list_item_1, lstFound);
                    listModules.setAdapter(adapter);
                } else {

                    ArrayAdapter adapter = new ArrayAdapter(ListOfModulesActivity.this, android.R.layout.simple_list_item_1, moduleList);
                    listModules.setAdapter(adapter);

                }
                return true;
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


    private void addEventFirebaseListener() {
        mDatabaseReference.child("Modules").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(moduleList.size()>0 )
                    moduleList.clear();
                for(DataSnapshot postSchnapshot : dataSnapshot.getChildren()){
                    Module module = postSchnapshot.getValue(Module.class);
                    moduleList.add(module);

                    registerForContextMenu(listModules);

                }
                ArrayAdapter adapter = new ArrayAdapter(ListOfModulesActivity.this, android.R.layout.simple_list_item_1, moduleList);
                listModules.setAdapter(adapter);
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



    //redirect to MainActivity when back button is pressed
    //finish
    @Override
    public void onBackPressed() {
        Intent myIntent = new Intent(ListOfModulesActivity.this,
                MainActivity.class);
        startActivity(myIntent);
        finish();
    }


}
