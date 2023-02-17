package com.helipagos.microserviciotransaccion.feing;

import com.helipagos.recursossolicitudpago.entidad.SolicitudPago;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "servicio-solicitud-pago")
public interface ISolicitudPagoRest {

    @GetMapping("/api/pagos/{id}")
    public SolicitudPago buscarSolicitudPorId(@PathVariable Long id);

    @PutMapping("/api/pagos/{estado}/{id}")
    public void actualizarSolicitudYEstado(@PathVariable Boolean estado, @PathVariable Long id);

    @GetMapping("/api/estados/{id}")
    public String buscarEstadoPorId(@PathVariable Long id);


}
