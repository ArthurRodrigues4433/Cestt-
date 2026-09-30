package com.cestto.cestto.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Feira {

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String supermercado;

    @Enumerated(EnumType.STRING)
    private StatusFeira status;

    @OneToMany(mappedBy = "feira")
    private List<Produto> produtos = new ArrayList<>();

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Feira() {

    }

    public Feira( String nome, String supermercado) {
        this.nome = nome;
        this.supermercado = supermercado;
        this.status = StatusFeira.EM_ANDAMENTO;
    }

    public String getNome() {
        return nome;
    }

    private void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public String getSupermercado() {
        return supermercado;
    }

    private void setSupermercado(String supermercado) {
        this.supermercado = supermercado;
    }

    public StatusFeira getStatus() {
        return status;
    }

    private void setStatus(StatusFeira status) {
        this.status = status;
    }

    public boolean finalizar(){
        if (this.status == StatusFeira.EM_ANDAMENTO){
            this.setStatus(StatusFeira.FINALIZADA);
            return true;
        }

        return false;
    }

    public boolean cancelar(){
        if (this.status == StatusFeira.EM_ANDAMENTO){
            this.setStatus(StatusFeira.CANCELADA);
            return true;
        }

        return false;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public boolean adicionarProduto(Produto produto) {
        if (this.status == StatusFeira.EM_ANDAMENTO){
           produto.setFeira(this);
           produtos.add(produto);
            return true;
        }

        return false;
    }

    public BigDecimal getTotalPrevisto(){
        BigDecimal total = BigDecimal.ZERO;
        for (Produto produto : produtos) {
            total = total.add(produto.getSubtotal());
        }
        return total;
    }
}
