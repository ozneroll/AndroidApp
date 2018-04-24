package ObjectDB.Student;

import java.util.List;

import Classes.Student;
import io.reactivex.Flowable;

/**
 * Created by loren on 21.04.2018.
 */

public class StudentDataSource implements IStudentDataSource {

    private StudentDAO studentDAO;
    private static StudentDataSource mInstance;

    public StudentDataSource(StudentDAO studentDAO)
    {
        this.studentDAO = studentDAO;
    }

    public static StudentDataSource getInstance(StudentDAO studentDAO)
    {
        if(mInstance == null)
        {
            mInstance = new StudentDataSource(studentDAO);
        }
        return mInstance;
    }

    @Override
    public Flowable<List<Student>> getAll() {
        return studentDAO.getAll();
    }

    @Override
    public List<Student> getAllAsList() {
        return studentDAO.getAllAsList();
    }

    @Override
    public List<Student> loadAllByIds(int[] userIds) {
        return studentDAO.loadAllByIds(userIds);
    }

    public Student loadStudentById(int uid ){return studentDAO.loadStudentById(uid); }

    @Override
    public void update(Student... students) {
        studentDAO.update(students);
    }

    @Override
    public void insertAll(Student...students) {
        studentDAO.insertAll();
    }

    @Override
    public void delete(Student student) {
        studentDAO.delete(student);
    }
}
