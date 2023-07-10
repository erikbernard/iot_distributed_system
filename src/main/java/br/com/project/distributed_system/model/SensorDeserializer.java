package br.com.project.distributed_system.model;

import java.io.IOException;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SensorDeserializer implements Deserializer<Sensor> {

    @Override
    public Sensor deserialize(String topic, byte[] data) {
        try {
			return new ObjectMapper().readValue(data, Sensor.class);
		} catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return null;
	}
}
