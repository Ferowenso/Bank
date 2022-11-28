package com.example.bank.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calculation {

    public static BigDecimal paymentSum(BigDecimal monthlyRate, BigDecimal creditSum) {
        return creditSum.multiply(monthlyRate)
                .multiply(monthlyRate
                        .add(BigDecimal.ONE)
                        .pow(12)
                        .divide(monthlyRate.add(BigDecimal.ONE)
                                .pow(12)
                                .subtract(BigDecimal.ONE), 8, RoundingMode.HALF_UP))
                .setScale(2, RoundingMode.HALF_UP);
    }

    public static BigDecimal monthlyRate(BigDecimal yearRate) {
        return yearRate.divide(new BigDecimal(12), 8, RoundingMode.HALF_UP)
                .divide(new BigDecimal(100), 8, RoundingMode.HALF_UP);
    }

    public static BigDecimal repaymentSum(BigDecimal paymentSum, BigDecimal repaymentPercentages) {
        return paymentSum.subtract(repaymentPercentages).setScale(2, RoundingMode.HALF_UP);
    }

    public static BigDecimal repaymentPercentages(BigDecimal monthlyRate, BigDecimal creditBalance) {
        return creditBalance.multiply(monthlyRate).setScale(2, RoundingMode.HALF_UP);
    }
}
