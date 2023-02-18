package com.helipagos.recursossolicitudpago.entidad;

import com.helipagos.recursossolicitudpago.enums.Estado;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
