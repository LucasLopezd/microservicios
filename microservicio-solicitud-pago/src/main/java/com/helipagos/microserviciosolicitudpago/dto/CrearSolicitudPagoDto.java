package com.helipagos.microserviciosolicitudpago.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CrearSolicitudPagoDto {

    private Double importe;

    private String descripcion;
}
