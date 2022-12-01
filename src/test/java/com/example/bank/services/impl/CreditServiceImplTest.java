package com.example.bank.services.impl;

import com.example.bank.models.Client;
import com.example.bank.models.Credit;
import com.example.bank.repositories.ClientRepository;
import com.example.bank.repositories.CreditRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = CreditServiceImpl.class)
class CreditServiceImplTest {

    @Autowired
    CreditServiceImpl creditService;
    @MockBean
    CreditRepository creditRepository;

    @Test
    void addCredit() {
        Credit credit = new Credit(1L, new BigDecimal(100000), new BigDecimal(9.5));
        when(creditRepository.save(credit)).thenReturn(credit);
        Credit savedCredit = creditService.addCredit(credit);
        verify(creditRepository).save(credit);
        assertEquals(savedCredit, credit);
    }

    @Test
    void deleteCredit() {
        Credit credit = new Credit(1L, new BigDecimal(100000), new BigDecimal(9.5));
        when(creditRepository.findById(1L)).thenReturn(Optional.of(credit));
        creditService.deleteCredit(1L);
        verify(creditRepository).delete(credit);
    }

    @Test
    void getAllCredits() {
    }

    @Test
    void getCreditById() {
        Credit credit = new Credit(1L, new BigDecimal(100000), new BigDecimal(9.5));
        when(creditRepository.findById(1L)).thenReturn(Optional.of(credit));
        assertEquals(creditService.getCreditById(1L), credit);
    }
}