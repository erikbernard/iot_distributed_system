package br.com.project.distributed_system.service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import br.com.project.distributed_system.model.DataAnalyst;
import br.com.project.distributed_system.model.Sensor;
import br.com.project.distributed_system.model.SensorDeserializer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Service
public class AnalystConsumerService {
    private final Logger logger = LoggerFactory.getLogger(getClass());


    String topicUnstructuredData = "SENSOR-DATA-UNSTRUCTURED";
    static String topicStructuredData = "STRUCTURED-AND-ANALYZED-SENSOR-DATA";

    private String bootstrapServers="127.0.0.1:9092";
    private String groupIdSensor ="group_id_sensor";
    private String groupIdAnalys ="group_id_analyst";
    private List<Sensor> sensors = new ArrayList<>();


    
    @KafkaListener(topics = "SENSOR-DATA-UNSTRUCTURED", containerFactory = "kafkaListenerJsonFactory", groupId = "group_id_sensor")
    public void consumeMessage(Sensor message) {
        logger.info("\n \n------------------------------Consumed message message"+message);
        sensors.add(message);
    }
    
    @KafkaListener(topics = "STRUCTURED-AND-ANALYZED-SENSOR-DATA", containerFactory = "kafkaListenerJsonFactory", groupId = "group_id_analyst")
    public void consumeDataStruture(Sensor message) {
        logger.info(String.format("\n \n------------------------------Consumed message analyst->  ", message));
    }
    

}
