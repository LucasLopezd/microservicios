package com.helipagos.microserviciosolicitudpago.controlador;

import com.helipagos.microserviciosolicitudpago.entidad.EstadoPago;
import com.helipagos.microserviciosolicitudpago.servicio.IEstadoPagoServicio;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/estados")
public record EstadoPagoControlador(IEstadoPagoServicio servicio) {

    @GetMapping("/{id}")
    public String buscarEstadoPorId(@PathVariable Long id){
        return servicio.obtenerEstadoPorId(id);
    }
}
