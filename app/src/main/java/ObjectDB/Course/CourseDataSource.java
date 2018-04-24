package ObjectDB.Course;

import java.util.List;

import Classes.Course;
import io.reactivex.Flowable;

/**
 * Created by loren on 21.04.2018.
 */

public class CourseDataSource implements ICourseDataSource {

    private CourseDAO courseDAO;
    private static CourseDataSource mInstance;

    public CourseDataSource(CourseDAO courseDAO)
    {
        this.courseDAO = courseDAO;
    }

    public static CourseDataSource getInstance(CourseDAO courseDAO)
    {
        if(mInstance == null)
        {
            mInstance = new CourseDataSource(courseDAO);
        }
        return mInstance;
    }

    @Override
    public Flowable<List<Course>> getAll() {
        return courseDAO.getAll();
    }

    @Override
    public List<Course> getAllAsList() {
        return courseDAO.getAllAsList();
    }

    @Override
    public List<Course> loadAllByIds(int[] Ids) {
        return courseDAO.loadAllByIds(Ids);
    }

    @Override
    public List<Course> getAllListForOneModule(int id_module)
    {
        return courseDAO.getAllListForOneModule(id_module);
    }

    @Override
    public void insertAll(Course...courses) {
        courseDAO.insertAll();
    }

    @Override
    public void delete(Course course) {
        courseDAO.delete(course);
    }
}
