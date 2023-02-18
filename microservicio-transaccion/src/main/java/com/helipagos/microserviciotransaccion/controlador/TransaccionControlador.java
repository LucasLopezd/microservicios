package com.helipagos.microserviciotransaccion.controlador;

import com.helipagos.microserviciotransaccion.dto.TransaccionDto;
import static com.helipagos.microserviciotransaccion.respuesta.ConstructorRespuesta.*;
import com.helipagos.microserviciotransaccion.servicio.ITransaccionServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transacciones")
public record TransaccionControlador(ITransaccionServicio servicio) {

    @PostMapping("/pagar")
    public ResponseEntity<?> realizarPago(@RequestBody TransaccionDto dto){
        return construirRespuesta(HttpStatus.OK, servicio.realizarPago(dto));
    }

    @PostMapping("/rechazar/{id}")
    public ResponseEntity<?> rechazarPago(@PathVariable Long id){
        return construirRespuesta(HttpStatus.OK, servicio.rechazarPago(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        return construirRespuesta(HttpStatus.OK, servicio.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<?> listar(){
        return construirRespuesta(HttpStatus.OK, servicio.listar());
    }
}
