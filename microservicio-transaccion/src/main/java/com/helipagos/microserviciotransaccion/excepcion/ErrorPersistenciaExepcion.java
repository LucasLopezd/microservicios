package com.helipagos.microserviciotransaccion.excepcion;

public class ErrorPersistenciaExepcion extends RuntimeException{

    public ErrorPersistenciaExepcion(String mensaje){
        super(mensaje);
    }
}
