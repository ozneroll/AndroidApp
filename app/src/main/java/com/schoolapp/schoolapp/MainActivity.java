package com.schoolapp.schoolapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageButton;
import ObjectDB.AppDatabase;

public class MainActivity extends AppCompatActivity {
    public static AppDatabase studentDB;
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        studentDB = AppDatabase.getInstance(this);
        super.onCreate(savedInstanceState);
        // Get the view from activity_main.xml
        setContentView(R.layout.activity_main);

        // Locate the button in activity_main.xml
        cv1 = (CardView) findViewById(R.id.cardview1);
        cv2 = (CardView) findViewById(R.id.cardview2);
        cv3=  (CardView)  findViewById(R.id.cardview3);
        cv4 = (CardView) findViewById(R.id.cardview4);
        cv5 = (CardView) findViewById(R.id.cardview5);

        ib1 = (ImageButton) findViewById(R.id.imageButton1);
        ib2 = (ImageButton) findViewById(R.id.imageButton2);
        ib3 = (ImageButton) findViewById(R.id.imageButton3);
        ib4 = (ImageButton) findViewById(R.id.imageButton4);
        ib5 = (ImageButton) findViewById(R.id.imageButton5);


// Create an implementation of OnClickListener
        View.OnClickListener myListener = new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent;
                switch (v.getId() /*to get clicked view id**/) {
                    case R.id.cardview1:
                    case R.id.imageButton1 :
                        myIntent= new Intent(MainActivity.this, ListOfStudentsActivity.class);
                        startActivity(myIntent);
                        break;
                    case R.id.cardview2 :
                    case R.id.imageButton2:
                        myIntent= new Intent(MainActivity.this, ListOfClassesActivity.class);
                        startActivity(myIntent);
                        break;
                    case R.id.cardview3 :
                    case R.id.imageButton3:
                        myIntent= new Intent(MainActivity.this, ListOfCoursesActivity.class);
                        startActivity(myIntent);
                        break;
                    case R.id.cardview4 :
                    case R.id.imageButton4:
                        myIntent= new Intent(MainActivity.this, ListOfModulesActivity.class);
                        startActivity(myIntent);
                        break;
                    case R.id.cardview5 :
                    case R.id.imageButton5:
                        myIntent= new Intent(MainActivity.this, ListOfTeachersActivity.class);
                        startActivity(myIntent);
                        break;
                    default:
                        break;
                }
            }
        };
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


    /*    // Capture button clicks
        ib1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(MainActivity.this,
                        ListOfStudentsActivity.class);
                startActivity(myIntent);
            }
        }); */
    }







}