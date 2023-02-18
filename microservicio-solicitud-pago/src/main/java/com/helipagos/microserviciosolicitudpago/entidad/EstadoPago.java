package com.helipagos.microserviciosolicitudpago.entidad;

import com.helipagos.microserviciosolicitudpago.enums.Estado;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "estado_solicitud")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EstadoPago implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double importe;

    @Enumerated(EnumType.STRING)
    private Estado descripcion;
}
