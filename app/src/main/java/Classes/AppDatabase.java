package Classes;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by loren on 17.04.2018.

@Database(entities = {Student.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract StudentDAO sdtDao();
}
 */