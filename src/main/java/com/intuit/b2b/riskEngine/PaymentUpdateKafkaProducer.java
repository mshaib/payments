package com.intuit.b2b.riskEngine;

import com.intuit.b2b.payment.entity.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentUpdateKafkaProducer {

    private static Logger logger = LoggerFactory.getLogger(PaymentUpdateKafkaProducer.class);

    @Autowired
    private KafkaTemplate<String, Payment> kafkaTemplate;

    @Value(value = "${spring.kafka.payment.update.topic}")
    private String paymentUpdateTopic;

    public void produceToPaymentUpdateTopic(Payment payment) {
        logger.info("Producing message='" + payment.getPaymentId() + "' to topic='" + paymentUpdateTopic + "'");
        kafkaTemplate.send(paymentUpdateTopic, payment);
    }
}
