package com.example.bank.models;

import lombok.Data;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@Data
@Entity
public class Credit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "limit_")
    @NotNull
    private BigDecimal limit;

    @NotNull
    private BigDecimal rate;

    public Credit(long id, BigDecimal limit , BigDecimal rate) {
    }

    public Credit() {

    }
}
