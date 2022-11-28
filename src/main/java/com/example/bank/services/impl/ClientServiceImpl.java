package com.example.bank.services.impl;

import com.example.bank.models.Client;
import com.example.bank.repositories.ClientRepository;
import com.example.bank.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Client addClient(Client client) {
        Client newClient = clientRepository.save(client);
        return newClient;
    }

    @Override
    public void deleteClient(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        clientRepository.delete(client);
    }

    @Override
    public List<Client> getAllClients() {
        List<Client> clients = clientRepository.findAll();
        return clients;
    }

    @Override
    public Client getClientById(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return client;
    }

    @Override
    public Client editClient(Long id, Client client) {
        if (clientRepository.findById(id).isPresent()) {
            return clientRepository.save(client);
        }else {
            throw new EntityNotFoundException();
        }
    }

}
