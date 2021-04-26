package com.sss.blogapp.roomdb;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Blog.class}, version = 1, exportSchema = false)
@TypeConverters({ListConverter.class})
public abstract class BlogDb extends RoomDatabase {
    public static BlogDb blogDb;
    public static String Database_Name = "blog_db";
    public static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static synchronized BlogDb getInstance(Context context) {
        if (blogDb == null) {
            blogDb = Room.databaseBuilder(context.getApplicationContext(),
                    BlogDb.class, Database_Name)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return blogDb;
    }

    public abstract BlogDao dao();      //abstract method of type SensorDao

}
