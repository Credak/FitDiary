package com.example.daniel.fittracker.DataAccesObjects;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.daniel.fittracker.DataObjects.ExerciseProgress;
import com.example.daniel.fittracker.DataObjects.Weight;

import java.util.List;

/**
 * Created by Daniel on 29.11.2017.
 */
@Dao
public interface WeightDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertWeight(Weight weight);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateWeight(Weight weight);

    @Delete
    void deleteWeight(Weight weight);

    @Query("SELECT * FROM weight INNER JOIN user ON user.id = weight.userID WHERE user.username = :username")
    public List<Weight> getAllWeight(String username);

    @Query("DELETE FROM weight")
    void deleteAllWeight();
}
