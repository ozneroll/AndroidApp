package com.schoolapp.schoolapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageButton;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import Classes.Course;
import Classes.Module;
import Classes.Student;


public class MainActivity extends AppCompatActivity {
    public static FirebaseDatabase mFirebaseDatabase;
    public static DatabaseReference mDatabaseReference;
    private CardView cv1;
    private CardView cv2;
    private CardView cv3;
    private CardView cv4;
    private CardView cv5;

    private ImageButton ib1;
    private ImageButton ib2;
    private ImageButton ib3;
    private ImageButton ib4;
    private ImageButton ib5;

/*  public String uid1;
    public String uid2;
    public String uid3;
    public String uid4;
    public String uid5;
    public String uid6;
    public String uid7;*/


    View.OnClickListener myListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        initFirebase();
        //     createModules();
        //     createCourses();
        super.onCreate(savedInstanceState);
        // Get the view from activity_main.xml
        setContentView(R.layout.activity_main);

        // Locate the button in activity_main.xml
        cv1 = (CardView) findViewById(R.id.cardview1);
        cv2 = (CardView) findViewById(R.id.cardview2);
        cv3 = (CardView) findViewById(R.id.cardview3);
        cv4 = (CardView) findViewById(R.id.cardview4);
        cv5 = (CardView) findViewById(R.id.cardview5);

