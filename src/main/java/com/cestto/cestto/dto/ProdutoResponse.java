package com.cestto.cestto.dto;

import com.cestto.cestto.domain.Produto;

import java.math.BigDecimal;

public class ProdutoResponse {

    private Long produtoId;
    private String nome;
    private int  quantidade;
    private int quantidadeMinimaAtacado;
    private BigDecimal precoAtacado;
    private BigDecimal precoVarejo;
    private BigDecimal precoEscolhido;
    private BigDecimal subtotal;

    public ProdutoResponse(Produto produto) {
        this.produtoId = produto.getProdutoId();
        this.nome = produto.getNome();
        this.quantidade = produto.getQuantidade();
        this.quantidadeMinimaAtacado = produto.getQuantidadeMinimaAtacado();
        this.precoAtacado = produto.getPrecoAtacado();
        this.precoVarejo = produto.getPrecoVarejo();
        this.precoEscolhido = produto.getPrecoEscolhido();
        this.subtotal = produto.getSubtotal();
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }


    public int getQuantidadeMinimaAtacado() {
        return quantidadeMinimaAtacado;
    }


    public BigDecimal getPrecoAtacado() {
        return precoAtacado;
    }

    public BigDecimal getPrecoVarejo() {
        return precoVarejo;
    }


    public BigDecimal getPrecoEscolhido() {
        return precoEscolhido;
    }


    public BigDecimal getSubtotal() {
        return subtotal;
    }
}
