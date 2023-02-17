package com.helipagos.microserviciooauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
@EntityScan({"com.helipagos.recursosusuario.entidad"})
public class MicroservicioOauthApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioOauthApplication.class, args);
	}
}
