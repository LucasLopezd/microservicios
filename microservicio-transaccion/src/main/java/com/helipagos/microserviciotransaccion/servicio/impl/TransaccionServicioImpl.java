package com.helipagos.microserviciotransaccion.servicio.impl;

import com.helipagos.recursossolicitudpago.entidad.SolicitudPago;
import com.helipagos.microserviciotransaccion.feing.ISolicitudPagoRest;
import com.helipagos.microserviciotransaccion.dto.TransaccionDto;
import com.helipagos.microserviciotransaccion.entidad.EstadoTransaccion;
import com.helipagos.microserviciotransaccion.entidad.Transaccion;
import com.helipagos.microserviciotransaccion.enums.Estado;
import com.helipagos.microserviciotransaccion.repositorio.EstadoTransaccionRepositorio;
import com.helipagos.microserviciotransaccion.repositorio.TransaccionRepositorio;
import com.helipagos.microserviciotransaccion.servicio.ITransaccionServicio;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TransaccionServicioImpl implements ITransaccionServicio {

    private final TransaccionRepositorio repositorioTransaccion;
    private final EstadoTransaccionRepositorio repositorioEstado;
    private final ISolicitudPagoRest clienteFeing;

    @Override
    public Transaccion realizarPago(TransaccionDto dto) {
        SolicitudPago solicitud = clienteFeing.buscarSolicitudPorId(dto.getSolicitudId());
        String estadoSolicitud = clienteFeing.buscarEstadoPorId(dto.getSolicitudId());
        boolean solicitudAprobada = false;
        Transaccion nuevaTransaccion = null;

        if(estadoSolicitud.equals(com.helipagos.recursossolicitudpago.enums.Estado.NUEVA.name())
                && solicitud.getImporte().equals(dto.getImporte())){

            Transaccion transaccion = new Transaccion();
            transaccion.setFecha(LocalDateTime.now());
            transaccion.setImporte(dto.getImporte());
            transaccion.setSolicitudPagoId(dto.getSolicitudId());

            nuevaTransaccion = repositorioTransaccion.save(transaccion);

            repositorioEstado.save(
                    new EstadoTransaccion(dto.getImporte(), Estado.APROBADA, nuevaTransaccion));

            solicitudAprobada = true;
            clienteFeing.actualizarSolicitudYEstado(solicitudAprobada, dto.getSolicitudId());

        } else {
            throw new RuntimeException(
                    "Error: La solicitud no es NUEVA o el importe ingresado no coincide con el importe de la solicitud.");
        }
        return nuevaTransaccion;
    }

    @Override
    public Transaccion rechazarPago(Long id){
        SolicitudPago solicitud = clienteFeing.buscarSolicitudPorId(id);
        String estadoSolicitud = clienteFeing.buscarEstadoPorId(id);
        Transaccion nuevaTransaccion = null;

        if(estadoSolicitud.equals(com.helipagos.recursossolicitudpago.enums.Estado.NUEVA.name())
                && Objects.nonNull(solicitud)){

            Transaccion transaccion = new Transaccion();
            transaccion.setFecha(LocalDateTime.now());
            transaccion.setImporte(solicitud.getImporte());
            transaccion.setSolicitudPagoId(id);

            nuevaTransaccion = repositorioTransaccion.save(transaccion);

            repositorioEstado.save(
                    new EstadoTransaccion(solicitud.getImporte(), Estado.CANCELADA, nuevaTransaccion));

            clienteFeing.actualizarSolicitudYEstado(false, id);
        } else {
            throw new RuntimeException(
                    "Error: La solicitud no es NUEVA o la solicitud no fue encontrada con el ID ingresado");
        }
        return nuevaTransaccion;
    }

    @Override
    public Transaccion buscarPorId(Long id) {
        return repositorioTransaccion.findById(id).orElseThrow(() -> new RuntimeException("Transaccion no encontrada"));
    }

    @Override
    public List<Transaccion> listar() {
        return repositorioTransaccion.findAll();
    }

}
