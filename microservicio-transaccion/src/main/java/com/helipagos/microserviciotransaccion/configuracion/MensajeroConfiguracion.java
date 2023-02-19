package com.helipagos.microserviciotransaccion.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class MensajeroConfiguracion {

    @Bean
    public ResourceBundleMessageSource mensajero(){
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasenames("locale/messages");
        source.setUseCodeAsDefaultMessage(true);

        return source;
    }
}
