package com.example.daniel.fittracker.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.example.daniel.fittracker.DataAccesObjects.ExerciseDAO;
import com.example.daniel.fittracker.DataAccesObjects.ExerciseProgressDAO;
import com.example.daniel.fittracker.DataAccesObjects.UserDAO;
import com.example.daniel.fittracker.DataAccesObjects.WeightDAO;
import com.example.daniel.fittracker.DataObjects.Exercise;
import com.example.daniel.fittracker.DataObjects.ExerciseProgress;
import com.example.daniel.fittracker.DataObjects.User;
import com.example.daniel.fittracker.DataObjects.Weight;
import com.example.daniel.fittracker.util.DateConverter;

import java.util.Date;

/**
 * Created by Daniel on 29.11.2017.
 */
@Database(entities = {User.class, Exercise.class, ExerciseProgress.class, Weight.class}, version = 2, exportSchema = false)
@TypeConverters({DateConverter.class})
public abstract class AppDatabase extends RoomDatabase{

    private static AppDatabase INSTANCE;

    public abstract UserDAO userDAO();
    public abstract ExerciseDAO exerciseDAO();
    public abstract ExerciseProgressDAO exerciseProgressDAO();
    public abstract WeightDAO weightDAO();

    public static AppDatabase getDatabase(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context, AppDatabase.class, "userdatabase")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
    public void destroyInstance(){
        INSTANCE = null;
    }
}
