package com.helipagos.microservicioapigateway.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
public class SpringSeguridad {

    @Autowired
    private AutenticadorJwtFiltro filtro;

    @Bean
    public SecurityWebFilterChain configuracion(ServerHttpSecurity http){
        return http
                .authorizeExchange()
                .pathMatchers("/oauth/**").permitAll()
                .pathMatchers(HttpMethod.GET,
                        "/api/pagos**", "/api/transacciones**").hasAnyRole("ADMIN", "CLIENTE")
                .pathMatchers(HttpMethod.POST, "/api/transacciones/**").hasRole("CLIENTE")
                .pathMatchers(HttpMethod.POST, "/api/pagos").hasRole("ADMIN")
                .pathMatchers(HttpMethod.PUT, "/api/pagos").hasRole("ADMIN")
                .pathMatchers(HttpMethod.DELETE,"/api/pagos/{id}").hasRole("ADMIN")
                .anyExchange()
                .authenticated()
                .and()
                .addFilterAt(filtro, SecurityWebFiltersOrder.AUTHENTICATION)
                .csrf().disable()
                .build();
    }
}
