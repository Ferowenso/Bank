package com.example.bank.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;


@Data
@Entity
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    private Client client;

    @NotNull
    @OneToOne
    private Credit credit;

    @NotNull
    private BigDecimal creditSum;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Payment> payments;

}
