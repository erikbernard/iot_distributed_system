package br.com.project.distributed_system;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DistributedSystemApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(DistributedSystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}

}
