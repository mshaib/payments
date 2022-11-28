package com.intuit.b2b.kafka;

import com.intuit.b2b.payment.PaymentsService;
import com.intuit.b2b.payment.entity.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentUpdateKafkaConsumer {
    private static Logger logger = LoggerFactory.getLogger(PaymentUpdateKafkaConsumer.class);
    @Autowired
    private PaymentsService paymentsService;

    @KafkaListener(topics = "${spring.kafka.payment.update.topic}",
            groupId = "${spring.kafka.b2b.consumer.group}",
            containerFactory = "paymentUpdateListener")
    public void consume(Payment payment) {
        logger.info("updating PaymentId=" + payment.getPaymentId() + " Status and risk percentage");
        paymentsService.updatePaymentStatusAndRiskPercentage(payment, payment.getPaymentId());
    }
}
