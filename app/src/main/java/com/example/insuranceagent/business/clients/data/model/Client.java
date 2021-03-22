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
    private String policyFirstNumber;
    private String policySecondNumber;
    private String telNumber;
    private String address;
//    private Date startDate;


    public Client() {
    }

    public Client(String name, String policyFirstNumber, String policySecondNumber, String telNumber, String address) {
        this.name = name;
        this.policyFirstNumber = policyFirstNumber;
        this.policySecondNumber = policySecondNumber;
        this.telNumber = telNumber;
        this.address = address;
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
}
