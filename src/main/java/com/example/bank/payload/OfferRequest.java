package com.example.bank.payload;


import lombok.Data;


import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@Data
public class OfferRequest {

    @NotNull
    private Long creditId;

    @NotNull
    private Long clientId;

    @NotNull
    private BigDecimal creditSum;

    @NotNull
    private Integer period;




}
