package com.helipagos.microserviciosolicitudpago.servicio.impl;

import com.helipagos.microserviciosolicitudpago.dto.CrearSolicitudPagoDto;
import com.helipagos.microserviciosolicitudpago.dto.ModificarSolicitudPagoDto;
import com.helipagos.microserviciosolicitudpago.entidad.EstadoPago;
import com.helipagos.microserviciosolicitudpago.entidad.SolicitudPago;
import com.helipagos.microserviciosolicitudpago.repositorio.SolicitudPagoRepositorio;
import com.helipagos.microserviciosolicitudpago.servicio.IEstadoPagoServicio;
import com.helipagos.microserviciosolicitudpago.servicio.ISolicitudPagoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SolicitudPagoServicioImpl implements ISolicitudPagoServicio {

    private final SolicitudPagoRepositorio repositorio;
    private final IEstadoPagoServicio estadoServicio;

    @Override
    public SolicitudPago crear(CrearSolicitudPagoDto dto) {
        SolicitudPago solicitud = new SolicitudPago();

        solicitud.setImporte(dto.getImporte());
        solicitud.setDescripcion(dto.getDescripcion());

        SolicitudPago nuevaSolicitud = repositorio.save(solicitud);
        estadoServicio.crear(nuevaSolicitud);

        return nuevaSolicitud;
    }

    @Override
    public SolicitudPago modificarImporte(ModificarSolicitudPagoDto dto) {
        SolicitudPago solicitud = buscarPorId(dto.getSolicitudId());

        solicitud.setImporte(dto.getImporte());
        estadoServicio.modificarImporte(dto.getImporte(), dto.getSolicitudId());

        return repositorio.save(solicitud);
    }

    @Override
    public void eliminar(Long id) {
        repositorio.deleteById(id);
    }

    @Override
    public SolicitudPago buscarPorId(Long id) {
        return repositorio.findById(id).orElseThrow(() -> new RuntimeException("Solicitud no encontrada."));
    }

    @Override
    public List<SolicitudPago> listar() {
        return repositorio.findAll();
    }

    @Override
    public void actualizarSolicitud(boolean aprobacion, Long id) {
        estadoServicio.actualizarEstado(aprobacion, id);
        SolicitudPago solicitud = buscarPorId(id);
        solicitud.setFecha(LocalDateTime.now());
        repositorio.save(solicitud);
    }
}
