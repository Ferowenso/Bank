package com.example.bank.controllers;


import com.example.bank.models.Offer;
import com.example.bank.payload.OfferRequest;
import com.example.bank.services.impl.ClientServiceImpl;
import com.example.bank.services.impl.OfferServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/offers")
public class OfferController {

    private final OfferServiceImpl offerService;
    private final ClientServiceImpl clientService;

    public OfferController(OfferServiceImpl offerService, ClientServiceImpl clientService) {
        this.offerService = offerService;
        this.clientService = clientService;
    }

    @GetMapping("/{id}")
    ResponseEntity<Offer> getOfferById(@PathVariable("id") Long id){
        return ResponseEntity.ok(offerService.getOfferById(id));
    }

    @GetMapping
    ResponseEntity<List<Offer>> getAllOffers(){
        return ResponseEntity.ok(offerService.getAllOffers());
    }

    @PostMapping
    ResponseEntity<Offer> addOffer(@RequestBody @Valid OfferRequest offerRequest){
        return ResponseEntity.ok(offerService.addOffer(offerRequest));
    }



}
