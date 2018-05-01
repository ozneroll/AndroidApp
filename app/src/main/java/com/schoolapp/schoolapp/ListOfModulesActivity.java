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

import Classes.Module;
import ObjectDB.Module.ModuleDataSource;
import ObjectDB.Module.ModuleRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ListOfModulesActivity extends AppCompatActivity {

    private ListView listModules;
    private MaterialSearchView searchView;
    private List<Module> moduleList = new ArrayList<>();
    private ArrayAdapter<Module> adapter;
    private CompositeDisposable compositeDisposable;
    private ModuleRepository moduleRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_modules);

        compositeDisposable = new CompositeDisposable();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.modules));

        listModules = (ListView) findViewById(R.id.listitem);

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, moduleList);
        registerForContextMenu(listModules);
        listModules.setAdapter(adapter);

        moduleRepository = ModuleRepository.getInstance(ModuleDataSource.getInstance(MainActivity.studentDB.moduleDAO()));

        loadData();


        listModules.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Module _temp = (Module) adapterView.getItemAtPosition(i);
                int id = _temp.getId();


                List<Module> modules = MainActivity.studentDB.moduleDAO().loadAllByIds(new int[]{id});

                Intent myIntent = new Intent(ListOfModulesActivity.this,
                        DetailModuleActivity.class);
                myIntent.putExtra("nomModule", modules.get(0).getName());
                myIntent.putExtra("idModule", modules.get(0).getId());

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
                        if (item.getName().toUpperCase().contains(newText)||(item.getName().toLowerCase().contains(newText)))
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


    private void loadData() {
        Disposable disposable = moduleRepository.getAll()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<Module>>() {
                               @Override
                               public void accept(List<Module> teachers) throws Exception {
                                   onGetAllModulesSuccess(teachers);
                               }
                           },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Toast.makeText(ListOfModulesActivity.this, "" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
        compositeDisposable.add(disposable);
    }

    private void onGetAllModulesSuccess(List<Module> teachers) {
        moduleList.clear();
        moduleList.addAll(teachers);
        adapter.notifyDataSetChanged();
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
