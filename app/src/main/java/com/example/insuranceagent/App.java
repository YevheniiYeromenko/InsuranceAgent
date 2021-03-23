package com.example.insuranceagent;

import android.app.Application;

import androidx.room.Room;

import com.example.insuranceagent.business.clients.data.database.room.ClientDatabase;
import com.example.insuranceagent.business.clients.data.model.Client;

public class App extends Application {
    private ClientDatabase clientDatabase;
    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        if (clientDatabase == null) {
            clientDatabase = Room.databaseBuilder(this, ClientDatabase.class, "client_database")
                    .build();
        }

    }

    public static App getInstance() {
        return instance;
    }

    public ClientDatabase getClientDatabase() {
        return clientDatabase;
    }
}
