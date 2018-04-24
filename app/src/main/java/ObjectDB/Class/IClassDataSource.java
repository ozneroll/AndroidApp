package ObjectDB.Class;

import java.util.List;

import Classes.Class;
import Classes.Student;
import io.reactivex.Flowable;

/**
 * Created by loren on 21.04.2018.
 */

public interface IClassDataSource {

    Flowable<List<Class>> getAll();

    List<Class> getAllAsList();

    List<Class> loadAllByIds(int[] Ids);

    Class loadClassById(int idclass);

    void insertAll(Class... classes);

    void delete(Class classe);
}
