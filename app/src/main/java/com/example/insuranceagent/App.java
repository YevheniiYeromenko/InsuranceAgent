package com.example.insuranceagent;

import android.app.Application;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.room.Room;

import com.example.insuranceagent.business.data.database.room.InsuranceDatabase;

public class App extends Application {
    private InsuranceDatabase insuranceDatabase;
    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        instance = this;
        if (insuranceDatabase == null) {
            insuranceDatabase = Room.databaseBuilder(this, InsuranceDatabase.class, "insurance_database")
                    .build();
        }

    }

    public static App getInstance() {
        return instance;
    }

    public InsuranceDatabase getInsuranceDatabase() {
        return insuranceDatabase;
    }
}
