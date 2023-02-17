package com.helipagos.microserviciousuario.repositorio;

import com.helipagos.recursosusuario.entidad.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

    Usuario findByUsername(String username);
}
