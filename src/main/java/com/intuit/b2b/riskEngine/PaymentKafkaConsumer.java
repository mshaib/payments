package com.intuit.b2b.riskEngine;


import com.intuit.b2b.kafka.PaymentKafkaProducer;
import com.intuit.b2b.payment.entity.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentKafkaConsumer {
    private static Logger logger = LoggerFactory.getLogger(PaymentKafkaProducer.class);
    @Autowired
    private RiskEngineService riskEngineService;

    @KafkaListener(topics = "${spring.kafka.payment.created.topic}",
            groupId = "${spring.kafka.consumer.group}",
            containerFactory = "paymentUpdateListener")
    public void consume(Payment payment) {
        logger.info("Sending payment = " + payment.toString() + " to risk validation");
                riskEngineService.isSafePayment(payment);
    }
}
