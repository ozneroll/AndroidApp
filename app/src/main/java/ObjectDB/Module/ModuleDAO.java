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

    /**
     * Gets a list of modules
     *
     * @return list of modules
     */
    @Query("SELECT * FROM Module ORDER BY name")
    Flowable<List<Module>> getAll();

    /**
     * Gets a list of modules
     *
     * @return list of modules
     */
    @Query("SELECT * FROM Module ORDER BY name")
    List<Module> getAllAsList();

    /**
     * Gets a list of modules with ids
     *
     * @param Ids
     * @return List of modules
     */
    @Query("SELECT * FROM Module WHERE id IN (:Ids) ORDER BY name")
    List<Module> loadAllByIds(int[] Ids);

    @Insert
    void insertAll(Module... modules);

    @Delete
    void delete(Module student);

}
