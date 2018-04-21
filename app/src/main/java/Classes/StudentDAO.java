package Classes;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by loren on 17.04.2018.


@Dao
public interface StudentDAO {
    @Query("SELECT * FROM Students")
    List<Student> getAll();

    @Query("SELECT * FROM Students WHERE uid IN (:userIds)")
    List<Student> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM Students WHERE firstName LIKE :first AND "
            + "lastName LIKE :last LIMIT 1")

    Student findByName(String first, String last);

    @Insert
    void insertAll(Student... students);

    @Delete
    void delete(Student student);

}
 */