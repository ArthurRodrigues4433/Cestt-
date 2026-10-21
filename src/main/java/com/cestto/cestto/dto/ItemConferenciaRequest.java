package com.cestto.cestto.dto;

import java.math.BigDecimal;

public class ItemConferenciaRequest {

    private String codigoProduto;
    private int quantidadeCobrada;
    private BigDecimal precoUnitarioCobrado;
    private BigDecimal subtotalCobrado;

    public String getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(String codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public int getQuantidadeCobrada() {
        return quantidadeCobrada;
    }

    public void setQuantidadeCobrada(int quantidadeCobrada) {
        this.quantidadeCobrada = quantidadeCobrada;
    }

    public BigDecimal getPrecoUnitarioCobrado() {
        return precoUnitarioCobrado;
    }

    public void setPrecoUnitarioCobrado(BigDecimal precoUnitarioCobrado) {
        this.precoUnitarioCobrado = precoUnitarioCobrado;
    }

    public BigDecimal getSubtotalCobrado() {
        return subtotalCobrado;
    }

    public void setSubtotalCobrado(BigDecimal subtotalCobrado) {
        this.subtotalCobrado = subtotalCobrado;
    }
}