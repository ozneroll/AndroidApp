package com.schoolapp.schoolapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

public class ListOfStudents extends AppCompatActivity{
    private ListView mListView;
    MaterialSearchView searchView;
    String[] listItems = {"Android","IPhone","WindowsMobile","Blackberry",
            "WebOS","Ubuntu","Windows7","Max OS X"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_students);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        searchView = (MaterialSearchView)findViewById(R.id.search_view);
        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {

            }

            @Override
            public void onSearchViewClosed() {

                mListView = (ListView) findViewById(R.id.listitem);

                ArrayAdapter adapter = new ArrayAdapter(ListOfStudents.this, android.R.layout.simple_list_item_1, listItems);
                mListView.setAdapter(adapter);
            }
        });
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText !=null && !newText.isEmpty()){
                    List<String> lstFound = new ArrayList<String>();
                    for(String item:listItems){
                        if(item.contains(newText))
                                lstFound.add(item);
                    }
                    ArrayAdapter adapter = new ArrayAdapter(ListOfStudents.this, android.R.layout.simple_list_item_1, lstFound);
                    mListView.setAdapter(adapter);
                }
                else{

                    ArrayAdapter adapter = new ArrayAdapter(ListOfStudents.this, android.R.layout.simple_list_item_1, listItems);
                    mListView.setAdapter(adapter);

                }
                return true;
            }

        });

        mListView = (ListView) findViewById(R.id.listitem);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems);
        mListView.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(ListOfStudents.this,
                        AddStudent.class);
                startActivity(myIntent);}


        });



    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_items, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return true;
    }

    public void onClick(View v) {
        // Start NewActivity.class
        Intent myIntent = new Intent(ListOfStudents.this,
                DetailsStudents.class);
        startActivity(myIntent);}

    }




