package com.helipagos.microserviciotransaccion.repositorio;

import com.helipagos.microserviciotransaccion.entidad.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransaccionRepositorio extends JpaRepository<Transaccion, Long> {
}
