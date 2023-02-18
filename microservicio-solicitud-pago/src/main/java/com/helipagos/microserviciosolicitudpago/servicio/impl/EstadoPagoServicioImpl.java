package com.helipagos.microserviciosolicitudpago.servicio.impl;

import com.helipagos.microserviciosolicitudpago.entidad.EstadoPago;
import com.helipagos.microserviciosolicitudpago.entidad.SolicitudPago;
import com.helipagos.microserviciosolicitudpago.enums.Estado;
import com.helipagos.microserviciosolicitudpago.excepcion.ErrorPersistenciaExepcion;
import com.helipagos.microserviciosolicitudpago.excepcion.RecursoNoEncontradoExcepcion;
import com.helipagos.microserviciosolicitudpago.repositorio.EstadoPagoRepositorio;
import com.helipagos.microserviciosolicitudpago.servicio.IEstadoPagoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EstadoPagoServicioImpl implements IEstadoPagoServicio {

    private final EstadoPagoRepositorio repositorio;

    @Override
    public EstadoPago crear(Double importe) {
        try{
            EstadoPago estadoPago = new EstadoPago();

            estadoPago.setImporte(importe);
            estadoPago.setDescripcion(Estado.NUEVA);

            return repositorio.save(estadoPago);
        }catch (Exception e) {
            throw new ErrorPersistenciaExepcion("Hubo un problema en la persistencia de datos.");
        }
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
                () -> new RecursoNoEncontradoExcepcion("Estado no encontrado."));
    }
}
