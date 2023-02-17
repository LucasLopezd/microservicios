package com.helipagos.recursossolicitudpago.entidad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "solicitud_pago")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SolicitudPago implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double importe;
    private String descripcion;
    private LocalDateTime fecha;
}
