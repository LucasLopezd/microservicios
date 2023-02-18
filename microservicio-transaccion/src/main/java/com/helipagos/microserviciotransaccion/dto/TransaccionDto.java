package com.helipagos.microserviciotransaccion.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransaccionDto {

    private Double importe;

    private Long solicitudId;
}
