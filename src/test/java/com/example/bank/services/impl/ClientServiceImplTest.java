package com.example.bank.services.impl;

import com.example.bank.exceptions.ClientAlreadyExistsException;
import com.example.bank.exceptions.ClientNotFoundException;
import com.example.bank.models.Client;
import com.example.bank.repositories.ClientRepository;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = ClientServiceImpl.class)
class ClientServiceImplTest {

    @Autowired
    ClientServiceImpl clientService;
    @MockBean
    ClientRepository clientRepository;

    @Test
    void shouldAddClient() {
        Client client = new Client(1L, "Сергей Сергеевич Сергеев", "test@gmail.com", "1111111111", "1111111111");
        when(clientRepository.save(client)).thenReturn(client);
        Client savedClient = clientService.addClient(client);
        verify(clientRepository).save(client);
        assertEquals(savedClient, client);
    }

    @Test
    void deleteClient(){
        Client client = new Client(1L, "Сергей Сергеевич Сергеев", "test@gmail.com", "1111111111", "1111111111");
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
        clientService.deleteClient(1L);
        verify(clientRepository).delete(client);
    }


    @Test
    void getClient(){
        Client client = new Client(1L, "Сергей Сергеевич Сергеев", "test@gmail.com", "1111111111", "1111111111");
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
        assertEquals(clientService.getClientById(1L), client);
    }

    @Test
    void getEmptyClient(){
        when(clientRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ClientNotFoundException.class, () -> clientService.getClientById(1L));
    }
}