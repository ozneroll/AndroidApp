package Classes;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import io.reactivex.annotations.NonNull;

/**
 * Project : AndroidApp
 * Created by Célia Ahmad & Lorenzo Lamberti on 17.04.2018.
 */

public class Module {


    private String uid;


    private String name;




    //required by firebase
    public Module()
    {

    }

    public Module( String uid, String name ) {

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





    @Override
    public String toString() {
        return name ;
    }

}
