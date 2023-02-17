package com.helipagos.microserviciosolicitudpago.servicio.impl;

import com.helipagos.microserviciosolicitudpago.entidad.EstadoPago;
import com.helipagos.microserviciosolicitudpago.entidad.SolicitudPago;
import com.helipagos.microserviciosolicitudpago.enums.Estado;
import com.helipagos.microserviciosolicitudpago.repositorio.EstadoPagoRepositorio;
import com.helipagos.microserviciosolicitudpago.servicio.IEstadoPagoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EstadoPagoServicioImpl implements IEstadoPagoServicio {

    private final EstadoPagoRepositorio repositorio;

    @Override
    public void crear(SolicitudPago solicitudPago) {
        EstadoPago estadoPago = new EstadoPago();

        estadoPago.setImporte(solicitudPago.getImporte());
        estadoPago.getSolicitudes().add(solicitudPago);

        repositorio.save(estadoPago);
    }

    @Override
    public void modificarImporte(Double importe, Long id) {
        EstadoPago estadoPago = buscarPorId(id);
        estadoPago.setImporte(importe);
        repositorio.save(estadoPago);
    }

    @Override
    public void actualizarEstado(boolean aprobacion, Long id) {
        EstadoPago estadoPago = buscarPorId(id);
        estadoPago.setDescripcion((aprobacion) ? Estado.PAGADA : Estado.RECHAZADA);
        repositorio.save(estadoPago);
    }

    @Override
    public EstadoPago buscarPorId(Long id) {
        return repositorio.findById(id).orElseThrow(
                () -> new RuntimeException("Estado no encontrado."));
    }

    @Override
    public String obtenerEstadoPorId(Long id) {
        return buscarPorId(id).getDescripcion().name();
    }
}
