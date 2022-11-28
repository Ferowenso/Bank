package com.example.bank.services.impl;

import com.example.bank.models.Credit;
import com.example.bank.repositories.CreditRepository;
import com.example.bank.services.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CreditServiceImpl implements CreditService {

    @Autowired
    private CreditRepository creditRepository;

    @Override
    public Credit addCredit(Credit credit) {
        Credit newCredit = creditRepository.save(credit);
        return newCredit;
    }

    @Override
    public void deleteCredit(Long id) {
        Credit deleteCredit = creditRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        creditRepository.delete(deleteCredit);
    }

    @Override
    public List<Credit> getAllCredits() {
        List<Credit> credits = creditRepository.findAll();
        return credits;
    }

    @Override
    public Credit getCreditById(Long id) {
        Credit credit = creditRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return credit;
    }

    @Override
    public Credit editCredit(Long id, Credit credit) {
        if (creditRepository.findById(id).isPresent()) {
            return creditRepository.save(credit);
        }else {
            throw new EntityNotFoundException();
        }
    }
}
