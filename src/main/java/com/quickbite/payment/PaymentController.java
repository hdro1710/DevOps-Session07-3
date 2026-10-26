package com.quickbite.payment;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentCalculator calculator;

    public PaymentController(PaymentCalculator calculator) {
        this.calculator = calculator;
    }

    // Ví dụ: GET /api/payments/quote?amount=100000
    @GetMapping("/quote")
    public Map<String, Object> quote(@RequestParam BigDecimal amount) {
        return Map.of(
                "amount", amount,
                "fee", calculator.calculateFee(amount),
                "total", calculator.calculateTotal(amount));
    }
}
