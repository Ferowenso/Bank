package com.example.bank.models;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate paymentDate;

    private BigDecimal paymentSum;

    private BigDecimal repaymentSum;

    private BigDecimal repaymentPercentages;
}
