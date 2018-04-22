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


    @Override
    public Flowable<List<Class>> getAll() {
        return null;
    }

    @Override
    public List<Class> getAllAsList() {
        return null;
    }

    @Override
    public List<Class> loadAllByIds(int[] userIds) {
        return null;
    }

    @Override
    public Class findByName(String first) {
        return null;
    }

    @Override
    public void insertAll(Class... classes) {

    }

    @Override
    public void delete(Class classe) {

    }
}
