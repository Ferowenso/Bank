package com.example.bank.services.impl;

import com.example.bank.models.Client;
import com.example.bank.models.Offer;
import com.example.bank.payload.OfferRequest;
import com.example.bank.repositories.ClientRepository;
import com.example.bank.repositories.CreditRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OfferServiceImplTest {

    @InjectMocks
    OfferServiceImpl offerService;

    @Mock
    ClientRepository clientRepository;

    @Mock
    CreditRepository creditRepository;

    @Test
    void addOffer() {
        OfferRequest offerRequest = new OfferRequest();
        offerRequest.setPeriod(12);
        offerRequest.setCreditSum(new BigDecimal(100000));
        offerRequest.setCreditId(1L);
        System.out.println(clientRepository.count());
        when(offerService.addOffer(offerRequest, 1l)).thenReturn(new Offer());
    }
}