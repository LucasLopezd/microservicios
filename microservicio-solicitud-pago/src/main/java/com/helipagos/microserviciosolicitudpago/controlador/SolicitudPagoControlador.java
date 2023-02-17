package com.helipagos.microserviciosolicitudpago.controlador;

import com.helipagos.microserviciosolicitudpago.dto.CrearSolicitudPagoDto;
import com.helipagos.microserviciosolicitudpago.dto.ModificarSolicitudPagoDto;
import com.helipagos.microserviciosolicitudpago.entidad.SolicitudPago;
import com.helipagos.microserviciosolicitudpago.servicio.IEstadoPagoServicio;
import com.helipagos.microserviciosolicitudpago.servicio.ISolicitudPagoServicio;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/pagos")
public record SolicitudPagoControlador(ISolicitudPagoServicio servicio) {

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public SolicitudPago crear(@RequestBody CrearSolicitudPagoDto dto){
        return servicio.crear(dto);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public SolicitudPago modificar(@RequestBody ModificarSolicitudPagoDto dto){
        return servicio.modificarImporte(dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void eliminar(@PathVariable Long id){
        servicio.eliminar(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SolicitudPago buscarPorId(@PathVariable Long id){
        return servicio.buscarPorId(id);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<SolicitudPago> listar(){
        return servicio.listar();
    }

    @PutMapping("/{estado}/{id}")
    public void actualizarSolicitudYEstado(@PathVariable Boolean estado, @PathVariable Long id){
        servicio.actualizarSolicitud(estado, id);
    }
}
