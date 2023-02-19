package com.helipagos.microserviciosolicitudpago.servicio;

import com.helipagos.microserviciosolicitudpago.entidad.EstadoPago;
import com.helipagos.microserviciosolicitudpago.enums.Estado;

public interface IEstadoPagoServicio {

    EstadoPago crear(Double importe);

    void modificarImporte(Double importe, Long id);

    void actualizarEstado(Estado estado, Long id);

    EstadoPago buscarPorId(Long id);
}
