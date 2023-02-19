package com.helipagos.microserviciotransaccion.repositorio;

import com.helipagos.microserviciotransaccion.entidad.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface TransaccionRepositorio extends JpaRepository<Transaccion, Long> {

    @Transactional(readOnly = true)
    boolean existsBySolicitudPagoId(Long id);
}
