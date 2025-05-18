package com.example.paymentservice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public Payment processPayment(Long userId, Double amount) {
        PaymentStatus status = amount > 0 ? PaymentStatus.SUCCESS : PaymentStatus.FAILED;
        Payment payment = Payment.builder()
                .userId(userId)
                .amount(amount)
                .status(status)
                .build();
        return paymentRepository.save(payment);
    }

    public List<Payment> getUserPayments(Long userId) {
        return paymentRepository.findByUserId(userId);
    }
}