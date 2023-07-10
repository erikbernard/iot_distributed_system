package br.com.project.distributed_system.service;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import br.com.project.distributed_system.model.Sensor;

@Service
public class AnalystProducerService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    String topicStructuredData = "STRUCTURED-AND-ANALYZED-SENSOR-DATA";

    @Autowired
    private KafkaTemplate<String, Sensor> kafkaTemplate;

    public void structureDataSend(Sensor message) {
        var key = UUID.randomUUID().toString();
        kafkaTemplate.send(topicStructuredData, key, message);
        logger.info("\n \n ----------> STRUCTURED-AND-ANALYZED-SENSOR-DATA"+ message);
    }

}
