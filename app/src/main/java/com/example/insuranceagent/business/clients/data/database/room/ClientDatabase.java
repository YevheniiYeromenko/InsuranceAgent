package com.example.insuranceagent.business.clients.data.database.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.insuranceagent.business.clients.data.model.Client;

@Database(entities = {Client.class}, version = 1)
public abstract class ClientDatabase extends RoomDatabase {
    public abstract ClientDao clientDao();
}
