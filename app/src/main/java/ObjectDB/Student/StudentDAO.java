package ObjectDB.Student;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import Classes.Student;
import io.reactivex.Flowable;

/**
 * Created by loren on 17.04.2018.
 */

@Dao
public interface StudentDAO {
    @Query("SELECT * FROM Student ORDER BY FirstName")
    Flowable<List<Student>> getAll();

    @Query("SELECT * FROM Student ORDER BY FirstName")
    List<Student> getAllAsList();


    @Query("SELECT * FROM Student WHERE idclass LIKE :idclass ORDER BY FirstName")
    List<Student> getAllListForOneClass(int idclass);

    @Query("SELECT * FROM Student WHERE uid IN (:userIds) ")
    List<Student> loadAllByIds(int[] userIds);


    @Query("SELECT * FROM Student WHERE uid LIKE :uid")
    Student loadStudentById(int uid);

    @Query("SELECT * FROM Student WHERE firstName LIKE :first AND "
            + "lastName LIKE :last LIMIT 1")

    Student findByName(String first, String last);

    @Update
    void update(Student...students);

    @Insert
    void insertAll(Student... students);

    @Delete
    void delete(Student student);

}
