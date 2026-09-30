package com.cestto.cestto.dto;

import com.cestto.cestto.domain.Usuario;

import java.time.LocalDate;

public class UsuarioResponse {

    Long id;
    String nome;
    String email;
    LocalDate dataNascimento;

    public UsuarioResponse(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.dataNascimento = usuario.getDataNascimento();
    }


    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
}