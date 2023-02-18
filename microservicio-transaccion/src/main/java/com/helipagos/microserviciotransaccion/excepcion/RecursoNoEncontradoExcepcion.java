package com.helipagos.microserviciotransaccion.excepcion;

public class RecursoNoEncontradoExcepcion extends RuntimeException{

    public RecursoNoEncontradoExcepcion(String mensaje){
        super(mensaje);
    }
}
