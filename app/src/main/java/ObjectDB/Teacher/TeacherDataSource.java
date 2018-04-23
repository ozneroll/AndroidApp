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

    public TeacherDataSource(TeacherDAO teacherDAO)
    {
        this.teacherDAO = teacherDAO;
    }

    public static TeacherDataSource getInstance(TeacherDAO teacherDAO)
    {
        if(mInstance == null)
        {
            mInstance = new TeacherDataSource(teacherDAO);
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
    public void update(Teacher... teachers) {
        teacherDAO.update(teachers);
    }

    @Override
    public void insertAll(Teacher...teachers) {
        teacherDAO.insertAll();
    }

    @Override
    public void delete(Teacher teacher) {
        teacherDAO.delete(teacher);
    }
}
