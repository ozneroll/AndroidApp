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

import java.util.concurrent.Executors;

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
import io.reactivex.annotations.NonNull;

/**
 * Created by loren on 17.04.2018.
 */

@Database(entities = {Student.class, Class.class, Teacher.class, Course.class, Module.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public static final int DATABASE_VERSION = 1;
    public static final String DB_NAME = "SchoolDB";

    public abstract StudentDAO sdtDao();

    public abstract ClassDAO classDAO();

    public abstract TeacherDAO teacherDAO();

    public abstract ModuleDAO moduleDAO();

    public abstract CourseDAO courseDAO();

    private static AppDatabase mInstance;

    public synchronized static AppDatabase getInstance(Context context) {
        if (mInstance == null) {
            mInstance = buildDatabase(context);

        }

        return mInstance;
    }

    private static AppDatabase buildDatabase(final Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, DB_NAME).fallbackToDestructiveMigration().addCallback(new Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
                Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
                        getInstance(context).classDAO().insertAll(populateDataClasses());
                        getInstance(context).moduleDAO().insertAll(populateDataModules());
                        getInstance(context).sdtDao().insertAll(populateDataStudents());
                        getInstance(context).courseDAO().insertAll(populateDataCourses());
                        getInstance(context).teacherDAO().insertAll(populateDataTeachers());


                    }
                });
            }
        }).allowMainThreadQueries().build();

    }

    public static Student[] populateDataStudents() {
        return new Student[]{
                new Student("Ahmad", "Célia", "Rive des Nombieux 33", 3),
                new Student("Lamberti", "Lorenzo", "Rue du Grand Clos 5", 3),
                new Student("Mitrovic", "Vlado", "Avenue des Champs 8", 5),
                new Student("Moreira", "Caroline", "Rue des Pommiers 29", 5),
                new Student("Arbellay", "Olivier", "Rue des Pommiers 29", 2),
                new Student("Coluccia", "Mick", "Rue des Pommiers 29", 4),
                new Student("Debons", "Jean-François", "Rue du Cimetière 12", 1),
                new Student("Duay", "Killian", "Rue des Pommiers 29", 2),
                new Student("Gindre", "Anne", "Rue des Pommiers 29", 3),
                new Student("Huser", "Gaétan", "Rue des Pommiers 29", 4),
                new Student("Glassey", "Aurélie", "Ruelle Sombre 39", 5),
                new Student("Lourenço", "Daniela", "Ruelle Sombre 39", 1),
                new Student("Picon", "Romain", "Ruelle Sombre 39", 2),
                new Student("Piguet", "Nicolas", "Ruelle Sombre 39", 3),
                new Student("Pinto", "Sam", "Ruelle du Chou 12", 5),
                new Student("Reichenbach", "Julien", "Ruelle du Chou 12", 5),
                new Student("Remion", "Quentin", "Ruelle du Chou 12", 4),
                new Student("Resin", "Julien", "Ruelle du Chou 12", 3),
                new Student("Rey", "Gauthier", "Route des Marais 9", 2),
                new Student("Roh", "Sylvain", "Route des Marais 9", 1),
                new Student("Salyador", "Beyza", "Route des Marais 9", 2),
                new Student("Schnyder", "Jonathan", "Route des Marais 9", 3),
                new Student("Sornette", "Jaufray", "Route des Marais 9", 4),
                new Student("Spahr", "Bryan", "Route des Marais 9", 3),
                new Student("Terrani", "Fabien", "Route des Marais 9", 2),
                new Student("Walter", "Florian", "Route des Marais 9", 1)

        };
    }

    public static Class[] populateDataClasses() {
        return new Class[]{
                new Class("601-F"),
                new Class("602-F"),
                new Class("603-F"),
                new Class("604-F"),
                new Class("605-F")
        };
    }

    public static Module[] populateDataModules() {
        return new Module[]{
                new Module("611-1 - L'entreprise"),
                new Module("611-2 - Communication écrite"),
                new Module("612-1 - Environnement économique"),
                new Module("621-2 - Structuration des données"),
                new Module("631-2 - Maîtrise de l'ordinateur"),
                new Module("632-2 - Réseaux informatiques d'entreprise"),
                new Module("633-1 - Algorithmes et structures de données")
        };

    }

    public static Course[] populateDataCourses() {
        return new Course[]{
                new Course("Entreprise", 1),
                new Course("Comptabilité", 1),
                new Course("Communication écrite", 2),
                new Course("Anglais", 2),
                new Course("Droit", 3),
                new Course("Environnement économique", 3),
                new Course("Marketing", 3),
                new Course("SQL", 4),
                new Course("XML", 4),
                new Course("Connaissances PC et OS", 5),
                new Course("Mathématiques", 5),
                new Course("Introduction aux réseaux", 6),
                new Course("Mathématiques", 6),
                new Course("Algorithmes", 7),
                new Course("Structuration de données", 7)
        };

    }

    public static Teacher[] populateDataTeachers() {
        return new Teacher[]{
                new Teacher("Widmer", "Antoine"),
                new Teacher("Schumacher", "Michael"),
                new Teacher("Grèzes", "Vincent"),
                new Teacher("Schumann", "René"),
                new Teacher("Lamon", "Anthony"),
                new Teacher("Cotting", "Alexandre")

        };
    }


}
