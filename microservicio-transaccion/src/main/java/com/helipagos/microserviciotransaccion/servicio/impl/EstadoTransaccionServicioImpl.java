package com.helipagos.microserviciotransaccion.servicio.impl;

import com.helipagos.microserviciotransaccion.entidad.EstadoTransaccion;
import com.helipagos.microserviciotransaccion.repositorio.EstadoTransaccionRepositorio;
import com.helipagos.microserviciotransaccion.servicio.IEstadoTransaccionServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EstadoTransaccionServicioImpl implements IEstadoTransaccionServicio {

    private final EstadoTransaccionRepositorio repositorio;

    @Override
    public EstadoTransaccion crear(EstadoTransaccion estadoTransaccion) {
        return repositorio.save(estadoTransaccion);
    }
}
