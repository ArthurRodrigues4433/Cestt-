package com.cestto.cestto.domain;
import java.math.BigDecimal;

public class Produto {

    private final String nome;
    private final int quantidade;
    private final int quantidadeMinimaAtacado;
    private final BigDecimal precoAtacado;
    private final BigDecimal precoVarejo;

    private BigDecimal precoEscolhido;
    private BigDecimal subtotal;

    public Produto(String nome, int quantidade, int quantidadeMinimaAtacado, BigDecimal precoAtacado, BigDecimal precoVarejo) {
        this.nome =nome;
        this.quantidade =quantidade;
        this.quantidadeMinimaAtacado =quantidadeMinimaAtacado;
        this.precoAtacado =precoAtacado;
        this.precoVarejo =precoVarejo;
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
}
