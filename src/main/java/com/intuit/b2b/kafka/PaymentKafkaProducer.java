package com.intuit.b2b.kafka;

import com.intuit.b2b.payment.entity.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentKafkaProducer {
    private static Logger logger = LoggerFactory.getLogger(PaymentKafkaProducer.class);
    @Autowired
    private KafkaTemplate<String, Payment> kafkaTemplate;

    @Value(value = "${spring.kafka.payment.created.topic}")
    private String paymentCreatedTopic;

    public void produceToPaymentCreatedTopic(Payment payment) {
        logger.info("Producing Payment with PaymentId='" + payment.getPaymentId() + "' to topic='" + paymentCreatedTopic + "'");
        kafkaTemplate.send(paymentCreatedTopic, payment);
    }
}
