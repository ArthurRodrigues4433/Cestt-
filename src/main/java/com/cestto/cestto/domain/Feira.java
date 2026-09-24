package com.cestto.cestto.domain;

public class Feira {
    private Long id;
    private String nome;
    private String supermercado;
    private StatusFeira status;

    public Feira(Long id, String nome, String supermercado) {
        this.id = id;
        this.nome = nome;
        this.supermercado = supermercado;
        this.status = StatusFeira.EM_ANDAMENTO;
    }

    public String  getNome() {
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
}
