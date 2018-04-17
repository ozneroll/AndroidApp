package Classes;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by loren on 17.04.2018.
 */

@Entity
public class Student {


    @PrimaryKey
    private int uid;

    @ColumnInfo(name = "lastName")
    private String lastName;

    @ColumnInfo(name = "firstName")
    private String firstName;

    @ColumnInfo(name = "address")
    private String address;

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



}
