package com.cestto.cestto.service;

import com.cestto.cestto.domain.Usuario;
import com.cestto.cestto.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername (String username) {

        Usuario usuario = usuarioRepository
                .findByEmail(username)
                .orElseThrow();

        return UserPrincipal.create(usuario);
    }

    public UserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
}
