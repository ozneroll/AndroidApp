package ObjectDB.Module;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;


import Classes.Module;
import io.reactivex.Flowable;

/**
 * Created by loren on 17.04.2018.
 */

@Dao
public interface ModuleDAO {
    @Query("SELECT * FROM Module")
    Flowable<List<Module>> getAll();

    @Query("SELECT * FROM Module")
    List<Module> getAllAsList();

    @Query("SELECT * FROM Module WHERE id IN (:Ids)")
    List<Module> loadAllByIds(int[] Ids);

    @Query("SELECT * FROM Module WHERE name LIKE :name LIMIT 1")
    Module findByName(String name);

    @Insert
    void insertAll(Module... modules);

    @Delete
    void delete(Module student);

}
