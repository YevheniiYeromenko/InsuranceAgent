package com.example.insuranceagent.business.clients.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Time;
import java.util.Date;

@Entity
public class Client {
    @PrimaryKey(autoGenerate = true)
    private Integer id;
    private String name;
    private String telNumber;
    private String address;
    private String policyFirstNumber;
    private String policySecondNumber;
    private Integer duration;
    private String startDate;

    public Client() {
    }

    public Client(String name, String telNumber, String address, String policyFirstNumber, String policySecondNumber, Integer duration, String startDate) {
        this.name = name;
        this.telNumber = telNumber;
        this.address = address;
        this.policyFirstNumber = policyFirstNumber;
        this.policySecondNumber = policySecondNumber;
        this.duration = duration;
        this.startDate = startDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPolicyFirstNumber() {
        return policyFirstNumber;
    }

    public void setPolicyFirstNumber(String policyFirstNumber) {
        this.policyFirstNumber = policyFirstNumber;
    }

    public String getPolicySecondNumber() {
        return policySecondNumber;
    }

    public void setPolicySecondNumber(String policySecondNumber) {
        this.policySecondNumber = policySecondNumber;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}
