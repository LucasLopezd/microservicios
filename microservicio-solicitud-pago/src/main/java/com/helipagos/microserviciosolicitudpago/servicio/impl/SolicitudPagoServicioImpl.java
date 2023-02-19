package com.helipagos.microserviciosolicitudpago.servicio.impl;

import com.helipagos.microserviciosolicitudpago.dto.CrearSolicitudPagoDto;
import com.helipagos.microserviciosolicitudpago.dto.ModificarSolicitudPagoDto;
import com.helipagos.microserviciosolicitudpago.entidad.SolicitudPago;
import com.helipagos.microserviciosolicitudpago.enums.Estado;
import com.helipagos.microserviciosolicitudpago.excepcion.ErrorPersistenciaExepcion;
import com.helipagos.microserviciosolicitudpago.excepcion.RecursoNoEncontradoExcepcion;
import com.helipagos.microserviciosolicitudpago.repositorio.SolicitudPagoRepositorio;
import com.helipagos.microserviciosolicitudpago.servicio.IEstadoPagoServicio;
import com.helipagos.microserviciosolicitudpago.servicio.ISolicitudPagoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

import static com.helipagos.microserviciosolicitudpago.enums.CodigoError.*;

@Service
@RequiredArgsConstructor
public class SolicitudPagoServicioImpl implements ISolicitudPagoServicio {

    private final SolicitudPagoRepositorio repositorio;
    private final IEstadoPagoServicio estadoServicio;
    private final MessageSource mensajero;

    @Override
    public SolicitudPago crear(CrearSolicitudPagoDto dto) {
        try{
            SolicitudPago solicitud = new SolicitudPago();

            solicitud.setImporte(dto.getImporte());
            solicitud.setDescripcion(dto.getDescripcion());
            solicitud.setEstado(estadoServicio.crear(dto.getImporte()));

            return repositorio.save(solicitud);
        }catch (Exception e){
            throw new ErrorPersistenciaExepcion(
                    mensajero.getMessage(PERSISTENCIA_ERROR.name(), null, Locale.getDefault()));
        }
    }

    @Override
    public SolicitudPago modificarImporte(ModificarSolicitudPagoDto dto) {
        try{
            SolicitudPago solicitud = buscarPorId(dto.getSolicitudId());

            solicitud.setImporte(dto.getImporte());
            estadoServicio.modificarImporte(dto.getImporte(), solicitud.getEstado().getId());

            return repositorio.save(solicitud);
        } catch (Exception e){
            throw new ErrorPersistenciaExepcion(
                    mensajero.getMessage(PERSISTENCIA_ERROR.name(), null, Locale.getDefault()));
        }
    }

    @Override
    public void eliminar(Long id) {
        if(repositorio.existsById(id)){
            repositorio.deleteById(id);
        } else {
            throw new RecursoNoEncontradoExcepcion(
                    mensajero.getMessage(RECURSO_NO_ENCONTRADO.name(), new Object[] { id }, Locale.getDefault()));
        }
    }

    @Override
    public SolicitudPago buscarPorId(Long id) {
        return repositorio.findById(id).orElseThrow(
                () -> new RecursoNoEncontradoExcepcion(
                        mensajero.getMessage(RECURSO_NO_ENCONTRADO.name(), new Object[] { id }, Locale.getDefault())));
    }

    @Override
    public List<SolicitudPago> listar() {
        return repositorio.findAll();
    }

    @Override
    public void actualizarSolicitud(Estado estado, Long id) {
        try{
            SolicitudPago solicitud = buscarPorId(id);
            estadoServicio.actualizarEstado(estado, solicitud.getEstado().getId());
            solicitud.setFecha(LocalDateTime.now());

            repositorio.save(solicitud);
        } catch (Exception e) {
            throw new ErrorPersistenciaExepcion(
                    mensajero.getMessage(PERSISTENCIA_ERROR.name(), null, Locale.getDefault()));
        }
    }
}
