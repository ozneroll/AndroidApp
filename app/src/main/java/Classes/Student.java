package Classes;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Project : AndroidApp
 * Created by Célia Ahmad & Lorenzo Lamberti on 17.04.2018.
 */

public class Student implements Comparable<Student> {

    private String uid;

    private String lastName;

    private String firstName;

    private String address;

    private String classe;

    //required by firebase
    public Student() {

    }

    public Student(String uid, String lastName, String firstName, String address, String classe) {

        this.uid = uid;
        this.lastName = lastName;
        this.firstName = firstName;
        this.address = address;
        this.classe = classe;
    }

    // Getters and setters

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    @Override
    public String toString() {
        return lastName + " " + firstName;
    }

    @Override
    public int compareTo(@android.support.annotation.NonNull Student st) {

        Student e = (Student) st;

        if (this.firstName.equals(e.getFirstName())) {
            return this.lastName.compareTo(e.getLastName());
        } else {
            return this.firstName.compareTo(e.getFirstName());
        }


    }
}
