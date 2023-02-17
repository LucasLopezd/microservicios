package com.helipagos.microserviciousuario.servicio;

import com.helipagos.recursosusuario.entidad.Usuario;

public interface IUsuarioServicio {

    Usuario buscarPorUsername(String username);
}
