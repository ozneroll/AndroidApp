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
    @Query("SELECT * FROM Teacher")
    Flowable<List<Teacher>> getAll();

    @Query("SELECT * FROM Teacher")
    List<Teacher> getAllAsList();

    @Query("SELECT * FROM Teacher WHERE uid IN (:userIds)")
    List<Teacher> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM Teacher WHERE firstName LIKE :first AND "
            + "lastName LIKE :last LIMIT 1")

    Teacher findByName(String first, String last);

    @Update
    void update(Teacher... students);

    @Insert
    void insertAll(Teacher... students);

    @Delete
    void delete(Teacher student);

}
