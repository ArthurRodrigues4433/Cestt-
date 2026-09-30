package com.cestto.cestto.service;

import com.cestto.cestto.domain.Usuario;
import com.cestto.cestto.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;


@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    private final PasswordEncoder passwordEncoder;


    public UsuarioService(UsuarioRepository usuarioRepository,  PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean emailExiste(String email){
        return usuarioRepository.findByEmail(email).isPresent();
    }

    public Usuario cadastrarUsuario(String nome, String email, String senha, LocalDate dataNascimento) {

        if (emailExiste(email)) {
            return null;
        }

        String hashPassword = passwordEncoder.encode(senha);

        Usuario usuario = new Usuario(nome, email, hashPassword, dataNascimento);
        usuarioRepository.save(usuario);
        return  usuario;
    }

}
