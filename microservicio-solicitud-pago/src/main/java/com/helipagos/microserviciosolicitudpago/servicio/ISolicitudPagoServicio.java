package com.helipagos.microserviciosolicitudpago.servicio;

import com.helipagos.microserviciosolicitudpago.dto.CrearSolicitudPagoDto;
import com.helipagos.microserviciosolicitudpago.dto.ModificarSolicitudPagoDto;
import com.helipagos.microserviciosolicitudpago.entidad.SolicitudPago;

import java.util.List;

public interface ISolicitudPagoServicio {

    SolicitudPago crear(CrearSolicitudPagoDto dto);

    SolicitudPago modificarImporte(ModificarSolicitudPagoDto dto);

    void eliminar(Long id);

    SolicitudPago buscarPorId(Long id);

    List<SolicitudPago> listar();

    void actualizarSolicitud(boolean aprobacion, Long id);
}
