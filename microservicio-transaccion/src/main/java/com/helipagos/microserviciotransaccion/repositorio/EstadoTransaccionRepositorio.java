package com.helipagos.microserviciotransaccion.repositorio;

import com.helipagos.microserviciotransaccion.entidad.EstadoTransaccion;
import com.helipagos.microserviciotransaccion.entidad.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EstadoTransaccionRepositorio extends JpaRepository<EstadoTransaccion, Long> {
}
