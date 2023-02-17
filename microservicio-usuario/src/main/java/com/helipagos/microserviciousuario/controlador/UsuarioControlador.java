package com.helipagos.microserviciousuario.controlador;

import com.helipagos.microserviciousuario.servicio.IUsuarioServicio;
import com.helipagos.recursosusuario.entidad.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public record UsuarioControlador(IUsuarioServicio servicio) {

    @GetMapping("/{username}")
    @ResponseStatus(HttpStatus.OK)
    public Usuario buscarPorUsername(@PathVariable String username){
        return servicio.buscarPorUsername(username);
    }
}
