package com.intuit.b2b.riskEngine;

import com.intuit.b2b.configuration.PaymentStatus;
import com.intuit.b2b.payment.entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RiskEngineService {

    @Value(value = "${risk.engine.threshold}")
    private double paymentRiskThreshold;

    @Autowired
    private PaymentUpdateKafkaProducer paymentUpdateKafkaProducer;

    public Boolean isSafePayment(Payment payment) {
        double paymentRiskPercentage = calculatePaymentRisk(payment);
        boolean isSafePayment = paymentRiskPercentage < paymentRiskThreshold;
        updatePaymentRiskParams(payment, paymentRiskPercentage, isSafePayment);
        return isSafePayment;
    }

    private double calculatePaymentRisk(Payment payment) {
        return Math.random();
    }

    private void updatePaymentRiskParams(Payment payment, double paymentRiskPercentage, boolean isSafePayment) {
        String paymentStatus = isSafePayment ? PaymentStatus.APPROVED.toString() : PaymentStatus.REJECTED.toString();
        payment.setPaymentStatus(paymentStatus);
        payment.setPaymentRiskPercentage(paymentRiskPercentage);
        paymentUpdateKafkaProducer.produceToPaymentUpdateTopic(payment);
    }
}
