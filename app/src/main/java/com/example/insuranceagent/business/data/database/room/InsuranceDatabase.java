package com.example.insuranceagent.business.data.database.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.insuranceagent.business.data.model.Client;
import com.example.insuranceagent.business.data.model.User;

@Database(entities = {Client.class, User.class}, version = 1)
public abstract class InsuranceDatabase extends RoomDatabase {
    public abstract ClientDao clientDao();
    public abstract UserDao userDao();
}
