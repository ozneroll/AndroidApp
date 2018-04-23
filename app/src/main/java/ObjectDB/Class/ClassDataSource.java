
package ObjectDB.Class;

import java.util.List;

import Classes.Class;
import Classes.Student;
import io.reactivex.Flowable;


/**
 * Created by loren on 21.04.2018.
 */


public class ClassDataSource implements IClassDataSource {

    private ClassDAO classDAO;
    private static ClassDataSource mInstance;

    public ClassDataSource(ClassDAO studentDAO)
    {
        this.classDAO = studentDAO;
    }

    public static ClassDataSource getInstance(ClassDAO studentDAO)
    {
        if(mInstance == null)
        {
            mInstance = new ClassDataSource(studentDAO);
        }
        return mInstance;
    }

    @Override
    public Flowable<List<Class>> getAll() {
        return classDAO.getAll();
    }

    @Override
    public List<Class> getAllAsList() {
        return classDAO.getAllAsList();
    }

    @Override
    public List<Class> loadAllByIds(int[] Ids) {
        return classDAO.loadAllByIds(Ids);
    }

    @Override
    public Class findByName(String first) {
        return classDAO.findByName(first);
    }

    @Override
    public void insertAll(Class...classes) {
        classDAO.insertAll();
    }

    @Override
    public void delete(Class classe) {
        classDAO.delete(classe);
    }
}

