package com.yakubin.hospital.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PaymentCalculator {

    public BigDecimal calculateTotal(BigDecimal baseAmount, BigDecimal discountPercent) {
        validateBaseAmount(baseAmount);
        validateDiscountPercent(discountPercent);

        BigDecimal discount = baseAmount
                .multiply(discountPercent)
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);

        return baseAmount.subtract(discount).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal calculateDebt(BigDecimal totalAmount, BigDecimal paidAmount) {
        validateBaseAmount(totalAmount);
        validateBaseAmount(paidAmount);

        BigDecimal debt = totalAmount.subtract(paidAmount);
        if (debt.compareTo(BigDecimal.ZERO) < 0) {
            return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        }

        return debt.setScale(2, RoundingMode.HALF_UP);
    }

    public boolean isFullyPaid(BigDecimal totalAmount, BigDecimal paidAmount) {
        validateBaseAmount(totalAmount);
        validateBaseAmount(paidAmount);
        return paidAmount.compareTo(totalAmount) >= 0;
    }

    private void validateBaseAmount(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Amount must be zero or positive");
        }
    }

    private void validateDiscountPercent(BigDecimal discountPercent) {
        if (discountPercent == null
                || discountPercent.compareTo(BigDecimal.ZERO) < 0
                || discountPercent.compareTo(BigDecimal.valueOf(100)) > 0) {
            throw new IllegalArgumentException("Discount must be between 0 and 100");
        }
    }
}
