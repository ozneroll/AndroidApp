package Classes;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;

/**
 * Created by loren on 17.04.2018.
 */

@Entity(tableName = "Student", foreignKeys = @ForeignKey(entity = Class.class, parentColumns = "idclass", childColumns = "idclass"))
public class Student {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "uid")
    private int uid;

    @ColumnInfo(name = "lastName")
    private String lastName;

    @ColumnInfo(name = "firstName")
    private String firstName;

    @ColumnInfo(name = "address")
    private String address;

    @ColumnInfo(name = "idclass")
    private int idclass;


    public Student()
    {

    }
    @Ignore
    public Student(String lastName, String firstName, String address, int idclass)
    {
        this.lastName = lastName;
        this.firstName = firstName;
        this.address = address;
        this.idclass = idclass;
    }


    public void setClasse(int idclasse) {
        this.idclass = idclass;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public int getIdclass() {
        return idclass;
    }

    public void setIdclass(int idclass) {
        this.idclass = idclass;
    }
    @Override
    public String toString()
    {
        return firstName + " " + lastName;
    }

}