        ib1 = (ImageButton) findViewById(R.id.imageButton1);
        ib2 = (ImageButton) findViewById(R.id.imageButton2);
        ib3 = (ImageButton) findViewById(R.id.imageButton3);
        ib4 = (ImageButton) findViewById(R.id.imageButton4);
        ib5 = (ImageButton) findViewById(R.id.imageButton5);


// Create an implementation of OnClickListener
        myListener = new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent;
                switch (v.getId() /*to get clicked view id**/) {
                    case R.id.cardview1:
                    case R.id.imageButton1:
                        myIntent = new Intent(MainActivity.this, ListOfStudentsActivity.class);
                        startActivity(myIntent);
                        break;
                    case R.id.cardview2:
                    case R.id.imageButton2:
                        myIntent = new Intent(MainActivity.this, ListOfClassesActivity.class);
                        startActivity(myIntent);
                        break;
                    case R.id.cardview3:
                    case R.id.imageButton3:
                        myIntent = new Intent(MainActivity.this, ListOfCoursesActivity.class);
                        startActivity(myIntent);
                        break;
                    case R.id.cardview4:
                    case R.id.imageButton4:
                        myIntent = new Intent(MainActivity.this, ListOfModulesActivity.class);
                        startActivity(myIntent);
                        break;
                    case R.id.cardview5:
                    case R.id.imageButton5:
                        myIntent = new Intent(MainActivity.this, ListOfTeachersActivity.class);
                        startActivity(myIntent);
                        break;
                    default:
                        break;
                }
            }
        };


    }

    public void onResume() {
        super.onResume();
        ib1.setOnClickListener(myListener);
        ib2.setOnClickListener(myListener);
        ib3.setOnClickListener(myListener);
        ib4.setOnClickListener(myListener);
        ib5.setOnClickListener(myListener);

        cv1.setOnClickListener(myListener);
        cv2.setOnClickListener(myListener);
        cv3.setOnClickListener(myListener);
        cv4.setOnClickListener(myListener);
        cv5.setOnClickListener(myListener);
    }

    public void onPause() {
        super.onPause();
        ib1.setOnClickListener(null);
        ib2.setOnClickListener(null);
        ib3.setOnClickListener(null);
        ib4.setOnClickListener(null);
        ib5.setOnClickListener(null);

        cv1.setOnClickListener(null);
        cv2.setOnClickListener(null);
        cv3.setOnClickListener(null);
        cv4.setOnClickListener(null);
        cv5.setOnClickListener(null);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    private void initFirebase() {

        FirebaseApp.initializeApp(this);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference();
    }

  /*  private void createModules() {
        List<Module> modules = new ArrayList<>();
        uid1 = UUID.randomUUID().toString();
        uid2 = UUID.randomUUID().toString();
        uid3 = UUID.randomUUID().toString();
        uid4 = UUID.randomUUID().toString();
        uid5 = UUID.randomUUID().toString();
        uid6 = UUID.randomUUID().toString();
        uid7 = UUID.randomUUID().toString();
        modules.add(new Module(uid1, "611-1 - L'entreprise"));
        modules.add(new Module(uid2, "611-2 - Communication écrite"));
        modules.add(new Module(uid3, "612-1 - Environnement économique"));
        modules.add(new Module(uid4, "621-2 - Structuration des données"));
        modules.add(new Module(uid5, "631-2 - Maîtrise de l'ordinateur"));
        modules.add(new Module(uid6, "632-2 - Réseaux informatiques d'entreprise"));
        modules.add(new Module(uid7, "633-1 - Algorithmes et structures de données"));

        for (Module module : modules
                ) {
            MainActivity.mDatabaseReference.child("Modules").child(module.getUid()).setValue(module);
            MainActivity.mDatabaseReference.child("ModuleNameToId").child(module.getName()).setValue(module.getUid());
        }

    }

    private void createCourses() {
       List<Course> courses = new ArrayList<>();

        String vid1 = UUID.randomUUID().toString();
        String vid2 = UUID.randomUUID().toString();
        String vid3 = UUID.randomUUID().toString();
        String vid4 = UUID.randomUUID().toString();
        String vid5 = UUID.randomUUID().toString();
        String vid6 = UUID.randomUUID().toString();
        String vid7 = UUID.randomUUID().toString();
        String vid8 = UUID.randomUUID().toString();
        String vid9 = UUID.randomUUID().toString();
        String vid10 = UUID.randomUUID().toString();
        String vid11 = UUID.randomUUID().toString();
        String vid12 = UUID.randomUUID().toString();
        String vid13 = UUID.randomUUID().toString();
        String vid14 = UUID.randomUUID().toString();


        courses.add(new Course(vid1, "Entreprise", "611-1 - L'entreprise"));
        courses.add(new Course(vid2, "Comptabilité", "611-1 - L'entreprise"));
        courses.add(new Course(vid3, "Communication écrite","611-2 - Communication écrite"));
        courses.add(new Course(vid4, "Anglais","611-2 - Communication écrite"));
        courses.add(new Course(vid5, "Droit","612-1 - Environnement économique"));
        courses.add(new Course(vid6, "Environnement économique","612-1 - Environnement économique"));
        courses.add(new Course(vid7, "Marketing","612-1 - Environnement économique"));
        courses.add(new Course(vid8, "SQL","621-2 - Structuration des données"));
        courses.add(new Course(vid9, "XML","621-2 - Structuration des données"));
        courses.add(new Course(vid10, "Connaissances PC et OS","631-2 - Maîtrise de l'ordinateur"));
        courses.add(new Course(vid11, "Mathématiques","631-2 - Maîtrise de l'ordinateur"));
        courses.add(new Course(vid12, "Introduction aux réseaux","632-2 - Réseaux informatiques d'entreprise"));
        courses.add(new Course(vid13, "Algorithmes","633-1 - Algorithmes et structures de données"));
        courses.add(new Course(vid14, "Structuration de données","633-1 - Algorithmes et structures de données"));

        for (Course course : courses
                ) {
            MainActivity.mDatabaseReference.child("Courses").child(course.getUid()).setValue(course);

        }

        MainActivity.mDatabaseReference.child("Modules").child(this.uid1).child("listOfCourses").child(vid1).setValue("Entreprise");
        MainActivity.mDatabaseReference.child("Modules").child(this.uid1).child("listOfCourses").child(vid2).setValue("Comptabilité");
        MainActivity.mDatabaseReference.child("Modules").child(this.uid2).child("listOfCourses").child(vid3).setValue("Communication écrite");
        MainActivity.mDatabaseReference.child("Modules").child(this.uid2).child("listOfCourses").child(vid4).setValue("Anglais");
        MainActivity.mDatabaseReference.child("Modules").child(this.uid3).child("listOfCourses").child(vid5).setValue("Droit");
        MainActivity.mDatabaseReference.child("Modules").child(this.uid3).child("listOfCourses").child(vid6).setValue("Environnement économique");
        MainActivity.mDatabaseReference.child("Modules").child(this.uid3).child("listOfCourses").child(vid7).setValue("Marketing");
        MainActivity.mDatabaseReference.child("Modules").child(this.uid4).child("listOfCourses").child(vid8).setValue("SQL");
        MainActivity.mDatabaseReference.child("Modules").child(this.uid4).child("listOfCourses").child(vid9).setValue("XML");
        MainActivity.mDatabaseReference.child("Modules").child(this.uid5).child("listOfCourses").child(vid10).setValue("Connaissances PC et OS");
        MainActivity.mDatabaseReference.child("Modules").child(this.uid5).child("listOfCourses").child(vid11).setValue("Mathématiques");
        MainActivity.mDatabaseReference.child("Modules").child(this.uid6).child("listOfCourses").child(vid12).setValue("Introduction aux réseaux");
        MainActivity.mDatabaseReference.child("Modules").child(this.uid7).child("listOfCourses").child(vid13).setValue("Algorithmes");
        MainActivity.mDatabaseReference.child("Modules").child(this.uid7).child("listOfCourses").child(vid14).setValue("Structuration de données");




    }*/
}