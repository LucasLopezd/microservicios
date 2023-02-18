package com.helipagos.microserviciosolicitudpago.excepcion.controlador;

import static com.helipagos.microserviciosolicitudpago.respuesta.ConstructorRespuesta.*;

import com.helipagos.microserviciosolicitudpago.dto.ErrorDetalle;
import com.helipagos.microserviciosolicitudpago.excepcion.ErrorPersistenciaExepcion;
import com.helipagos.microserviciosolicitudpago.excepcion.RecursoNoEncontradoExcepcion;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExepcionControlador {

    @ExceptionHandler({ RecursoNoEncontradoExcepcion.class,
            ErrorPersistenciaExepcion.class
    })
    public ResponseEntity<?> conflicto(HttpServletRequest peticion, Exception e){
        return construirRespuesta(HttpStatus.CONFLICT, peticion.getRequestURI(),
                new ErrorDetalle(e.getClass().getName(), e.getMessage()));
    }
}
