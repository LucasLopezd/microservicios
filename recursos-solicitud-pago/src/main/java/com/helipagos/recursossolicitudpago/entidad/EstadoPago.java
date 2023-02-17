package com.helipagos.recursossolicitudpago.entidad;


import com.helipagos.recursossolicitudpago.enums.Estado;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "estado_solicitud")
@Getter
@Setter
public class EstadoPago implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double importe;
    @Enumerated(EnumType.STRING)
    private Estado descripcion;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "estado_id")
    private List<SolicitudPago> solicitudes;

    public EstadoPago(){
        this.solicitudes = new ArrayList<>();
        this.descripcion = Estado.NUEVA;
    }

}
