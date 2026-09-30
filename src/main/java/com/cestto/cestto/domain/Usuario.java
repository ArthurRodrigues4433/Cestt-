package com.cestto.cestto.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Usuario {

    @OneToMany(mappedBy = "usuario")
    private List<Feira> feiras = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    private String nome;
    private String senha;
    private LocalDate dataNascimento;

    public Usuario() {
    }

    public Usuario(String nome,String email, String senha, LocalDate dataNascimento) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
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

    public String getSenha(){
        return senha;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
}
