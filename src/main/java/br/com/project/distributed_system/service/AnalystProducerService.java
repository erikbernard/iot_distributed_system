package br.com.project.distributed_system.service;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import br.com.project.distributed_system.model.DataAnalyst;
import br.com.project.distributed_system.model.Sensor;
import br.com.project.distributed_system.model.SensorDeserializer;

@Service
public class AnalystProducerService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    // @Value("${spring.kafka.topic.analyst}")
    // private String analyst;

    // @Value("${spring.kafka.topic.sensor}")
    // private String topic;

    String topicUnstructuredData = "SENSOR-DATA-UNSTRUCTURED";
    String topicStructuredData = "STRUCTURED-AND-ANALYZED-SENSOR-DATA";

    String bootstrapServers="127.0.0.1:9092";
    String groupIdSensor ="group_id_sensor";
    String groupIdAnalys ="group_id_analyst";


    @Autowired
    private KafkaTemplate<String, Sensor> kafkaTemplate;

    public void structureData(Sensor message) {
        var key = UUID.randomUUID().toString();
        var data = DataAnalyst.getSensors();
        kafkaTemplate.send(topicStructuredData, key, message);
        // kafkaTemplate.flush();
        logger.info("\n \n ----------> STRUCTURED-AND-ANALYZED-SENSOR-DATA"+ data);
    }


}
