package com.example.bank.services.impl;

import com.example.bank.exceptions.ClientNotFoundException;
import com.example.bank.models.Client;
import com.example.bank.models.Credit;
import com.example.bank.models.Offer;
import com.example.bank.models.Payment;
import com.example.bank.payload.OfferRequest;
import com.example.bank.repositories.ClientRepository;
import com.example.bank.repositories.CreditRepository;
import com.example.bank.repositories.OfferRepository;
import com.example.bank.services.OfferService;
import com.example.bank.utils.Calculation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
public class OfferServiceImpl implements OfferService {

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CreditRepository creditRepository;

    @Override
    public Offer addOffer(OfferRequest offerRequest) {
        Credit credit = creditRepository.findById(offerRequest.getCreditId()).orElseThrow(EntityNotFoundException::new);
        Client client = clientRepository.findById(offerRequest.getClientId()).orElseThrow(EntityNotFoundException::new);
        int period = offerRequest.getPeriod();
        BigDecimal creditLimit = credit.getLimit();
        BigDecimal creditSum = offerRequest.getCreditSum();
        BigDecimal monthlyRate = Calculation.monthlyRate(credit.getRate());
        BigDecimal paymentSum = Calculation.paymentSum(monthlyRate, creditSum);
        LocalDate now = LocalDate.now().plusMonths(1);
        if (creditSum.compareTo(creditLimit) == 1){
            throw new IllegalArgumentException("Credit limit exceeded");
        }else{
            List<Payment> payments = new ArrayList<>();
            Offer offer = new Offer();
            BigDecimal creditBalance = creditSum;
            for (int i = 0; i<period; i++){
                BigDecimal repaymentPercentages = Calculation.repaymentPercentages(monthlyRate, creditBalance);
                BigDecimal repaymentSum = Calculation.repaymentSum(paymentSum, repaymentPercentages);
                Payment payment = new Payment();
                payment.setPaymentDate(now);
                payment.setRepaymentSum(repaymentSum);
                payment.setRepaymentPercentages(repaymentPercentages);
                payment.setPaymentSum(paymentSum);
                now = now.plusMonths(1);
                payments.add(payment);
                creditBalance = creditBalance.subtract(repaymentSum);
            }
            List<Offer> offers = client.getOffers();
            offers.add(offer);
            client.setOffers(offers);
            offer.setCredit(credit);
            offer.setCreditSum(creditSum);
            offer.setPayments(payments);
            offerRepository.save(offer);
            return offer;
        }
    }

    @Override
    public void deleteOffer(Long id) {

    }

    @Override
    public List<Offer> getAllOffers() {
        List<Offer> offers = offerRepository.findAll();
        return offers;
    }

    @Override
    public Offer getOfferById(Long id) {
        Offer offer = offerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return offer;
    }
}
