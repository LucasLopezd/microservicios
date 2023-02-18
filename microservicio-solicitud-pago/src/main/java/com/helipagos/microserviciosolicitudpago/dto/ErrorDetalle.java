package com.helipagos.microserviciosolicitudpago.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorDetalle {

    private String error;
    private String mensaje;
}
