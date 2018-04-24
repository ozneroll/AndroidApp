package ObjectDB.Course;

import java.util.List;

import Classes.Course;
import io.reactivex.Flowable;

/**
 * Created by loren on 21.04.2018.
 */

public interface ICourseDataSource {

    Flowable<List<Course>> getAll();
    List<Course> getAllAsList();
    List<Course> loadAllByIds(int[] Ids);
    List<Course> getAllListForOneModule(int id_module);
    void update(Course... courses);
    void insertAll(Course... courses);
    void delete(Course course);
}
