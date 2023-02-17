package com.helipagos.microserviciosolicitudpago.repositorio;

import com.helipagos.microserviciosolicitudpago.entidad.SolicitudPago;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolicitudPagoRepositorio extends JpaRepository<SolicitudPago, Long> {
}
