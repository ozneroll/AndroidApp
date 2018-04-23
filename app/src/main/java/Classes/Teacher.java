package Classes;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import io.reactivex.annotations.NonNull;

/**
 * Created by loren on 17.04.2018.
 */
/*, foreignKeys = @ForeignKey(entity = Class.class, parentColumns = "id", childColumns = "class"*/
@Entity(tableName = "Teacher")
public class Teacher {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "uid")
    private int uid;

    @ColumnInfo(name = "lastName")
    private String lastName;

    @ColumnInfo(name = "firstName")
    private String firstName;


    public void setClasse(String classe) {
        this.classe = classe;
    }

    @ColumnInfo(name = "class")
    private String classe;



    public Teacher()
    {

    }
    @Ignore
    public Teacher(String lastName, String firstName)
    {
        this.lastName = lastName;
        this.firstName = firstName;

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

    public String getClasse() {
        return classe;
    }

    @Override
    public String toString()
    {
        return firstName + " " + lastName;
    }

}
