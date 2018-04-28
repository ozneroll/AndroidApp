package com.schoolapp.schoolapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;


import Classes.Teacher;

import ObjectDB.Teacher.TeacherDataSource;
import ObjectDB.Teacher.TeacherRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ListOfTeachersActivity extends AppCompatActivity {

    private ListView listTeachers;
    private MaterialSearchView searchView;
    private FloatingActionButton fab;
    private List<Teacher> teacherList = new ArrayList<>();
    private ArrayAdapter<Teacher> adapter;
    private CompositeDisposable compositeDisposable;
    private TeacherRepository teacherRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_teachers);

        compositeDisposable = new CompositeDisposable();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.teachers));

        listTeachers = (ListView) findViewById(R.id.listitem);

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, teacherList);
        registerForContextMenu(listTeachers);
        listTeachers.setAdapter(adapter);

        teacherRepository = TeacherRepository.getInstance(TeacherDataSource.getInstance(MainActivity.studentDB.teacherDAO()));

        loadData();


        listTeachers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Teacher _temp = teacherList.get(i);
                int id = _temp.getUid();


                List<Teacher> prof = MainActivity.studentDB.teacherDAO().loadAllByIds(new int[]{id});

                Intent myIntent = new Intent(ListOfTeachersActivity.this,
                        DetailTeacherActivity.class);
                myIntent.putExtra("Prénom", prof.get(0).getFirstName());
                myIntent.putExtra("Nom", prof.get(0).getLastName());
                myIntent.putExtra("id", prof.get(0).getUid());

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

                listTeachers = (ListView) findViewById(R.id.listitem);

                ArrayAdapter adapter = new ArrayAdapter(ListOfTeachersActivity.this, android.R.layout.simple_list_item_1, teacherList);
                listTeachers.setAdapter(adapter);
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
                    List<Teacher> lstFound = new ArrayList<Teacher>();
                    for (Teacher item : teacherList) {
                        if ((item.getLastName().toLowerCase().contains(newText) || item.getFirstName().toLowerCase().contains(newText)) || (item.getLastName().toUpperCase().contains(newText) || item.getFirstName().toUpperCase().contains(newText)))
                            lstFound.add(item);
                    }
                    ArrayAdapter adapter = new ArrayAdapter(ListOfTeachersActivity.this, android.R.layout.simple_list_item_1, lstFound);
                    listTeachers.setAdapter(adapter);
                } else {

                    ArrayAdapter adapter = new ArrayAdapter(ListOfTeachersActivity.this, android.R.layout.simple_list_item_1, teacherList);
                    listTeachers.setAdapter(adapter);

                }
                return true;
            }

        });


        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {


            public void onClick(View arg0) {

                Intent myIntent = new Intent(ListOfTeachersActivity.this,
                        AddTeacherActivity.class);
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


    private void loadData() {
        Disposable disposable = teacherRepository.getAll()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<Teacher>>() {
                               @Override
                               public void accept(List<Teacher> teachers) throws Exception {
                                   onGetAllTeachersSuccess(teachers);
                               }
                           },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Toast.makeText(ListOfTeachersActivity.this, "" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
        compositeDisposable.add(disposable);
    }

    private void onGetAllTeachersSuccess(List<Teacher> teachers) {
        teacherList.clear();
        teacherList.addAll(teachers);
        adapter.notifyDataSetChanged();
    }

    //redirect to MainActivity when back button is pressed
    //finish
    @Override
    public void onBackPressed() {
        Intent myIntent = new Intent(ListOfTeachersActivity.this,
                MainActivity.class);
        startActivity(myIntent);
        finish();
    }

}
