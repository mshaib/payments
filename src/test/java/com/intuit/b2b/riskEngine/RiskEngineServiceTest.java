package com.intuit.b2b.riskEngine;

import com.intuit.b2b.payment.entity.Payment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RiskEngineServiceTest {

    @Autowired
    private RiskEngineService riskEngineService;

    @MockBean
    private PaymentUpdateKafkaProducer paymentUpdateKafkaProducer;


    @Test
    public void isSafePaymentShouldProducePayemntWithRiskParams(){
        Payment mockPayment = new Payment(1,"1111","pending",1.5,800.0,"USD","userId","payeeId","PaymentMethodId");
        riskEngineService.isSafePayment(mockPayment);
        verify(paymentUpdateKafkaProducer, times(1)).produceToPaymentUpdateTopic(any());
    }
}
