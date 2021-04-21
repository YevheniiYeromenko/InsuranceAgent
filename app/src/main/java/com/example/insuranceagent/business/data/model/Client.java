package com.example.insuranceagent.business.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Client implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    public Integer id;
    public String name;
    public String telNumber;
    public String address;
    public String policyFirstNumber;
    public String policySecondNumber;
    public String duration;
    public String startDate;

    @Embedded
    public PolicySecond policySecond;

    public Client() {
    }


    protected Client(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        name = in.readString();
        telNumber = in.readString();
        address = in.readString();
        policyFirstNumber = in.readString();
        policySecondNumber = in.readString();
        duration = in.readString();
        startDate = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(name);
        dest.writeString(telNumber);
        dest.writeString(address);
        dest.writeString(policyFirstNumber);
        dest.writeString(policySecondNumber);
        dest.writeString(duration);
        dest.writeString(startDate);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Client> CREATOR = new Creator<Client>() {
        @Override
        public Client createFromParcel(Parcel in) {
            return new Client(in);
        }

        @Override
        public Client[] newArray(int size) {
            return new Client[size];
        }
    };

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", telNumber='" + telNumber + '\'' +
                ", address='" + address + '\'' +
                ", policyFirstNumber='" + policyFirstNumber + '\'' +
                ", policySecondNumber='" + policySecondNumber + '\'' +
                ", duration='" + duration + '\'' +
                ", startDate='" + startDate + '\'' +
                ", policySecond=" + policySecond +
                '}';
    }
}
