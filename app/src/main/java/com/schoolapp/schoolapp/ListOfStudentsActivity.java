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
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

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
import io.reactivex.disposables.CompositeDisposable;


/**
 * Created by loren on 11.05.2018.
 */

public class ListOfStudentsActivity extends AppCompatActivity {
    private ListView listStudents;
    private MaterialSearchView searchView;
    private FloatingActionButton fab;
    private List<Student> studentList = new ArrayList<>();
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_students);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.students));

        initFirebase();
        addEventFirebaseListener();

        listStudents = (ListView) findViewById(R.id.listitem);
        listStudents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Student _temp = (Student) adapterView.getItemAtPosition(i);
                Intent myIntent = new Intent(ListOfStudentsActivity.this,
                        DetailStudentActivity.class);
                myIntent.putExtra("firstName",_temp.getFirstName());
                myIntent.putExtra("lastName", _temp.getLastName());
                myIntent.putExtra("address", _temp.getAddress());
                myIntent.putExtra("id", _temp.getUid());
                myIntent.putExtra("classe", _temp.getClasse());

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
                        AddStudentsActivity.class);
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
        mDatabaseReference.child("Students").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(studentList.size()>0 )
                    studentList.clear();
                for(DataSnapshot postSchnapshot : dataSnapshot.getChildren()){
                    Student student = postSchnapshot.getValue(Student.class);
                    studentList.add(student);

                    registerForContextMenu(listStudents);
                }
                ArrayAdapter adapter = new ArrayAdapter(ListOfStudentsActivity.this, android.R.layout.simple_list_item_1, studentList);
                listStudents.setAdapter(adapter);
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
        Intent myIntent = new Intent(ListOfStudentsActivity.this,
                MainActivity.class);
        startActivity(myIntent);
        finish();
    }

}
