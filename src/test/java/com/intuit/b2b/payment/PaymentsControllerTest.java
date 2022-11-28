package com.intuit.b2b.payment;

import com.intuit.b2b.kafka.PaymentKafkaProducer;
import com.intuit.b2b.payment.entity.Payment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PaymentsControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private PaymentsService paymentsService;

    @MockBean
    private PaymentKafkaProducer paymentKafkaProducer;


    @Test
    public void createPaymentShouldReturnPaymentIdAndProduceToKafka() {
        Payment mockPayment = new Payment(1,"1111","pending",1.5,800.0,"USD","userId","payeeId","PaymentMethodId");
        when(paymentsService.createPayment(any())).thenReturn(mockPayment);
        doNothing().when(paymentKafkaProducer).produceToPaymentCreatedTopic(any());
        assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/v1/payments",
                mockPayment,String.class)).contains(mockPayment.getPaymentId());
        verify(paymentKafkaProducer, times(1)).produceToPaymentCreatedTopic(any());
    }
}
