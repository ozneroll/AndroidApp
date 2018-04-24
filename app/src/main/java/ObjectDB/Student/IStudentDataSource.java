package ObjectDB.Student;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import Classes.Student;
import io.reactivex.Flowable;

/**
 * Created by loren on 21.04.2018.
 */

public interface IStudentDataSource {

    Flowable<List<Student>> getAll();
    List<Student> getAllAsList();
    List<Student> loadAllByIds(int[] userIds);
    void update(Student...students);
    void insertAll(Student... students);
    void delete(Student student);
}
