package com.example.insuranceagent.business.clients.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Time;
import java.util.Date;

@Entity
public class Client {
    @PrimaryKey
    private Integer id;
    private String name;
    private String policyFirstNumber;
    private String policySecondNumber;
    private String telNumber;
    private String address;
    private Date startDate;

}
