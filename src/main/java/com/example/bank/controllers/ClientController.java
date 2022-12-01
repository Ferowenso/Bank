package com.example.bank.controllers;

import com.example.bank.models.Client;
import com.example.bank.services.impl.ClientServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController()
@RequestMapping("/clients")
public class ClientController {

    private final ClientServiceImpl clientService;

    public ClientController(ClientServiceImpl service) {
        this.clientService = service;
    }

    @PostMapping
    ResponseEntity<Client> addClient(@RequestBody @Valid Client newClient) {
        return ResponseEntity.ok().body(clientService.addClient(newClient));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Client> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    ResponseEntity<Client> getClient(@PathVariable Long id) {
        return ResponseEntity.ok().body(clientService.getClientById(id));
    }

    @GetMapping
    ResponseEntity<List<Client>> getAllClients() {
        return ResponseEntity.ok().body(clientService.getAllClients());
    }

    @PutMapping("/{id}")
    ResponseEntity<Client> editClient(@PathVariable Long id, @RequestBody @Valid Client client){
        return ResponseEntity.ok().body(clientService.editClient(id, client));
    }

}
