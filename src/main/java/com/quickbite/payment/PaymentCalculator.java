package com.quickbite.payment;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Component;

@Component
public class PaymentCalculator {

    private static final BigDecimal FEE_RATE = new BigDecimal("0.02");

    /** Phí giao dịch = 2% số tiền, làm tròn đến đồng. */
    public BigDecimal calculateFee(BigDecimal amount) {
        validate(amount);
        return amount.multiply(FEE_RATE).setScale(0, RoundingMode.HALF_UP);
    }

    /** Tổng tiền khách phải trả = số tiền + phí. */
    public BigDecimal calculateTotal(BigDecimal amount) {
        return amount.add(calculateFee(amount));
    }

    private void validate(BigDecimal amount) {
        if (amount == null || amount.signum() <= 0) {
            throw new IllegalArgumentException("Số tiền phải lớn hơn 0");
        }
    }
}
