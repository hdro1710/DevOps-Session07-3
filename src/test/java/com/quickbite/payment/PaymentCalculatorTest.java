package com.quickbite.payment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class PaymentCalculatorTest {

    private final PaymentCalculator calculator = new PaymentCalculator();

    @Test
    void feeIsTwoPercentOfAmount() {
        assertEquals(new BigDecimal("2000"), calculator.calculateFee(new BigDecimal("100000")));
    }

    @Test
    void totalIsAmountPlusFee() {
        assertEquals(new BigDecimal("102000"), calculator.calculateTotal(new BigDecimal("100000")));
    }

    @Test
    void rejectsNonPositiveAmount() {
        assertThrows(IllegalArgumentException.class, () -> calculator.calculateFee(BigDecimal.ZERO));
        assertThrows(IllegalArgumentException.class, () -> calculator.calculateFee(new BigDecimal("-5")));
    }
}
