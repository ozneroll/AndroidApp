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
    /**
     * Gets a list of students
     *
     * @return List of students
     */
    @Query("SELECT * FROM Student ORDER BY lastName, firstName")
    Flowable<List<Student>> getAll();

    /**
     * Gets a list of students
     *
     * @return List of students
     */
    @Query("SELECT * FROM Student ORDER BY lastName, firstName")
    List<Student> getAllAsList();

    /**
     * Gets a list of students for one class
     *
     * @param idclass
     * @return a list of students
     */
    @Query("SELECT * FROM Student WHERE idclass LIKE :idclass ORDER BY lastName, firstName")
    List<Student> getAllListForOneClass(int idclass);

    /**
     * Gets a list of students with ids
     *
     * @param userIds
     * @return List of students
     */
    @Query("SELECT * FROM Student WHERE uid IN (:userIds) ")
    List<Student> loadAllByIds(int[] userIds);

    /**
     * Get a student with specific id
     *
     * @param uid
     * @return a student
     */
    @Query("SELECT * FROM Student WHERE uid LIKE :uid")
    Student loadStudentById(int uid);


    @Update
    void update(Student... students);

    @Insert
    void insertAll(Student... students);

    @Delete
    void delete(Student student);

}
