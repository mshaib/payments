package com.intuit.b2b.payment;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.intuit.b2b.kafka.PaymentKafkaProducer;
import com.intuit.b2b.payment.entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class PaymentsController {

    private ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    @Autowired
    private PaymentsService paymentsService;

    @Autowired
    private PaymentKafkaProducer paymentKafkaProducer;

    @PostMapping("/payments")
    public ResponseEntity<String> createPayment(@RequestBody Payment newPayment) {
        try {
            Payment payment = paymentsService.createPayment(newPayment);
            paymentKafkaProducer.produceToPaymentCreatedTopic(payment);
            return ResponseEntity.status(HttpStatus.CREATED).body(ow.writeValueAsString(payment));
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing Payment Value " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create new Payment, Please try again later");
        }
    }

    @GetMapping("/payments/{paymentId}")
    public ResponseEntity<String> getPayment(@PathVariable String paymentId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ow.writeValueAsString(paymentsService.fetchPaymentByPaymentId(paymentId)));
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/payments/{paymentId}")
    public ResponseEntity<String> updatePayment(@PathVariable String paymentId, @RequestBody Payment newPayment) {
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(ow.writeValueAsString(paymentsService.updatePaymentStatusAndRiskPercentage(newPayment, paymentId)));
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
