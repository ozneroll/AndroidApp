package ObjectDB;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;

import Classes.Class;
import Classes.Course;
import Classes.Module;
import Classes.Student;

import Classes.Teacher;
import ObjectDB.Class.ClassDAO;
import ObjectDB.Course.CourseDAO;
import ObjectDB.Module.ModuleDAO;
import ObjectDB.Student.StudentDAO;
import ObjectDB.Teacher.TeacherDAO;

/**
 * Created by loren on 17.04.2018.
 */

@Database(entities = {Student.class , Class.class, Teacher.class,  Course.class, Module.class  }, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public static final int DATABASE_VERSION = 1;
    public static final String DB_NAME = "SchoolDB";
    public abstract StudentDAO sdtDao();
    public abstract ClassDAO classDAO();
    public abstract TeacherDAO teacherDAO();
    public abstract ModuleDAO moduleDAO();
    public abstract CourseDAO courseDAO();

    private static AppDatabase mInstance;

    public synchronized static AppDatabase getInstance(Context context)
    {
       if (mInstance == null)
       {
           mInstance = Room.databaseBuilder(context, AppDatabase.class, DB_NAME).fallbackToDestructiveMigration().allowMainThreadQueries().build();
           mInstance.classDAO().insertAll(

                   new Class("604-F"),
                   new Class("605-F"),
                   new Class("606-F"),
                   new Class("607-F"),
                   new Class("608-F")

           );
           mInstance.moduleDAO().insertAll(

                   new Module("Module 624"),
                   new Module("SAP"),
                   new Module("Programmation")


           );
           mInstance.courseDAO().insertAll(

                   new Course("Statistiques",1),
                   new Course("Statistiques",2),
                   new Course("Statistiques",3)



           );
       }

       return mInstance;
    }



}
