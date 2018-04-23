package ObjectDB.Course;

import java.util.List;

import Classes.Course;
import io.reactivex.Flowable;

/**
 * Created by loren on 21.04.2018.
 */

public class CourseRepository implements ICourseDataSource {

    private ICourseDataSource mLocalDataSource;

    private static CourseRepository mInstance;

    public CourseRepository(ICourseDataSource mLocalDataSource) {
        this.mLocalDataSource = mLocalDataSource;
    }

    public static CourseRepository getInstance(ICourseDataSource mLocalDataSource)
    {
        if (mInstance == null)
        {
            mInstance = new CourseRepository(mLocalDataSource);
        }
        return mInstance;
    }

    @Override
    public Flowable<List<Course>> getAll() {
        return mLocalDataSource.getAll();
    }

    @Override
    public List<Course> getAllAsList() {
        return mLocalDataSource.getAllAsList();
    }

    @Override
    public List<Course> loadAllByIds(int[] userIds) {
        return mLocalDataSource.loadAllByIds(userIds);
    }

    @Override
    public Course findByName(String name) {
        return mLocalDataSource.findByName(name);
    }

    @Override
    public void update(Course... course) {
        mLocalDataSource.update(course);
    }

    @Override
    public void insertAll(Course...courses) {
        mLocalDataSource.insertAll(courses);
    }

    @Override
    public void delete(Course course) {
        mLocalDataSource.delete(course);
    }
}
