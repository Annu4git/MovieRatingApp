package org.anurag.microservice_configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class MicroserviceConfigurationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceConfigurationsApplication.class, args);
	}

}
