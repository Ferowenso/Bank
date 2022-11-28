package com.example.bank.services;

import com.example.bank.models.Credit;
import com.example.bank.models.Offer;
import com.example.bank.payload.OfferRequest;

import java.util.List;

public interface OfferService {

    Offer addOffer(OfferRequest offerRequest, Long userId);
    void deleteOffer(Long id);
    List<Offer> getAllOffers(Long clientId);
    Offer getOfferById(Long id);

}
