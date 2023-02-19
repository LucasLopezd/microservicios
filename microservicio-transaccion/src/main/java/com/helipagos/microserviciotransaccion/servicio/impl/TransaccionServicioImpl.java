package com.helipagos.microserviciotransaccion.servicio.impl;

import com.helipagos.microserviciotransaccion.entidad.EstadoTransaccion;
import com.helipagos.microserviciotransaccion.excepcion.RecursoNoEncontradoExcepcion;
import com.helipagos.microserviciotransaccion.excepcion.TransaccionInvalidaExcepcion;
import com.helipagos.microserviciotransaccion.feing.ISolicitudPagoFeing;
import com.helipagos.microserviciotransaccion.dto.TransaccionDto;
import com.helipagos.microserviciotransaccion.entidad.Transaccion;
import com.helipagos.microserviciotransaccion.repositorio.TransaccionRepositorio;
import com.helipagos.microserviciotransaccion.servicio.IEstadoTransaccionServicio;
import com.helipagos.microserviciotransaccion.servicio.ITransaccionServicio;

import com.helipagos.recursossolicitudpago.entidad.SolicitudPago;
import com.helipagos.recursossolicitudpago.enums.Estado;
import static com.helipagos.microserviciotransaccion.enums.Estado.*;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransaccionServicioImpl implements ITransaccionServicio {

    private final TransaccionRepositorio repositorioTransaccion;
    private final IEstadoTransaccionServicio estadoServicio;
    private final ISolicitudPagoFeing solicitudFeing;

    @Override
    public Transaccion realizarPago(TransaccionDto dto) {
        try {
            if(repositorioTransaccion.existsBySolicitudPagoId(dto.getSolicitudId())){
                throw new TransaccionInvalidaExcepcion(
                        "Error: ya existe una transacción para el ID de la solicitud ingresada.");
            }

            SolicitudPago solicitud = solicitudFeing.buscarSolicitudPorId(dto.getSolicitudId());
            Transaccion nuevaTransaccion = null;

            if(solicitud.getEstado().getDescripcion().equals(Estado.NUEVA)
                    && solicitud.getImporte().equals(dto.getImporte())){

                EstadoTransaccion nuevoEstadoTransaccion = estadoServicio.crear(
                                            new EstadoTransaccion(dto.getImporte(), APROBADA));

                Transaccion transaccion = new Transaccion();
                transaccion.setFecha(LocalDateTime.now());
                transaccion.setImporte(dto.getImporte());
                transaccion.setSolicitudPagoId(solicitud.getId());
                transaccion.setEstado(nuevoEstadoTransaccion);

                nuevaTransaccion = repositorioTransaccion.save(transaccion);

                solicitudFeing.actualizarSolicitudYEstado(Estado.PAGADA, dto.getSolicitudId());

            } else {
                throw new TransaccionInvalidaExcepcion(
                        "Error: La solicitud no es NUEVA o el importe ingresado no coincide con el importe de la solicitud.");
            }
            return nuevaTransaccion;

        } catch (FeignException e) {
            throw new RecursoNoEncontradoExcepcion("No se ha encontrado la solicitud con el ID ingresado");
        }
    }

    @Override
    public Transaccion rechazarPago(Long id){
        try{
            SolicitudPago solicitud = solicitudFeing.buscarSolicitudPorId(id);
            Transaccion nuevaTransaccion = null;

            if(solicitud.getEstado().getDescripcion().equals(Estado.NUEVA)){

                EstadoTransaccion nuevoEstadoTransaccion = estadoServicio.crear(
                                        new EstadoTransaccion(solicitud.getImporte(), CANCELADA));

                Transaccion transaccion = new Transaccion();
                transaccion.setFecha(LocalDateTime.now());
                transaccion.setImporte(solicitud.getImporte());
                transaccion.setSolicitudPagoId(solicitud.getId());
                transaccion.setEstado(nuevoEstadoTransaccion);

                nuevaTransaccion = repositorioTransaccion.save(transaccion);

                solicitudFeing.actualizarSolicitudYEstado(Estado.RECHAZADA, id);

            } else {
                throw new TransaccionInvalidaExcepcion(
                        "Error: La solicitud no es NUEVA o la solicitud no fue encontrada con el ID ingresado.");
            }
            return nuevaTransaccion;

        } catch (FeignException e){
            throw new RecursoNoEncontradoExcepcion("No se ha encontrado la solicitud con el ID ingresado");
        }
    }

    @Override
    public Transaccion buscarPorId(Long id) {
        return repositorioTransaccion.findById(id).orElseThrow(
                () -> new RecursoNoEncontradoExcepcion("Transaccion no encontrada."));
    }

    @Override
    public List<Transaccion> listar() {
        return repositorioTransaccion.findAll();
    }
}
