package com.cestto.cestto.dto;

import java.math.BigDecimal;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public class ProdutoRequest {

    @NotBlank
    private String nome;

    @Positive
    private  int quantidade;

    @PositiveOrZero
    private int quantidadeMinimaAtacado;

    @PositiveOrZero
    private BigDecimal precoAtacado;

    @NotNull
    @Positive
    private BigDecimal precoVarejo;


    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}

    public int getQuantidade() {return quantidade;}

    public void setQuantidade(int quantidade) {this.quantidade = quantidade;}

    public int getQuantidadeMinimaAtacado() {return quantidadeMinimaAtacado;}

    public void setQuantidadeMinimaAtacado(int quantidadeMinimaAtacado) {this.quantidadeMinimaAtacado = quantidadeMinimaAtacado;}

    public BigDecimal getPrecoAtacado() {return precoAtacado;}

    public void setPrecoAtacado(BigDecimal precoAtacado) {this.precoAtacado = precoAtacado;}

    public BigDecimal getPrecoVarejo() {return precoVarejo;}

    public void setPrecoVarejo(BigDecimal precoVarejo){this.precoVarejo = precoVarejo;}
}