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


@Dao
public interface ClassDAO {

    /**
     * Gets a list of classes
     *
     * @return List of classes
     */
    @Query("SELECT * FROM Class ORDER BY name")
    Flowable<List<Class>> getAll();

    /**
     * Gets a list of classes
     *
     * @return List of classes
     */
    @Query("SELECT * FROM Class ORDER BY name")
    List<Class> getAllAsList();


    /**
     * Gets a list of classes with ids
     *
     * @param Ids
     * @return List of classes
     */
    @Query("SELECT * FROM Class WHERE idclass IN (:Ids) ORDER BY name")
    List<Class> loadAllByIds(int[] Ids);


    /**
     * Get a class with specific id
     *
     * @param idclass
     * @return a class
     */
    @Query("SELECT * FROM Class WHERE idclass LIKE :idclass ORDER BY name")
    Class loadClassById(int idclass);

    @Insert
    void insertAll(Class... classes);

    @Update
    void update(Class... classes);

    @Delete
    void delete(Class classe);

}
