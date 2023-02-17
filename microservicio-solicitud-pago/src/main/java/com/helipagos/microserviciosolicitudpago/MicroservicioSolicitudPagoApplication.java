package com.helipagos.microserviciosolicitudpago;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MicroservicioSolicitudPagoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioSolicitudPagoApplication.class, args);
	}

}
