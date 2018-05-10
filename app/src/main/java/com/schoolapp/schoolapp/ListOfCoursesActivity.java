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

import Classes.Course;
import ObjectDB.Course.CourseDataSource;
import ObjectDB.Course.CourseRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ListOfCoursesActivity extends AppCompatActivity {

    private ListView listCourses;
    private MaterialSearchView searchView;
    private List<Course> courseList = new ArrayList<>();
    private ArrayAdapter<Course> adapter;
    private CompositeDisposable compositeDisposable;
    private CourseRepository courseRepository;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_courses);

        compositeDisposable = new CompositeDisposable();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.course));


        listCourses = (ListView) findViewById(R.id.listitem);

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, courseList);
        registerForContextMenu(listCourses);
        listCourses.setAdapter(adapter);

        courseRepository = CourseRepository.getInstance(CourseDataSource.getInstance(MainActivity.studentDB.courseDAO()));

        loadData();


        listCourses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Course _temp = (Course) adapterView.getItemAtPosition(i);
                int id = _temp.getId();


                List<Course> cours = MainActivity.studentDB.courseDAO().loadAllByIds(new int[]{id});

                Intent myIntent = new Intent(ListOfCoursesActivity.this,
                        DetailCourseActivity.class);
                myIntent.putExtra("Cours", cours.get(0).getName());
                myIntent.putExtra("IdModule", cours.get(0).getId_module());

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

                listCourses = (ListView) findViewById(R.id.listitem);
                ArrayAdapter adapter = new ArrayAdapter(ListOfCoursesActivity.this, android.R.layout.simple_list_item_1, courseList);
                listCourses.setAdapter(adapter);
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
                    List<Course> lstFound = new ArrayList<Course>();
                    for (Course item : courseList) {
                        if (item.getName().toUpperCase().contains(newText)||(item.getName().toLowerCase().contains(newText)))
                            lstFound.add(item);
                    }
                    ArrayAdapter adapter = new ArrayAdapter(ListOfCoursesActivity.this, android.R.layout.simple_list_item_1, lstFound);
                    listCourses.setAdapter(adapter);
                } else {

                    ArrayAdapter adapter = new ArrayAdapter(ListOfCoursesActivity.this, android.R.layout.simple_list_item_1, courseList);
                    listCourses.setAdapter(adapter);

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


    private void loadData() {
        Disposable disposable = courseRepository.getAll()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<Course>>() {
                               @Override
                               public void accept(List<Course> courses) throws Exception {
                                   onGetAllCoursesSuccess(courses);
                               }
                           },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Toast.makeText(ListOfCoursesActivity.this, "" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
        compositeDisposable.add(disposable);
    }

    private void onGetAllCoursesSuccess(List<Course> courses) {
        courseList.clear();
        courseList.addAll(courses);
        adapter.notifyDataSetChanged();
    }

    //redirect to MainActivity when back button is pressed
    //finish
    @Override
    public void onBackPressed() {
        Intent myIntent = new Intent(ListOfCoursesActivity.this,
                MainActivity.class);
        startActivity(myIntent);
        finish();
    }

}
