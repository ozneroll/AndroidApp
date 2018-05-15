package Classes;


import android.support.annotation.NonNull;

/**
 * Project : AndroidApp
 * Created by Célia Ahmad & Lorenzo Lamberti on 17.04.2018.
 */

public class Teacher implements Comparable<Teacher> {

    private String uid;

    private String lastName;


    private String firstName;


    //required by firebase
    public Teacher() {

    }

    public Teacher(String uid, String lastName, String firstName) {

        this.uid = uid;
        this.lastName = lastName;
        this.firstName = firstName;

    }

    // Getters and setters

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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


    @Override
    public String toString() {
        return lastName + " " + firstName;
    }

    @Override
    public int compareTo(@NonNull Teacher teacher) {
        Teacher e = (Teacher) teacher;

        if (this.firstName.equals(e.getFirstName())) {
            return this.lastName.compareTo(e.getLastName());
        } else {
            return this.firstName.compareTo(e.getFirstName());
        }
    }
}
