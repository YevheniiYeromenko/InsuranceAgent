package com.example.insuranceagent.business.clients.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class Client {
    private String name;
    private String polis;
    private String telNumber;

    public Client(String name, String polis, String telNumber) {
        this.name = name;
        this.polis = polis;
        this.telNumber = telNumber;
    }

    public Client() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPolis() {
        return polis;
    }

    public void setPolis(String polis) {
        this.polis = polis;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }
}
