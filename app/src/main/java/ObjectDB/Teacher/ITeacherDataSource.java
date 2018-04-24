package ObjectDB.Teacher;

import java.util.List;

import Classes.Student;
import Classes.Teacher;
import io.reactivex.Flowable;

/**
 * Created by loren on 21.04.2018.
 */

public interface ITeacherDataSource {

    Flowable<List<Teacher>> getAll();

    List<Teacher> getAllAsList();

    List<Teacher> loadAllByIds(int[] userIds);

    void update(Teacher... teachers);

    void insertAll(Teacher... teachers);

    void delete(Teacher teacher);
}
