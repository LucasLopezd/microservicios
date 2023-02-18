package com.helipagos.microserviciosolicitudpago.servicio;

import com.helipagos.microserviciosolicitudpago.entidad.EstadoPago;

public interface IEstadoPagoServicio {

    EstadoPago crear(Double importe);

    void modificarImporte(Double importe, Long id);

    void actualizarEstado(boolean aprobacion, Long id);

    EstadoPago buscarPorId(Long id);
}
