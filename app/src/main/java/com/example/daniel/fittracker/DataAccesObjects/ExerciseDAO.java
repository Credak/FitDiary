package com.example.daniel.fittracker.DataAccesObjects;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.daniel.fittracker.DataObjects.Exercise;

import java.util.List;

/**
 * Created by Daniel on 29.11.2017.
 */
@Dao
public interface ExerciseDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertExercise(Exercise exercise);

    @Update(onConflict =  OnConflictStrategy.REPLACE)
    void updateExercise(Exercise exercise);

    @Query("SELECT * FROM exercise")
    public List<Exercise> getAllExercises();

    @Query("SELECT * FROM exercise WHERE id = :exerciseid")
    public List<Exercise> getExercise(long exerciseid);

    @Delete
    void deleteExercise(Exercise exercise);

    @Query("DELETE from exercise")
    void deleteAllExercises();


}
