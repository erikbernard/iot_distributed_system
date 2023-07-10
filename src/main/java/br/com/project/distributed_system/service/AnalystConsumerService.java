package br.com.project.distributed_system.service;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import br.com.project.distributed_system.model.Sensor;
import lombok.Getter;

@Getter
@Service
public class AnalystConsumerService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private String topicStructuredData = "STRUCTURED-AND-ANALYZED-SENSOR-DATA";
    private List<Sensor> sensors = new ArrayList<>();

    @KafkaListener(topics = "STRUCTURED-AND-ANALYZED-SENSOR-DATA", containerFactory = "kafkaListenerJsonFactory", groupId = "group_id_analyst")
    public void consumeDataStruture(Sensor message) {
        sensors.add(message);
        logger.info(String.format("\n\n------Consumed message analyst->  ", message));
    }

}
