package ObjectDB.Class;

import java.util.List;

import Classes.Class;
import Classes.Student;
import io.reactivex.Flowable;

/**
 * Created by loren on 21.04.2018.
 */


public class ClassRepository implements IClassDataSource {

    private IClassDataSource mLocalDataSource;

    private static ClassRepository mInstance;

    public ClassRepository(IClassDataSource mLocalDataSource) {
        this.mLocalDataSource = mLocalDataSource;
    }

    public static ClassRepository getInstance(IClassDataSource mLocalDataSource)
    {
        if (mInstance == null)
        {
            mInstance = new ClassRepository(mLocalDataSource);
        }
        return mInstance;
    }


    @Override
    public Flowable<List<Class>> getAll() {
        return mLocalDataSource.getAll();
    }

    @Override
    public List<Class> getAllAsList() {
        return mLocalDataSource.getAllAsList();
    }

    @Override
    public List<Class> loadAllByIds(int[] Ids) {
        return mLocalDataSource.loadAllByIds(Ids);
    }

    @Override
    public Class loadClassById(int idclass) {
        return null;
    }

    @Override
    public void insertAll(Class... classes) {
        mLocalDataSource.insertAll(classes);
    }

    @Override
    public void delete(Class classe) {
        mLocalDataSource.delete(classe);
    }
}
