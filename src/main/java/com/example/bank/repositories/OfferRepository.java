package com.example.bank.repositories;

import com.example.bank.models.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, Long> {

    List<Offer> findAllByClient_Id(Long clientId);

}
