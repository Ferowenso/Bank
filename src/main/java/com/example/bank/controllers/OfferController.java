package com.example.bank.controllers;


import com.example.bank.models.Offer;
import com.example.bank.payload.OfferRequest;
import com.example.bank.services.impl.OfferServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/clients/{clientId}/offers")
public class OfferController {

    private final OfferServiceImpl offerService;

    public OfferController(OfferServiceImpl offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/{id}")
    ResponseEntity<Offer> getOfferById(@PathVariable("id") Long id){
        return ResponseEntity.ok(offerService.getOfferById(id));
    }

    @GetMapping("")
    ResponseEntity<List<Offer>> getAllOffers(@PathVariable("clientId") Long clientId){
        return ResponseEntity.ok(offerService.getAllOffers(clientId));
    }

    @PostMapping("")
    ResponseEntity<Offer> addOffer(@RequestBody @Valid OfferRequest offerRequest, @PathVariable("clientId") Long clientId){
        return ResponseEntity.ok(offerService.addOffer(offerRequest, clientId));
    }



}
