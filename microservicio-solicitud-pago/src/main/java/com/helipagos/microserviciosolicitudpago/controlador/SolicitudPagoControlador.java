package com.helipagos.microserviciosolicitudpago.controlador;

import com.helipagos.microserviciosolicitudpago.dto.CrearSolicitudPagoDto;
import com.helipagos.microserviciosolicitudpago.dto.ModificarSolicitudPagoDto;
import static com.helipagos.microserviciosolicitudpago.respuesta.ConstructorRespuesta.*;

import com.helipagos.microserviciosolicitudpago.entidad.SolicitudPago;
import com.helipagos.microserviciosolicitudpago.enums.Estado;
import com.helipagos.microserviciosolicitudpago.servicio.ISolicitudPagoServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/pagos")
public record SolicitudPagoControlador(ISolicitudPagoServicio servicio) {

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody CrearSolicitudPagoDto dto){
        return construirRespuesta(HttpStatus.OK, servicio.crear(dto));
    }

    @PutMapping
    public ResponseEntity<?> modificar(@RequestBody ModificarSolicitudPagoDto dto){
        return construirRespuesta(HttpStatus.OK, servicio.modificarImporte(dto));
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id){
        servicio.eliminar(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        return construirRespuesta(HttpStatus.OK, servicio.buscarPorId(id));
    }

    @GetMapping()
    public ResponseEntity<?> listar(){
        return construirRespuesta(HttpStatus.OK, servicio.listar());
    }

    @PutMapping("/feing/{estado}/{id}")
    public void actualizarSolicitudYEstado(@PathVariable Estado estado, @PathVariable Long id){
        servicio.actualizarSolicitud(estado, id);
    }
    @GetMapping("/feing/{id}")
    public SolicitudPago buscarSolicitudPorIdFeing(@PathVariable Long id){
        return servicio.buscarPorId(id);
    }
}
