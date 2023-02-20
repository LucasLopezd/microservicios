package com.helipagos.microserviciooauth.feing;

import com.helipagos.recursosusuario.entidad.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "servicio-usuario")
public interface UsuarioFeingCliente {

    @GetMapping("/api/usuarios/{username}")
    Usuario buscarPorUsername(@PathVariable String username);
}
