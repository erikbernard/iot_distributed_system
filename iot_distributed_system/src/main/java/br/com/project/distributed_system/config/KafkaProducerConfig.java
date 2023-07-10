package br.com.project.distributed_system.config;


import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import br.com.project.distributed_system.model.Sensor;
import br.com.project.distributed_system.model.SensorSerializer;


@Configuration
public class KafkaProducerConfig {

    private String bootstrapServers="127.0.0.1:9092";

    // Json Producer
    @Bean
    public ProducerFactory<String, Sensor> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();

        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.RETRIES_CONFIG, 3);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, SensorSerializer.class);

        return new DefaultKafkaProducerFactory(configProps, new StringSerializer(), new SensorSerializer());
    }

    @Primary
    @Bean
    public KafkaTemplate<String, Sensor> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
