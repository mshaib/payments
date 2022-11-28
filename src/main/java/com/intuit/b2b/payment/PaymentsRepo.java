package com.intuit.b2b.payment;

import com.intuit.b2b.payment.entity.Payment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PaymentsRepo extends CrudRepository<Payment, Long> {
    Payment findPaymentByPaymentId(String paymentId);
}
