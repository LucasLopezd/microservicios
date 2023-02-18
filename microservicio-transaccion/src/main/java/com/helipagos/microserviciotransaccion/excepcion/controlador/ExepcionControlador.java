package com.helipagos.microserviciotransaccion.excepcion.controlador;

import com.helipagos.microserviciotransaccion.dto.ErrorDetalle;
import com.helipagos.microserviciotransaccion.excepcion.ErrorPersistenciaExepcion;
import com.helipagos.microserviciotransaccion.excepcion.RecursoNoEncontradoExcepcion;
import static com.helipagos.microserviciotransaccion.respuesta.ConstructorRespuesta.*;

import com.helipagos.microserviciotransaccion.excepcion.TransaccionInvalidaExcepcion;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExepcionControlador {

    @ExceptionHandler({ RecursoNoEncontradoExcepcion.class,
            ErrorPersistenciaExepcion.class,
            TransaccionInvalidaExcepcion.class
    })
    public ResponseEntity<?> conflicto(HttpServletRequest peticion, Exception e){
        return construirRespuesta(HttpStatus.CONFLICT, peticion.getRequestURI(),
                new ErrorDetalle(e.getClass().getName(), e.getMessage()));
    }
}
