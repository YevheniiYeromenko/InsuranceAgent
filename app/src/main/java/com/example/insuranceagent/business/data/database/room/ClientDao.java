package com.example.insuranceagent.business.data.database.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.insuranceagent.business.data.model.Client;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface ClientDao {
    @Insert
    void addClient(Client client);

    @Delete
    void deleteClient(Client client);

    @Delete
    Completable deleteClientRx(Client client);

    @Query("SELECT * FROM client")
    List<Client> getAllClients();

    @Query("SELECT * FROM client")
    Flowable<List<Client>> getAllClientsRx();
}
