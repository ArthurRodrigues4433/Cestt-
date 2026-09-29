package com.cestto.cestto.domain;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long produtoId;

    private String nome;
    private int quantidade;
    private int quantidadeMinimaAtacado;
    private BigDecimal precoAtacado;
    private BigDecimal precoVarejo;
    private BigDecimal precoEscolhido;
    private BigDecimal subtotal;

    @ManyToOne
    @JoinColumn(name = "feira_id")
    private Feira feira;

    public void setFeira(Feira feira){
        this.feira = feira;
    }

    public Produto() {
    }

    public Produto(String nome, int quantidade, int quantidadeMinimaAtacado,
                   BigDecimal precoAtacado, BigDecimal precoVarejo) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.quantidadeMinimaAtacado = quantidadeMinimaAtacado;
        this.precoAtacado = precoAtacado;
        this.precoVarejo = precoVarejo;

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
