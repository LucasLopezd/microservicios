package com.helipagos.microserviciotransaccion.controlador;

import com.helipagos.microserviciotransaccion.dto.TransaccionDto;
import com.helipagos.microserviciotransaccion.entidad.Transaccion;
import com.helipagos.microserviciotransaccion.servicio.ITransaccionServicio;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transacciones")
public record TransaccionControlador(ITransaccionServicio servicio) {

    @PostMapping("/pagar")
    @ResponseStatus(HttpStatus.OK)
    public Transaccion realizarPago(@RequestBody TransaccionDto dto){
        return servicio.realizarPago(dto);
    }

    @PostMapping("/rechazar/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Transaccion rechazarPago(@PathVariable Long id){
        return servicio.rechazarPago(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Transaccion buscarPorId(@PathVariable Long id){
        return servicio.buscarPorId(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Transaccion> listar(){
        return servicio.listar();
    }
}
