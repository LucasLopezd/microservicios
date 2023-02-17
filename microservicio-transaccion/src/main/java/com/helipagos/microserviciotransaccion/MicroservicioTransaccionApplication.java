package com.helipagos.microserviciotransaccion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class MicroservicioTransaccionApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioTransaccionApplication.class, args);
	}

}
