package com.cestto.cestto.domain;
import java.math.BigDecimal;

public class Produto {

    private String nome;
    private int quantidade;
    private  int quantidadeMinimaAtacado;
    private BigDecimal precoAtacado;
    private  BigDecimal precoVarejo;

    private final Long produtoId;
    private BigDecimal precoEscolhido;
    private BigDecimal subtotal;

    public Produto(Long produtoId , String nome, int quantidade, int quantidadeMinimaAtacado, BigDecimal precoAtacado, BigDecimal precoVarejo) {
        this.produtoId = produtoId;
        this.nome =nome;
        this.quantidade =quantidade;
        this.quantidadeMinimaAtacado =quantidadeMinimaAtacado;
        this.precoAtacado =precoAtacado;
        this.precoVarejo =precoVarejo;

        escolherPreco();
        calcularSubtotal();
    }

    public void escolherPreco() {
        if (precoAtacado == null) {
            precoEscolhido = precoVarejo;
        } else if (precoVarejo.compareTo(precoAtacado) == 0) {
            precoEscolhido = precoVarejo;
        } else if (quantidadeMinimaAtacado <= quantidade) {
            precoEscolhido = precoAtacado;
        } else  {
            precoEscolhido = precoVarejo;
        }

    }

    public void calcularSubtotal() {
        subtotal = precoEscolhido.multiply(BigDecimal.valueOf(quantidade));
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public int getQuantidadeMinimaAtacado() {
        return quantidadeMinimaAtacado;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public BigDecimal getPrecoEscolhido() {
        return precoEscolhido;
    }

    public BigDecimal getPrecoVarejo() {
        return precoVarejo;
    }

    public BigDecimal getPrecoAtacado() {
        return precoAtacado;
    }

    public String getNome() {
        return nome;
    }

    public void alterarProduto(
            String novoNome,
            int novaQuantidade,
            int novaQuantidadeMinimaAtacado,
            BigDecimal novoPrecoAtacado,
            BigDecimal novoPrecoVarejo
    ) {
         nome = novoNome;
         quantidade = novaQuantidade;
         quantidadeMinimaAtacado = novaQuantidadeMinimaAtacado;
         precoAtacado = novoPrecoAtacado;
         precoVarejo = novoPrecoVarejo;

         escolherPreco();
         calcularSubtotal();
    }
}
