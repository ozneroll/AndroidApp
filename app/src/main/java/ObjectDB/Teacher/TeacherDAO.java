package ObjectDB.Teacher;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import Classes.Teacher;
import io.reactivex.Flowable;

/**
 * Created by loren on 17.04.2018.
 */

@Dao
public interface TeacherDAO {
    /**
     * Gets a list of teachers
     *
     * @return List of teachers
     */
    @Query("SELECT * FROM Teacher")
    Flowable<List<Teacher>> getAll();

    /**
     * Gets a list of teachers
     *
     * @return List of teachers
     */
    @Query("SELECT * FROM Teacher")
    List<Teacher> getAllAsList();

    /**
     * Gets a list of teachers with ids
     *
     * @param userIds
     * @return List of teachers
     */
    @Query("SELECT * FROM Teacher WHERE uid IN (:userIds)")
    List<Teacher> loadAllByIds(int[] userIds);

    /**
     * Get a teacher with specific id
     *
     * @param uid
     * @return a teacher
     */
    @Query("SELECT * FROM Teacher WHERE uid LIKE :uid")
    Teacher loadTeacherById(int uid);

    @Update
    void update(Teacher... students);

    @Insert
    void insertAll(Teacher... students);

    @Delete
    void delete(Teacher student);

}
