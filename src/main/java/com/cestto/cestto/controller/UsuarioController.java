package com.cestto.cestto.controller;

import com.cestto.cestto.domain.Usuario;
import com.cestto.cestto.dto.UsuarioRequest;
import com.cestto.cestto.dto.UsuarioResponse;
import com.cestto.cestto.service.AuthService;
import com.cestto.cestto.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.cestto.cestto.dto.AuthRequest;

@RestController
public class UsuarioController {

    private final UsuarioService usuarioService;
    private  final AuthService authService;

    public  UsuarioController(UsuarioService usuarioService, AuthService authService) {
        this.usuarioService = usuarioService;
        this.authService = authService;
    }

    @PostMapping("/api/usuarios")
    public UsuarioResponse createUsuario(@RequestBody @Valid UsuarioRequest usuarioRequest) {

        Usuario usuario = usuarioService.cadastrarUsuario(
                usuarioRequest.getNome(),
                usuarioRequest.getEmail(),
                usuarioRequest.getSenha(),
                usuarioRequest.getDataNascimento()
        );

        return new UsuarioResponse(usuario);
    }

    @PostMapping("/api/login")
    public String login(@RequestBody AuthRequest authRequest) {
        return authService.login(authRequest);
    }
}
