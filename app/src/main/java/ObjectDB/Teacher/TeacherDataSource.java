package ObjectDB.Teacher;

import java.util.List;

import Classes.Teacher;
import io.reactivex.Flowable;

/**
 * Created by loren on 21.04.2018.
 */

public class TeacherDataSource implements ITeacherDataSource {

    private TeacherDAO teacherDAO;
    private static TeacherDataSource mInstance;

    public TeacherDataSource(TeacherDAO studentDAO)
    {
        this.teacherDAO = studentDAO;
    }

    public static TeacherDataSource getInstance(TeacherDAO studentDAO)
    {
        if(mInstance == null)
        {
            mInstance = new TeacherDataSource(studentDAO);
        }
        return mInstance;
    }

    @Override
    public Flowable<List<Teacher>> getAll() {
        return teacherDAO.getAll();
    }

    @Override
    public List<Teacher> getAllAsList() {
        return teacherDAO.getAllAsList();
    }

    @Override
    public List<Teacher> loadAllByIds(int[] userIds) {
        return teacherDAO.loadAllByIds(userIds);
    }

    @Override
    public Teacher findByName(String first, String last) {
        return teacherDAO.findByName(first, last);
    }

    @Override
    public void update(Teacher... students) {
        teacherDAO.update(students);
    }

    @Override
    public void insertAll(Teacher...students) {
        teacherDAO.insertAll();
    }

    @Override
    public void delete(Teacher teacher) {
        teacherDAO.delete(teacher);
    }
}
