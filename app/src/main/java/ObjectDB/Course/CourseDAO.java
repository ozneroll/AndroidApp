package ObjectDB.Course;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import Classes.Course;
import io.reactivex.Flowable;

/**
 * Created by loren on 17.04.2018.
 */

@Dao
public interface CourseDAO {

    /**
     * Gets a list of courses
     *
     * @return list of courses
     */
    @Query("SELECT * FROM Course")
    Flowable<List<Course>> getAll();

    /**
     * Gets a list of courses
     *
     * @return list of courses
     */
    @Query("SELECT * FROM Course")
    List<Course> getAllAsList();

    /**
     * Gets a list of courses with ids
     *
     * @param Ids
     * @return List of courses
     */
    @Query("SELECT * FROM Course WHERE id IN (:Ids)")
    List<Course> loadAllByIds(int[] Ids);

    /**
     * Gets a list of courses for one module
     *
     * @param id_module
     * @return a list of courses
     */
    @Query("SELECT * FROM Course WHERE module LIKE :id_module ORDER BY name")
    List<Course> getAllListForOneModule(int id_module);

    @Insert
    void insertAll(Course... courses);

    @Delete
    void delete(Course course);

}
