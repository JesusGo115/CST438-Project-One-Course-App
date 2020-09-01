package com.example.projectonecourseapp.db;

import com.example.projectonecourseapp.db.typeConverters.DateTypeConverter;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {}, version = 1)
@TypeConverters(DateTypeConverter.class)
public abstract class AppDatabase extends RoomDatabase
{
    public static final String DB_NAME = "COURSE_DATABASE";

    public static final String USER_TABLE = "USER_TABLE";
    public static final String COURSE_TABLE = "COURSE_TABLE";
    public static final String GRADE_CATEGORY_TABLE = "GRADE_CATEGORY_TABLE";
    public static final String ASSIGNMENT_TABLE = "ASSIGNMENT_TABLE";
    public static final String GRADE_TABLE = "GRADE_TABLE";
    public static final String ENROLLMENT_TABLE = "ENROLLMENT_TABLE";

    public abstract CourseAppDAO getCourseDAO();
}

//    DodoDAO mDodoDao;
//
//    private void getDatabase()
//    {
//        mDodoDao = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME)
//                .allowMainThreadQueries()
//                .build()
//                .getDodoDAO();
//    }