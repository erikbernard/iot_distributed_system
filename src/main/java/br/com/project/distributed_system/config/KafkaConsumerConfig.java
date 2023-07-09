package br.com.project.distributed_system.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.KafkaListenerErrorHandler;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import br.com.project.distributed_system.model.Sensor;
import br.com.project.distributed_system.model.SensorDeserializer;

import org.slf4j.Logger;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {
        private final Logger logger = LoggerFactory.getLogger(getClass());

    // @Value("${spring.kafka.consumer.bootstrap-servers}")
    // private String bootstrapServers;

    // @Value("${spring.kafka.consumer.group_id_sensor}")
    // private String groupIdSensor;

    // @Value("${spring.kafka.consumer.group_id_analyst}")
    // private String groupIdAnalys;
    String bootstrapServers="127.0.0.1:9092";
    String groupIdSensor ="group_id_sensor";
    String groupIdAnalys ="group_id_analyst";

    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // Json Consumer
    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    @Bean
    public ConsumerFactory<String, Sensor> consumerFactory() {
       Map<String, Object> config = new HashMap<>();

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, groupIdAnalys);
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        // config.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getCanonicalName());
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, SensorDeserializer.class.getName());
        // JsonDeserializer -> por Sensordeserialize 
        return new DefaultKafkaConsumerFactory(config, new StringDeserializer(), new SensorDeserializer());
    }

    @Bean
    // public <T> ConcurrentKafkaListenerContainerFactory<?, ?> kafkaListenerJsonFactory() {
    public ConcurrentKafkaListenerContainerFactory kafkaListenerJsonFactory() {
        // sensor -> object
        ConcurrentKafkaListenerContainerFactory factory = new ConcurrentKafkaListenerContainerFactory<String, SensorDeserializer>();
        factory.setConsumerFactory(consumerFactory());
        factory.setBatchListener(true);
        return factory;
    }


    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // String Consumer
    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // @Bean
    // public ConsumerFactory<String, String> stringConsumerFactory() {
    //     Map<String, Object> config = new HashMap<>();

    //     config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    //     config.put(ConsumerConfig.GROUP_ID_CONFIG, groupIdSensor);
    //     config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    //     config.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
    //     config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    //     config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

    //     return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), new StringDeserializer());
    // }

    // @Bean
    // public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerStringFactory() {
    //     var factory = new ConcurrentKafkaListenerContainerFactory<String, String>();
    //     factory.setConsumerFactory(stringConsumerFactory());
    //     // factory.setBatchListener(true);
    //     return factory;
    // }

    @Bean
    public KafkaListenerErrorHandler myTopicErrorHandler() {
        return (m, e) -> {
            logger.error("Got an error {}", e.getMessage());
            return "some info about the failure";
        };
    }

}