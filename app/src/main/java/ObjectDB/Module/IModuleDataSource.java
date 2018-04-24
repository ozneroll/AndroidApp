package ObjectDB.Module;

import java.util.List;

import Classes.Class;
import Classes.Module;
import io.reactivex.Flowable;

/**
 * Created by loren on 21.04.2018.
 */

public interface IModuleDataSource {

    Flowable<List<Module>> getAll();

    List<Module> getAllAsList();

    List<Module> loadAllByIds(int[] Ids);

    void insertAll(Module... modules);

    void delete(Module modules);
}
