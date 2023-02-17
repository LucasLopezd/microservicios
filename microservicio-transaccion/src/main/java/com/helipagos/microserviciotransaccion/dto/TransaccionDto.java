package com.helipagos.microserviciotransaccion.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class TransaccionDto {

    private Double importe;
    private Long solicitudId;
}
