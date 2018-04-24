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

    public ModuleRepository(IModuleDataSource mLocalDataSource) {
        this.mLocalDataSource = mLocalDataSource;
    }

    public static ModuleRepository getInstance(IModuleDataSource mLocalDataSource) {
        if (mInstance == null) {
            mInstance = new ModuleRepository(mLocalDataSource);
        }
        return mInstance;
    }


    @Override
    public Flowable<List<Module>> getAll() {
        return mLocalDataSource.getAll();
    }

    @Override
    public List<Module> getAllAsList() {
        return mLocalDataSource.getAllAsList();
    }

    @Override
    public List<Module> loadAllByIds(int[] Ids) {
        return mLocalDataSource.loadAllByIds(Ids);
    }

    @Override
    public void insertAll(Module... modules) {

    }

    @Override
    public void delete(Module module) {

    }
}
