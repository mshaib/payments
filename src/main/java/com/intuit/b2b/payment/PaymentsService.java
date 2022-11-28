package com.intuit.b2b.payment;

import com.intuit.b2b.payment.entity.Payment;


public interface PaymentsService {

    Payment createPayment(Payment payment);

    Payment fetchPaymentByPaymentId(String paymentId);

    Payment updatePaymentStatusAndRiskPercentage(Payment payment, String paymentId);
}
