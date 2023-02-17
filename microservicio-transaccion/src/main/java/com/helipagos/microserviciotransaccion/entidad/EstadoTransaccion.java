package com.helipagos.microserviciotransaccion.entidad;

import com.helipagos.microserviciotransaccion.enums.Estado;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "estado_transaccion")
@Getter
@Setter
@RequiredArgsConstructor
public class EstadoTransaccion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double importe;

    @Enumerated(EnumType.STRING)
    private Estado descripcion;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "estado_id")
    private List<Transaccion> transacciones;

    public EstadoTransaccion(Double importe, Estado descripcion, Transaccion transaccion){
        this.importe = importe;
        this.descripcion = descripcion;
        this.transacciones = new ArrayList<>();
        transacciones.add(transaccion);
    }
}
