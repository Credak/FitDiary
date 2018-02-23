package com.example.daniel.fittracker.DataAccesObjects;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.daniel.fittracker.DataObjects.ExerciseProgress;

import java.util.List;

/**
 * Created by Daniel on 29.11.2017.
 */
@Dao
public interface ExerciseProgressDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProgress(ExerciseProgress exerciseProgress);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateProgress(ExerciseProgress exerciseProgress);

    @Delete
    void deleteProgress(ExerciseProgress exerciseProgress);

    @Query("SELECT * FROM exerciseprogress INNER JOIN exercise ON exercise.id = exerciseProgress.exerciseID WHERE exercise.exercise = :exercise")
    public List<ExerciseProgress> getAllProgress(String exercise);

    @Query("DELETE FROM exerciseprogress")
    void deleteAllProgress();

}
