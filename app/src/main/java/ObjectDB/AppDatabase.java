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
import Classes.Student;

import Classes.Teacher;
import ObjectDB.Student.StudentDAO;

/**
 * Created by loren on 17.04.2018.
 */

@Database(entities = {Student.class,  Class.class, Teacher.class, /* Course.class, Module.class */ }, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public static final int DATABASE_VERSION = 1;
    public static final String DB_NAME = "SchoolDB";
    public abstract StudentDAO sdtDao();

    private static AppDatabase mInstance;

    public synchronized static AppDatabase getInstance(Context context)
    {
       if (mInstance == null)
       {
           mInstance = Room.databaseBuilder(context, AppDatabase.class, DB_NAME).fallbackToDestructiveMigration().allowMainThreadQueries().build();
       }

       return mInstance;
    }

}
