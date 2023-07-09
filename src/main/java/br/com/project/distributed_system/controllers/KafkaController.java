package br.com.project.distributed_system.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import br.com.project.distributed_system.model.Sensor;
import br.com.project.distributed_system.service.AnalystConsumerService;
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


    @GetMapping(value = "/publish")
    public String sendMessageToKafkaTopic(@RequestParam("data") String data) {
        var number = Integer.parseInt(data);
        producerService.sensorDataGenerator(number);
        return "Successfully publisher message..!";
    }

    @GetMapping(value = "/Sensors")
    public List<Sensor> sendMessageToKafkaTopic() {
        return consumerService.getSensors();
    }


    // @PostMapping(value = "/publish")
    // public Map<String, Sensor> sendObjectToKafkaTopic(@RequestBody Sensor sensor) {
    //     consumerService.ge;

    //     Map<String, Sensor> map = new HashMap<>();
    //     // map.put("message", "Successfully publisher .!");
    //     map.put("payload", sensor);

    //     return map;
    // }
}
