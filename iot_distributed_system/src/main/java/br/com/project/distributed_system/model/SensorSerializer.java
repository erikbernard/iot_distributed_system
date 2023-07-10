package br.com.project.distributed_system.model;

import java.io.IOException;

import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SensorSerializer implements Serializer<Sensor>{

	@Override
	public byte[] serialize(String topic, Sensor data) {
		
		try {
			return new ObjectMapper().writeValueAsBytes(data);
		} catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return null;
	}
}