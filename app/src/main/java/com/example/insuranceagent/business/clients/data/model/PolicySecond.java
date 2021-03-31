package com.example.insuranceagent.business.clients.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class PolicySecond {
    @PrimaryKey(autoGenerate = true)
    public Integer id;

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
    public String prgFCdiagnosis;
    public String prgFCmonth;
    public String prgFCday;
    public String prgCFBdeath;
    public String prgCFBhospital;
    public String prgCFBreanimation;
    public String prgCIdiagnosis;

    public boolean as;
    public String prgCI_1_7_32;
    public String prgCIDuration;
    public String prgCFBDuration;

    @Override
    public String toString() {
        return "PolicySecond{" +
                "id=" + id +
                ", price='" + price + '\'' +
                ", prgADob='" + prgADob + '\'' +
                ", prgADTraffic='" + prgADTraffic + '\'' +
                ", prgPI='" + prgPI + '\'' +
                ", prgPITraffic='" + prgPITraffic + '\'' +
                ", prgBBB='" + prgBBB + '\'' +
                ", prgBI='" + prgBI + '\'' +
                ", prgH='" + prgH + '\'' +
                ", prgS='" + prgS + '\'' +
                ", prgC='" + prgC + '\'' +
                ", prgFCdiagnosis='" + prgFCdiagnosis + '\'' +
                ", prgFCmonth='" + prgFCmonth + '\'' +
                ", prgFCday='" + prgFCday + '\'' +
                ", prgCFBdeath='" + prgCFBdeath + '\'' +
                ", prgCFBhospital='" + prgCFBhospital + '\'' +
                ", prgCFBreanimation='" + prgCFBreanimation + '\'' +
                ", prgCIdiagnosis='" + prgCIdiagnosis + '\'' +
                ", as=" + as +
                ", prgCI_1_7_32='" + prgCI_1_7_32 + '\'' +
                ", prgCIDuration='" + prgCIDuration + '\'' +
                ", prgCFBDuration='" + prgCFBDuration + '\'' +
                '}';
    }
}
