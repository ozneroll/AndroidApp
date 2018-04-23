
package ObjectDB.Module;

import java.util.List;

import Classes.Module;
import io.reactivex.Flowable;


/**
 * Created by loren on 21.04.2018.
 */


public class ModuleDataSource implements IModuleDataSource {

    private ModuleDAO moduleDAO;
    private static ModuleDataSource mInstance;

    public ModuleDataSource(ModuleDAO moduleDAO)
    {
        this.moduleDAO = moduleDAO;
    }

    public static ModuleDataSource getInstance(ModuleDAO moduleDAO)
    {
        if(mInstance == null)
        {
            mInstance = new ModuleDataSource(moduleDAO);
        }
        return mInstance;
    }

    @Override
    public Flowable<List<Module>> getAll() {
        return moduleDAO.getAll();
    }

    @Override
    public List<Module> getAllAsList() {
        return moduleDAO.getAllAsList();
    }

    @Override
    public List<Module> loadAllByIds(int[] userIds) {
        return moduleDAO.loadAllByIds(userIds);
    }

    @Override
    public Module findByName(String first) {
        return moduleDAO.findByName(first);
    }

    @Override
    public void insertAll(Module...classes) {
        moduleDAO.insertAll();
    }

    @Override
    public void delete(Module classe) {
        moduleDAO.delete(classe);
    }
}

