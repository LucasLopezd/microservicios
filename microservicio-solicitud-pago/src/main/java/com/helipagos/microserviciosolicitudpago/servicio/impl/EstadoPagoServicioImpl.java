package com.helipagos.microserviciosolicitudpago.servicio.impl;

import com.helipagos.microserviciosolicitudpago.entidad.EstadoPago;
import static com.helipagos.microserviciosolicitudpago.enums.CodigoError.*;
import com.helipagos.microserviciosolicitudpago.enums.Estado;
import com.helipagos.microserviciosolicitudpago.excepcion.ErrorPersistenciaExepcion;
import com.helipagos.microserviciosolicitudpago.excepcion.RecursoNoEncontradoExcepcion;
import com.helipagos.microserviciosolicitudpago.repositorio.EstadoPagoRepositorio;
import com.helipagos.microserviciosolicitudpago.servicio.IEstadoPagoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class EstadoPagoServicioImpl implements IEstadoPagoServicio {

    private final EstadoPagoRepositorio repositorio;
    private final MessageSource mensajero;

    @Override
    public EstadoPago crear(Double importe) {
        try{
            EstadoPago estadoPago = new EstadoPago();

            estadoPago.setImporte(importe);
            estadoPago.setDescripcion(Estado.NUEVA);

            return repositorio.save(estadoPago);
        }catch (Exception e) {
            throw new ErrorPersistenciaExepcion(
                    mensajero.getMessage(PERSISTENCIA_ERROR.name(), null, Locale.getDefault()));
        }
    }

    @Override
    public void modificarImporte(Double importe, Long id) {
        EstadoPago estadoPago = buscarPorId(id);
        estadoPago.setImporte(importe);
        repositorio.save(estadoPago);
    }

    @Override
    public void actualizarEstado(Estado estado, Long id) {
        EstadoPago estadoPago = buscarPorId(id);
        estadoPago.setDescripcion(estado);
        repositorio.save(estadoPago);
    }

    @Override
    public EstadoPago buscarPorId(Long id) {
        return repositorio.findById(id).orElseThrow(
                () -> new RecursoNoEncontradoExcepcion(
                        mensajero.getMessage(RECURSO_NO_ENCONTRADO.name(), new Object[] { id }, Locale.getDefault())));
    }
}
