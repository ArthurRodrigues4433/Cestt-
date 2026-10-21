package com.cestto.cestto.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class ItemConferencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Feira feira;

    @ManyToOne
    private Produto produto;

    private String nome;
    private String codigoProduto;
    private int quantidadeCobrada;
    private BigDecimal precoUnitarioCobrado;
    private BigDecimal subtotalCobrado;

    @Enumerated(EnumType.STRING)
    private StatusConferencia status;

    public ItemConferencia(
            Feira feira,
            Produto produto,
            String nome,
            String codigoProduto,
            int quantidadeCobrada,
            BigDecimal precoUnitarioCobrado,
            BigDecimal subtotalCobrado,
            StatusConferencia status) {

        this.feira = feira;
        this.produto = produto;
        this.nome = nome;
        this.codigoProduto = codigoProduto;
        this.quantidadeCobrada = quantidadeCobrada;
        this.precoUnitarioCobrado = precoUnitarioCobrado;
        this.subtotalCobrado = subtotalCobrado;
        this.status = status;
    }

    protected ItemConferencia() {}

    public Long getId() {
        return id;
    }

    public Feira getFeira() {
        return feira;
    }

    public Produto getProduto() {
        return produto;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidadeCobrada() {
        return quantidadeCobrada;
    }

    public BigDecimal getPrecoUnitarioCobrado() {
        return precoUnitarioCobrado;
    }

    public BigDecimal getSubtotalCobrado() {
        return subtotalCobrado;
    }

    public StatusConferencia getStatus() {
        return status;
    }

    public String getCodigoProduto() {
        return codigoProduto;
    }
}