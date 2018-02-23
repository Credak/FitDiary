package com.example.daniel.fittracker.DataObjects;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.example.daniel.fittracker.util.DateConverter;

import java.util.Date;

/**
 * Created by Daniel on 29.11.2017.
 */
@Entity(tableName = "exerciseprogress",
        foreignKeys = {
                @ForeignKey(
                        entity = Exercise.class,
                        parentColumns = "id",
                        childColumns = "exerciseID",
                        onDelete = ForeignKey.CASCADE
                )},
        indices = {@Index(value="id")}
)
public class ExerciseProgress {
    @PrimaryKey(autoGenerate = true)
    public long id;

    public long exerciseID;
    public long weight;
    public long reps;

    public String commentary;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    private Date date;

    public ExerciseProgress(long exerciseID, long weight, long reps, String commentary, Date date){
        this.exerciseID = exerciseID;
        this.weight = weight;
        this.reps = reps;
        this.commentary = commentary;
        this.date = date;
    }

}
