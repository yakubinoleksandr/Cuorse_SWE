package com.yakubin.hospital.service;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PaymentCalculatorTest {

    private final PaymentCalculator calculator = new PaymentCalculator();

    @Test
    void unitShouldCalculateTotalWithoutDiscount() {
        assertEquals(new BigDecimal("500.00"), calculator.calculateTotal(new BigDecimal("500"), BigDecimal.ZERO));
    }

    @Test
    void unitShouldCalculateTotalWithDiscount() {
        assertEquals(new BigDecimal("850.00"), calculator.calculateTotal(new BigDecimal("1000"), new BigDecimal("15")));
    }

    @Test
    void unitShouldCalculateTotalWithFullDiscount() {
        assertEquals(new BigDecimal("0.00"), calculator.calculateTotal(new BigDecimal("300"), new BigDecimal("100")));
    }

    @Test
    void unitShouldRoundMoneyToTwoDecimals() {
        assertEquals(new BigDecimal("88.89"), calculator.calculateTotal(new BigDecimal("100.00"), new BigDecimal("11.111")));
    }

    @Test
    void unitShouldRejectNegativeAmount() {
        assertThrows(IllegalArgumentException.class, () -> calculator.calculateTotal(new BigDecimal("-1"), BigDecimal.ZERO));
    }

    @Test
    void unitShouldRejectInvalidDiscount() {
        assertThrows(IllegalArgumentException.class, () -> calculator.calculateTotal(new BigDecimal("100"), new BigDecimal("101")));
        assertThrows(IllegalArgumentException.class, () -> calculator.calculateTotal(new BigDecimal("100"), new BigDecimal("-1")));
    }

    @Test
    void functionalShouldCalculateDebtForPartlyPaidAppointment() {
        assertEquals(new BigDecimal("250.00"), calculator.calculateDebt(new BigDecimal("700"), new BigDecimal("450")));
    }

    @Test
    void functionalShouldNotReturnNegativeDebtWhenOverpaid() {
        assertEquals(new BigDecimal("0.00"), calculator.calculateDebt(new BigDecimal("700"), new BigDecimal("900")));
    }

    @Test
    void functionalShouldRecognizeFullyPaidAppointment() {
        assertTrue(calculator.isFullyPaid(new BigDecimal("700"), new BigDecimal("700")));
        assertTrue(calculator.isFullyPaid(new BigDecimal("700"), new BigDecimal("900")));
        assertFalse(calculator.isFullyPaid(new BigDecimal("700"), new BigDecimal("699.99")));
    }
}
