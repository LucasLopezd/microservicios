package com.helipagos.microserviciosolicitudpago.excepcion;

public class RecursoNoEncontradoExcepcion extends RuntimeException{

    public RecursoNoEncontradoExcepcion(String mensaje){
        super(mensaje);
    }
}
