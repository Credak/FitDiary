package com.example.daniel.fittracker.DataObjects;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;

import com.example.daniel.fittracker.util.DateConverter;

import java.util.Date;

/**
 * Created by Daniel on 29.11.2017.
 */
@Entity(tableName = "weight",
        foreignKeys = {
                @ForeignKey(
                        entity = User.class,
                        parentColumns = "id",
                        childColumns = "userID",
                        onDelete = ForeignKey.CASCADE
                )},
        indices = {@Index(value="id")}
)
public class Weight {
    @PrimaryKey(autoGenerate = true)
    public long id;

    public long userID;
    public long weight;
    public long height;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    private Date date;

    public Weight(long userID, long weight, long height, Date date){
        this.userID = userID;
        this.weight = weight;
        this.height = height;
        this.date = date;
    }

}
