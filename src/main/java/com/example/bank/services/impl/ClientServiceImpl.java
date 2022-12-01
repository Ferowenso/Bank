package com.example.bank.services.impl;

import com.example.bank.exceptions.ClientAlreadyExistsException;
import com.example.bank.exceptions.ClientNotFoundException;
import com.example.bank.models.Client;
import com.example.bank.models.Offer;
import com.example.bank.repositories.ClientRepository;
import com.example.bank.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Client addClient(Client client) {
        if (!clientRepository.exists(Example.of(client, ExampleMatcher.matchingAny().withIgnorePaths("id", "fullName")))){
            Client newClient = clientRepository.save(client);
            return newClient;
        }else{
            throw new ClientAlreadyExistsException();
        }

    }

    @Override
    public void deleteClient(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(ClientNotFoundException::new);
        clientRepository.delete(client);
    }

    @Override
    public List<Client> getAllClients() {
        List<Client> clients = clientRepository.findAll();
        return clients;
    }

    @Override
    public List<Offer> getAllOffers(Long id) {
        return clientRepository.findById(id).get().getOffers();
    }

    @Override
    public Client getClientById(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(ClientNotFoundException::new);
        return client;
    }

    @Override
    public Client editClient(Long id, Client client) {
        getClientById(id);  
        return clientRepository.save(client);
    }

}
