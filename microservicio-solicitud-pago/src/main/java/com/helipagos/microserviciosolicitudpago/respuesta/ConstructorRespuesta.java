package com.helipagos.microserviciosolicitudpago.respuesta;

import com.helipagos.microserviciosolicitudpago.dto.ErrorDetalle;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ConstructorRespuesta {

    public static ResponseEntity<?> construirRespuesta(HttpStatus estado, String ruta, Object objeto){
        Map<String, Object> respuesta = new HashMap<>();

        respuesta.put("estado", estado);
        respuesta.put("ruta", ruta);
        respuesta.put((objeto instanceof ErrorDetalle) ? "error" : "respuesta", objeto);

        return ResponseEntity.status(estado).body(respuesta);
    }

    public static ResponseEntity<?> construirRespuesta(HttpStatus estado, Object objeto){
        Map<String, Object> respuesta = new HashMap<>();

        respuesta.put("estado", estado);
        respuesta.put((objeto instanceof ErrorDetalle) ? "error" : "respuesta", objeto);

        return ResponseEntity.status(estado).body(respuesta);
    }
}
