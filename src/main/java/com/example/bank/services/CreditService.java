package com.example.bank.services;

import com.example.bank.models.Client;
import com.example.bank.models.Credit;

import java.util.List;

public interface CreditService {

    Credit addCredit(Credit credit);
    void deleteCredit(Long id);
    List<Credit> getAllCredits();
    Credit getCreditById(Long id);
    Credit editCredit(Long id, Credit credit);
}
