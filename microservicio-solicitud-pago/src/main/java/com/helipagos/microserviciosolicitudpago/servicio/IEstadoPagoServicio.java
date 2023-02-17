package com.helipagos.microserviciosolicitudpago.servicio;

import com.helipagos.microserviciosolicitudpago.entidad.EstadoPago;
import com.helipagos.microserviciosolicitudpago.entidad.SolicitudPago;
import com.helipagos.microserviciosolicitudpago.enums.Estado;

public interface IEstadoPagoServicio {

    void crear(SolicitudPago solicitudPago);

    void modificarImporte(Double importe, Long id);

    void actualizarEstado(boolean aprobacion, Long id);
    EstadoPago buscarPorId(Long id);

    String obtenerEstadoPorId(Long id);


}
