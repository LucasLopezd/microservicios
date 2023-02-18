package com.helipagos.microserviciosolicitudpago.servicio.impl;

import com.helipagos.microserviciosolicitudpago.dto.CrearSolicitudPagoDto;
import com.helipagos.microserviciosolicitudpago.dto.ModificarSolicitudPagoDto;
import com.helipagos.microserviciosolicitudpago.entidad.SolicitudPago;
import com.helipagos.microserviciosolicitudpago.excepcion.ErrorPersistenciaExepcion;
import com.helipagos.microserviciosolicitudpago.excepcion.RecursoNoEncontradoExcepcion;
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
        try{
            SolicitudPago solicitud = new SolicitudPago();

            solicitud.setImporte(dto.getImporte());
            solicitud.setDescripcion(dto.getDescripcion());
            solicitud.setEstado(estadoServicio.crear(dto.getImporte()));

            return repositorio.save(solicitud);
        }catch (Exception e){
            throw new ErrorPersistenciaExepcion("Hubo un problema en la persistencia de datos.");
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
            throw new ErrorPersistenciaExepcion("Hubo un problema en la persistencia de datos.");
        }
    }

    @Override
    public void eliminar(Long id) {
        if(repositorio.existsById(id)){
            repositorio.deleteById(id);
        } else {
            throw new RecursoNoEncontradoExcepcion("Solicitud no encontrada.");
        }
    }

    @Override
    public SolicitudPago buscarPorId(Long id) {
        return repositorio.findById(id).orElseThrow(
                () -> new RecursoNoEncontradoExcepcion("Solicitud no encontrada."));
    }

    @Override
    public List<SolicitudPago> listar() {
        return repositorio.findAll();
    }

    @Override
    public void actualizarSolicitud(boolean aprobacion, Long id) {
        try{
            SolicitudPago solicitud = buscarPorId(id);
            estadoServicio.actualizarEstado(aprobacion, solicitud.getEstado().getId());
            solicitud.setFecha(LocalDateTime.now());

            repositorio.save(solicitud);
        } catch (Exception e) {
            throw new ErrorPersistenciaExepcion("Hubo un problema en la persistencia de datos.");
        }
    }
}
