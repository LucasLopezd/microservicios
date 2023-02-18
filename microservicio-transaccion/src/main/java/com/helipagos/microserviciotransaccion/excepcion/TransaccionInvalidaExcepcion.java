package com.helipagos.microserviciotransaccion.excepcion;

public class TransaccionInvalidaExcepcion extends RuntimeException{

    public TransaccionInvalidaExcepcion(String mensaje){
        super(mensaje);
    }
}
