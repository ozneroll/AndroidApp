package Classes;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.ArrayList;

import io.reactivex.annotations.NonNull;

/**
 * Created by loren on 17.04.2018.
 */

@Entity(tableName = "Class")
public class Class {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idclass")
    private int idclass;

    @ColumnInfo(name = "name")
    private String name;


    public Class(){


    }


    @Ignore
    public Class( String name) {
        this.name = name;
    }

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
    public String toString()
    {
        return this.getName();
    }


}
