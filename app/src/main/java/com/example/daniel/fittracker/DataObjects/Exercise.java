package com.example.daniel.fittracker.DataObjects;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Daniel on 29.11.2017.
 */
@Entity(tableName = "exercise",
        foreignKeys = {
            @ForeignKey(
                    entity = User.class,
                    parentColumns = "id",
                    childColumns = "userID",
                    onDelete = ForeignKey.CASCADE
            )},
        indices = {@Index(value="id")}
)

public class Exercise {
    @PrimaryKey(autoGenerate = true)
    public long id;

    public long userID;
    public String exercise;

    public Exercise(long userID,String exercise){
        this.userID = userID;
        this.exercise = exercise;

    }
}
