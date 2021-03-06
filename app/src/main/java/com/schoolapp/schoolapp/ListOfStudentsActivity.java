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
import android.widget.Toast;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

import Classes.Student;
import ObjectDB.Student.StudentDataSource;
import ObjectDB.Student.StudentRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ListOfStudentsActivity extends AppCompatActivity {
    private ListView listStudents;
    private MaterialSearchView searchView;
    private FloatingActionButton fab;
    private List<Student> studentList = new ArrayList<>();
    private ArrayAdapter<Student> adapter;
    private CompositeDisposable compositeDisposable;
    private StudentRepository studentRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_students);

        compositeDisposable = new CompositeDisposable();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.students));

        listStudents = (ListView) findViewById(R.id.listitem);

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, studentList);
        registerForContextMenu(listStudents);
        listStudents.setAdapter(adapter);

        studentRepository = StudentRepository.getInstance(StudentDataSource.getInstance(MainActivity.studentDB.sdtDao()));

        loadData();


        listStudents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Student _temp = (Student) adapterView.getItemAtPosition(i);
                int id = _temp.getUid();


                List<Student> etudiant = MainActivity.studentDB.sdtDao().loadAllByIds(new int[]{id});

                Intent myIntent = new Intent(ListOfStudentsActivity.this,
                        DetailStudentActivity.class);
                myIntent.putExtra(getResources().getString(R.string.firstName), etudiant.get(0).getFirstName());
                myIntent.putExtra(getResources().getString(R.string.lastName), etudiant.get(0).getLastName());
                myIntent.putExtra(getResources().getString(R.string.address), etudiant.get(0).getAddress());
                myIntent.putExtra("id", etudiant.get(0).getUid());
                myIntent.putExtra("idClasse", etudiant.get(0).getIdclass());

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

                listStudents = (ListView) findViewById(R.id.listitem);

                ArrayAdapter adapter = new ArrayAdapter(ListOfStudentsActivity.this, android.R.layout.simple_list_item_1, studentList);
                listStudents.setAdapter(adapter);
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
                    List<Student> lstFound = new ArrayList<Student>();
                    for (Student item : studentList) {
                        if ((item.getLastName().toLowerCase().contains(newText) || item.getFirstName().toLowerCase().contains(newText)) || (item.getLastName().toUpperCase().contains(newText) || item.getFirstName().toUpperCase().contains(newText)))
                            lstFound.add(item);
                    }
                    ArrayAdapter adapter = new ArrayAdapter(ListOfStudentsActivity.this, android.R.layout.simple_list_item_1, lstFound);
                    listStudents.setAdapter(adapter);
                } else {

                    ArrayAdapter adapter = new ArrayAdapter(ListOfStudentsActivity.this, android.R.layout.simple_list_item_1, studentList);
                    listStudents.setAdapter(adapter);

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
                Intent myIntent = new Intent(ListOfStudentsActivity.this,
                        AddStudentActivity.class);
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
        Disposable disposable = studentRepository.getAll()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<Student>>() {
                               @Override
                               public void accept(List<Student> students) throws Exception {
                                   onGetAllStudentsSuccess(students);
                               }
                           },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Toast.makeText(ListOfStudentsActivity.this, "" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
        compositeDisposable.add(disposable);
    }

    private void onGetAllStudentsSuccess(List<Student> students) {
        studentList.clear();
        studentList.addAll(students);
        adapter.notifyDataSetChanged();
    }

    //redirect to MainActivity when back button is pressed
    //finish
    @Override
    public void onBackPressed() {
        Intent myIntent = new Intent(ListOfStudentsActivity.this,
                MainActivity.class);
        startActivity(myIntent);
        finish();
    }


}