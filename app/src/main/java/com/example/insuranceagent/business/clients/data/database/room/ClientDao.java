package com.example.insuranceagent.business.clients.data.database.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.insuranceagent.business.clients.data.model.Client;

import java.util.List;

@Dao
public interface ClientDao {
    @Insert
    void addClient(Client client);

    @Delete
    void deleteClient(Client client);

    @Query("SELECT * FROM client")
    List<Client> getAllClients();
}
