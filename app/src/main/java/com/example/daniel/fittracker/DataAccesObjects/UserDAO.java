package com.example.daniel.fittracker.DataAccesObjects;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.daniel.fittracker.DataObjects.User;

import java.util.List;

/**
 * Created by Daniel on 29.11.2017.
 */
@Dao
public interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addUser(User user);

    @Query("SELECT * FROM user")
    public List<User> getAllUser();

    @Query("SELECT * FROM user where username = :username")
    public User getUser(String username);

    @Query("SELECT * FROM user where username = :username AND password = :password")
    public User getLoginUser(String username, String password);

    @Query("SELECT * FROM user where username = :username AND email = :email")
    public User checkIfUserExists(String username, String email);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void UpdateUser(User user);

    @Delete
    void DeleteUser(User user);

    @Query("DELETE from user")
    void removeAllUsers();
}
