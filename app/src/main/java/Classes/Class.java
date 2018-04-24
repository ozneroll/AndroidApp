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

@Entity(tableName = "Class")
public class Class {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idclass")
    private int idclass;

    @ColumnInfo(name = "name")
    private String name;

    //Constructors
    public Class() {


    }

    @Ignore
    public Class(String name) {
        this.name = name;
    }


    // Getters and setters
    public int getIdclass() {
        return idclass;
    }

    public String getName() {
        return name;
    }

    public void setIdclass(int id) {
        this.idclass = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.getName();
    }


}
