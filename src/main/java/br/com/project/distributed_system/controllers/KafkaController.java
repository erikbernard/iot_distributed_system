package br.com.project.distributed_system.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.project.distributed_system.model.Sensor;
import br.com.project.distributed_system.service.AnalystConsumerService;
import br.com.project.distributed_system.service.AnalystProducerService;
import br.com.project.distributed_system.service.SensorProducerService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {

    @Autowired
    private final SensorProducerService producerService;
    private final AnalystConsumerService consumerService;
    private final AnalystProducerService analystproducerService;


    @GetMapping(value = "/publish")
    public String sendMessageToKafkaTopic(@RequestParam("data") String data) {
        var number = Integer.parseInt(data);
        producerService.sensorDataGenerator(number);
        return "Successfully publisher";
    }

    @GetMapping(value = "/sensor")
    public List<Sensor> sendMessageToKafkaTopic() {
        return consumerService.getSensors();
    }


    @PostMapping(value = "/sensor")
    public ResponseEntity<Sensor> sendObjectToKafkaTopic(@RequestBody Sensor sensor) {
        analystproducerService.structureDataSend(sensor);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
