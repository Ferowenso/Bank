package com.example.bank.services;

import com.example.bank.models.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    Client addClient(Client client);
    void deleteClient(Long id);
    List<Client> getAllClients();
    Client getClientById(Long id);
    Client editClient(Long id, Client client);

}
