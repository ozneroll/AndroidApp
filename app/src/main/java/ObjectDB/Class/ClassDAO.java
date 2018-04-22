package ObjectDB.Class;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import Classes.Class;
import Classes.Student;
import io.reactivex.Flowable;

/**
 * Created by loren on 17.04.2018.
 */

@Dao
public interface ClassDAO {
    @Query("SELECT * FROM Class")
    Flowable<List<Class>> getAll();

    @Query("SELECT * FROM Class")
    List<Class> getAllAsList();

    @Query("SELECT * FROM Class WHERE uid IN (:userIds)")
    List<Class> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM Class WHERE firstName LIKE :first LIMIT 1")


    Class findByName(String name);

    @Insert
    void insertAll(Class... classes);

    @Delete
    void delete(Class student);

}