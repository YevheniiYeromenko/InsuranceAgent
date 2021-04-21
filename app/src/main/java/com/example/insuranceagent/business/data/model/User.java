package com.example.insuranceagent.business.data.model;

import android.net.Uri;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey
    private Integer id;
    private String userLogoUri;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserLogoUri() {
        return userLogoUri;
    }

    public void setUserLogoUri(String userLogoUri) {
        this.userLogoUri = userLogoUri;
    }
}
