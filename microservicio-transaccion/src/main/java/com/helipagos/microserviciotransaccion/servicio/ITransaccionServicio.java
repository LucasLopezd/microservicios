package com.helipagos.microserviciotransaccion.servicio;

import com.helipagos.microserviciotransaccion.dto.TransaccionDto;
import com.helipagos.microserviciotransaccion.entidad.Transaccion;

import java.util.List;

public interface ITransaccionServicio {

    Transaccion realizarPago(TransaccionDto dto);

    Transaccion rechazarPago(Long id);

    Transaccion buscarPorId(Long id);

    List<Transaccion> listar();
}
