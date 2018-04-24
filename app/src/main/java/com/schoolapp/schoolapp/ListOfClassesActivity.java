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

import Classes.Class;

import Classes.Student;
import ObjectDB.Class.ClassDataSource;
import ObjectDB.Class.ClassRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ListOfClassesActivity extends AppCompatActivity {

    private ListView listClasses;
    private MaterialSearchView searchView;
    private FloatingActionButton fab;
    private List<Class> classList = new ArrayList<>();
    private ArrayAdapter<Class> adapter;
    private CompositeDisposable compositeDisposable;
    private ClassRepository classRepository;
    private int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_classes);

        compositeDisposable = new CompositeDisposable();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.classes));

        listClasses = (ListView) findViewById(R.id.listitem);


        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, classList);
        registerForContextMenu(listClasses);
        listClasses.setAdapter(adapter);

        classRepository = ClassRepository.getInstance(ClassDataSource.getInstance(MainActivity.studentDB.classDAO()));

        loadData();


        listClasses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Class _temp = classList.get(i);
                int id = _temp.getIdclass();

                List<Class> classes = MainActivity.studentDB.classDAO().loadAllByIds(new int[]{id});
                Intent myIntent = new Intent(ListOfClassesActivity.this,
                        DetailClassActivity.class);
                myIntent.putExtra((getResources().getString(R.string.name)), classes.get(0).getName());
                myIntent.putExtra("id", classes.get(0).getIdclass());

                startActivity(myIntent);

            }
        });


        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {

            }

            @Override
            public void onSearchViewClosed() {

                listClasses = (ListView) findViewById(R.id.listitem);

                ArrayAdapter adapter = new ArrayAdapter(ListOfClassesActivity.this, android.R.layout.simple_list_item_1, classList);
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
                    for (Class item : classList) {
                        if (item.getName().contains(newText))
                            lstFound.add(item);
                    }
                    ArrayAdapter adapter = new ArrayAdapter(ListOfClassesActivity.this, android.R.layout.simple_list_item_1, lstFound);
                    listClasses.setAdapter(adapter);
                } else {

                    ArrayAdapter adapter = new ArrayAdapter(ListOfClassesActivity.this, android.R.layout.simple_list_item_1, classList);
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

    public void onClick(View v) {
        // Start NewActivity.class
        Intent myIntent = new Intent(ListOfClassesActivity.this,
                DetailStudentActivity.class);
        startActivity(myIntent);
    }

    private void loadData() {
        Disposable disposable = classRepository.getAll()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<Class>>() {
                               @Override
                               public void accept(List<Class> classes) throws Exception {
                                   onGetAllClassesSuccess(classes);
                               }
                           },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Toast.makeText(ListOfClassesActivity.this, "" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
        compositeDisposable.add(disposable);
    }

    private void onGetAllClassesSuccess(List<Class> classes) {
        classList.clear();
        classList.addAll(classes);
        adapter.notifyDataSetChanged();
    }

    //redirect to MainActivity when back button is pressed

    @Override
    public void onBackPressed() {
        Intent myIntent = new Intent(ListOfClassesActivity.this,
                MainActivity.class);
        startActivity(myIntent);
    }
}
