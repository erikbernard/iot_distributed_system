package br.com.project.distributed_system;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.project.distributed_system.model.DataAnalyst;
import br.com.project.distributed_system.model.Sensor;
import br.com.project.distributed_system.service.AnalystProducerService;
import br.com.project.distributed_system.service.SensorProducerService;

@SpringBootApplication
public class DistributedSystemApplication implements CommandLineRunner {

	private final SensorProducerService sensorProducerService;
	private final AnalystProducerService analystProducerService;

	public DistributedSystemApplication(SensorProducerService sensorProducerService, AnalystProducerService analystProducerService) {
		this.sensorProducerService = sensorProducerService;
		this.analystProducerService = analystProducerService;
	}

	public static void main(String[] args) {
		SpringApplication.run(DistributedSystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// sensorProducerService.sensorDataGenerator(3);
		// var sensors = DataAnalyst.getSensors();

		// for (Sensor sensor : sensors) {
		// 	analystProducerService.structureData(sensor);

		// }
	}

}
