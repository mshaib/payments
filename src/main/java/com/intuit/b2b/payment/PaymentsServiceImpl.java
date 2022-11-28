package com.intuit.b2b.payment;

import com.intuit.b2b.configuration.PaymentStatus;
import com.intuit.b2b.payment.entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentsServiceImpl implements PaymentsService {

    @Autowired
    private PaymentsRepo paymentRepository;

    public Payment createPayment(Payment payment) {
        payment.setPaymentId(UUID.randomUUID().toString());
        payment.setPaymentStatus(PaymentStatus.PENDING.toString());
        payment.setPaymentRiskPercentage(-1);
        return paymentRepository.save(payment);
    }

    public Payment fetchPaymentByPaymentId(String paymentId) {
        return paymentRepository.findPaymentByPaymentId(paymentId);
    }

    public Payment updatePaymentStatusAndRiskPercentage(Payment newPayment, String paymentId) {
        Payment currentPayment = fetchPaymentByPaymentId(paymentId);
        if (newPayment.getPaymentStatus() != null && !newPayment.getPaymentStatus().isEmpty()) {
            currentPayment.setPaymentStatus(newPayment.getPaymentStatus());
        }
        currentPayment.setPaymentRiskPercentage(newPayment.getPaymentRiskPercentage());
        return paymentRepository.save(currentPayment);
    }
}
