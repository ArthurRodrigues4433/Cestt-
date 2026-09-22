package com.cestto.cestto.domain;

public class Feira {
    private String nome;
    private String supermercado;
    private StatusFeira status;

    public Feira(String nome, String supermercado) {
        this.nome = nome;
        this.supermercado = supermercado;
        this.status = StatusFeira.EM_ANDAMENTO;
    }

    public String  getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSupermercado() {
        return supermercado;
    }

    public void setSupermercado(String supermercado) {
        this.supermercado = supermercado;
    }

    private StatusFeira getStatus() {
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
}
