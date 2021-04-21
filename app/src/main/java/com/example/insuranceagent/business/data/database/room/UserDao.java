package com.example.insuranceagent.business.data.database.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.insuranceagent.business.data.model.Client;
import com.example.insuranceagent.business.data.model.User;

import java.util.List;

import io.reactivex.Completable;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable saveUser(User user);

    @Query("SELECT * FROM user")
    LiveData<List<User>> getUser();
}
