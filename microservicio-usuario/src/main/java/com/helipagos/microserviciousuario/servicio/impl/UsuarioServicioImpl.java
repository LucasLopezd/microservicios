package com.helipagos.microserviciousuario.servicio.impl;

import com.helipagos.microserviciousuario.repositorio.UsuarioRepositorio;
import com.helipagos.microserviciousuario.servicio.IUsuarioServicio;
import com.helipagos.recursosusuario.entidad.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServicioImpl implements IUsuarioServicio {

    @Autowired
    private UsuarioRepositorio repositorio;

    @Override
    public Usuario buscarPorUsername(String username) {
        return repositorio.findByUsername(username);
    }
}
