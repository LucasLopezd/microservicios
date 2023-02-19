package com.helipagos.microserviciotransaccion.feing;

import com.helipagos.recursossolicitudpago.entidad.SolicitudPago;
import com.helipagos.recursossolicitudpago.enums.Estado;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "servicio-solicitud-pago")
public interface ISolicitudPagoFeing {

    @GetMapping("/api/pagos/feing/{id}")
    SolicitudPago buscarSolicitudPorId(@PathVariable Long id);

    @PutMapping("/api/pagos/feing/{estado}/{id}")
    void actualizarSolicitudYEstado(@PathVariable Estado estado, @PathVariable Long id);
}
