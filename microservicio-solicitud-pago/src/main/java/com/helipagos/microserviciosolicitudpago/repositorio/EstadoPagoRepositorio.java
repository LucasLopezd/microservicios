package com.helipagos.microserviciosolicitudpago.repositorio;

import com.helipagos.microserviciosolicitudpago.entidad.EstadoPago;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoPagoRepositorio extends JpaRepository<EstadoPago, Long> {
}
