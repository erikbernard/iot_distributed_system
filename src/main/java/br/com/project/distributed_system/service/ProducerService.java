package br.com.project.distributed_system.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import br.com.project.distributed_system.model.Sensor;

@Service
public class ProducerService<T> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    // @Value("${spring.kafka.topic.sensor}")
    // private String topic;

    // @Autowired
    // private KafkaTemplate<String, String> kafkaTemplate;

    // @Autowired
    // private KafkaTemplate<String, Sensor> kafkaTemplateSensor;


    // public void sendMessage(String message) {
    //     logger.info("#### -> Publishing message -> {}", message);
    //     kafkaTemplate.send(topic, message);
    // }


    public void sendSensorMessage(Sensor sensor) {
        logger.info("#### -> Publishing sensor :: {}", sensor);
    //     kafkaTemplateSensor.send(SensorTopic, sensor);
    }
}
