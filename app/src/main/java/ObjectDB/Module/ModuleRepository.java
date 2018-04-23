package ObjectDB.Module;

import java.util.List;

import Classes.Class;
import Classes.Module;
import io.reactivex.Flowable;

/**
 * Created by loren on 21.04.2018.
 */


public class ModuleRepository implements IModuleDataSource {

    private IModuleDataSource mLocalDataSource;

    private static ModuleRepository mInstance;


    @Override
    public Flowable<List<Module>> getAll() {
        return null;
    }

    @Override
    public List<Module> getAllAsList() {
        return null;
    }

    @Override
    public List<Module> loadAllByIds(int[] userIds) {
        return null;
    }

    @Override
    public Module findByName(String first) {
        return null;
    }

    @Override
    public void insertAll(Module... modules) {

    }

    @Override
    public void delete(Module module) {

    }
}
