package com.intuit.b2b.kafka;


import com.intuit.b2b.payment.entity.Payment;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaTopicConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.b2b.consumer.group}")
    private String b2bConsumerGroupId;

    @Value(value = "${spring.kafka.payment.created.topic}")
    private String paymentCreatedTopic;

    @Value(value = "${spring.kafka.payment.update.topic}")
    private String paymentUpdateTopic;

    @Bean
    public NewTopic createPaymentCreatedTopic() {
        return new NewTopic(paymentCreatedTopic, 1, (short) 1);
    }

    @Bean
    public NewTopic createPaymentUpdateTopic() {
        return new NewTopic(paymentUpdateTopic, 1, (short) 1);
    }

    @Bean
    public ProducerFactory<String, Payment> paymentProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class.getName());
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, Payment> paymentKafkaTemplate() {
        return new KafkaTemplate<>(paymentProducerFactory());
    }

    @Bean
    public ConsumerFactory<String, Payment> consumerFactory() {
        Map<String, Object> config = new HashMap<>();

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, b2bConsumerGroupId);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),
                new JsonDeserializer<>(Payment.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Payment> paymentUpdateListener() {
        ConcurrentKafkaListenerContainerFactory<String, Payment> factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

}