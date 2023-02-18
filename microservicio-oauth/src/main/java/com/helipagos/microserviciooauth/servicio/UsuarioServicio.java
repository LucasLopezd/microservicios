package com.helipagos.microserviciooauth.servicio;

import com.helipagos.microserviciooauth.feing.UsuarioFeingCliente;
import com.helipagos.recursosusuario.entidad.Usuario;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UsuarioServicio implements UserDetailsService {

    @Autowired
    private UsuarioFeingCliente cliente;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try{
            Usuario usuario = cliente.buscarPorUsername(username);

            if(Objects.isNull(usuario)){
                throw new UsernameNotFoundException("Usuario no encontrado");
            }

            List<GrantedAuthority> roles = usuario.getRoles()
                    .stream()
                    .map(role -> new SimpleGrantedAuthority(role.getName()))
                    .collect(Collectors.toList());

            return new User(usuario.getUsername(), usuario.getPassword(), true, true,
                    true, true, roles);
        }catch (FeignException e){
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
    }
}
