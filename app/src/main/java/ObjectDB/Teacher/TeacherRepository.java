package ObjectDB.Teacher;

import java.util.List;

import Classes.Student;
import Classes.Teacher;
import io.reactivex.Flowable;

/**
 * Created by loren on 21.04.2018.
 */

public class TeacherRepository implements ITeacherDataSource {

    private ITeacherDataSource mLocalDataSource;

    private static TeacherRepository mInstance;

    public TeacherRepository(ITeacherDataSource mLocalDataSource) {
        this.mLocalDataSource = mLocalDataSource;
    }

    public static TeacherRepository getInstance(ITeacherDataSource mLocalDataSource)
    {
        if (mInstance == null)
        {
            mInstance = new TeacherRepository(mLocalDataSource);
        }
        return mInstance;
    }

    @Override
    public Flowable<List<Teacher>> getAll() {
        return mLocalDataSource.getAll();
    }

    @Override
    public List<Teacher> getAllAsList() {
        return mLocalDataSource.getAllAsList();
    }

    @Override
    public List<Teacher> loadAllByIds(int[] userIds) {
        return mLocalDataSource.loadAllByIds(userIds);
    }

    @Override
    public Teacher findByName(String first, String last) {
        return mLocalDataSource.findByName(first, last);
    }

    @Override
    public void update(Teacher... teacher) {
        mLocalDataSource.update(teacher);
    }

    @Override
    public void insertAll(Teacher...teachers) {
        mLocalDataSource.insertAll(teachers);
    }

    @Override
    public void delete(Teacher teacher) {
        mLocalDataSource.delete(teacher);
    }
}
