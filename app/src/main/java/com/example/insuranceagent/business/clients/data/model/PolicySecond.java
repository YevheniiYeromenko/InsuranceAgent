package com.example.insuranceagent.business.clients.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class PolicySecond {
    @PrimaryKey(autoGenerate = true)
    public Integer id;

    public String secondPolicyNumber;
    public String price;
    public String prgADob;
    public String prgADTraffic;
    public String prgPI;
    public String prgPITraffic;
    public String prgBBB;
    public String prgBI;
    public String prgH;
    public String prgS;
    public String prgC;
    public String prgFC;
    public String prgFCmonth;
    public String prgFCday;
    public String prgCFB;
    public String prgCFBhospital;
    public String prgCFBreanimation;
    public String prgCI;

    public boolean as;
    public String prgCI_1_7_32;
    public String prgCIDuration;
    public String prgFCDuration;
    public String prgCFBDuration;

}
