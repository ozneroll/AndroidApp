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
    @Query("SELECT * FROM Course")
    Flowable<List<Course>> getAll();

    @Query("SELECT * FROM Course")
    List<Course> getAllAsList();

    @Query("SELECT * FROM Course WHERE id IN (:Ids)")
    List<Course> loadAllByIds(int[] Ids);

    @Query("SELECT * FROM Course WHERE module LIKE :id_module ORDER BY name")
    List<Course> getAllListForOneModule(int id_module);

    @Update
    void update(Course... courses);

    @Insert
    void insertAll(Course... courses);

    @Delete
    void delete(Course course);

}
