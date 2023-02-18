package com.helipagos.microserviciotransaccion.servicio;

import com.helipagos.microserviciotransaccion.entidad.EstadoTransaccion;

public interface IEstadoTransaccionServicio {

    EstadoTransaccion crear(EstadoTransaccion estadoTransaccion);
}
