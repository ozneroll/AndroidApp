package com.schoolapp.schoolapp;

import android.support.annotation.NonNull;

import Classes.Student;

/**
 * Created by loren on 27.05.2018.
 */

public class ComparatorNames implements java.util.Comparator<Object> {

    //comparator to have a list of students, teachers, etc... in alphabetical order
    @Override
    public int compare(Object s1, Object s2) {
        return s1.toString().compareTo(s2.toString());
    }
}
