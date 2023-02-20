package com.helipagos.microserviciotransaccion.entidad;

import com.helipagos.microserviciotransaccion.enums.Estado;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "estado_transaccion")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EstadoTransaccion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double importe;

    @Enumerated(EnumType.STRING)
    private Estado descripcion;

    public EstadoTransaccion(Double importe, Estado descripcion){
        this.importe = importe;
        this.descripcion = descripcion;
    }
}
