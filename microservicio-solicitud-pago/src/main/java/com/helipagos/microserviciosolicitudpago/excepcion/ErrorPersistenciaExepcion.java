package com.helipagos.microserviciosolicitudpago.excepcion;

public class ErrorPersistenciaExepcion extends RuntimeException{

    public ErrorPersistenciaExepcion(String mensaje){
        super(mensaje);
    }
}
