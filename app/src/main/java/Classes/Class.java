package Classes;

import java.util.HashMap;
import java.util.List;

import io.reactivex.annotations.NonNull;

/**
 * Project : AndroidApp
 * Created by Célia Ahmad & Lorenzo Lamberti on 17.04.2018.
 */

public class Class {

    private String uid;

    private String name;

    private HashMap<String, String> listOfStudents;

    //required by firebase
    public Class()
    {

    }

    public Class( String uid, String name) {

        this.uid = uid;
        this.name = name;
    }

    // Getters and setters
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, String> getListOfStudents() {
        return listOfStudents;
    }

    public void setListOfStudents(HashMap<String, String> listOfStudents) {
        this.listOfStudents = listOfStudents;
    }

    @Override
    public String toString() {
        return name ;
    }



}
