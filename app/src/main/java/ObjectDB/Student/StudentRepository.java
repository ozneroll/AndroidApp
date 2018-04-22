package ObjectDB.Student;

import java.util.List;

import Classes.Student;
import io.reactivex.Flowable;

/**
 * Created by loren on 21.04.2018.
 */

public class StudentRepository implements IStudentDataSource{

    private IStudentDataSource mLocalDataSource;

    private static StudentRepository mInstance;

    public StudentRepository(IStudentDataSource mLocalDataSource) {
        this.mLocalDataSource = mLocalDataSource;
    }

    public static StudentRepository getInstance(IStudentDataSource mLocalDataSource)
    {
        if (mInstance == null)
        {
            mInstance = new StudentRepository(mLocalDataSource);
        }
        return mInstance;
    }

    @Override
    public Flowable<List<Student>> getAll() {
        return mLocalDataSource.getAll();
    }

    @Override
    public List<Student> getAllAsList() {
        return mLocalDataSource.getAllAsList();
    }

    @Override
    public List<Student> loadAllByIds(int[] userIds) {
        return mLocalDataSource.loadAllByIds(userIds);
    }

    @Override
    public Student findByName(String first, String last) {
        return mLocalDataSource.findByName(first, last);
    }

    @Override
    public void update(Student... students) {
        mLocalDataSource.update(students);
    }

    @Override
    public void insertAll(Student...students) {
        mLocalDataSource.insertAll(students);
    }

    @Override
    public void delete(Student student) {
        mLocalDataSource.delete(student);
    }
}
