package com.helipagos.microserviciosolicitudpago.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModificarSolicitudPagoDto {

    private Long solicitudId;
    private Double importe;
}
