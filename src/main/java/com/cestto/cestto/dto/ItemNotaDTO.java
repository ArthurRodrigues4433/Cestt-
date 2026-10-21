package com.cestto.cestto.dto;

import java.math.BigDecimal;

public class ItemNotaDTO {

    private String nome;
    private String codigoProduto;
    private int quantidade;
    private BigDecimal precoUnitario;
    private BigDecimal subtotal;

    public ItemNotaDTO(
            String nome,
            String codigoProduto,
            int quantidade,
            BigDecimal precoUnitario,
            BigDecimal subtotal) {

        this.nome = nome;
        this.codigoProduto = codigoProduto;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.subtotal = subtotal;
    }

    public String getNome() {
        return nome;
    }

    public String getCodigoProduto() {
        return codigoProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }
}