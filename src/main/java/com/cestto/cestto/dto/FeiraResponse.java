
package com.cestto.cestto.dto;

import com.cestto.cestto.domain.Feira;
import com.cestto.cestto.domain.StatusFeira;

import java.math.BigDecimal;

public class FeiraResponse {

    private Long id;
    private String nome;
    private String supermercado;
    private StatusFeira status;
    private BigDecimal totalPrevisto;

    public FeiraResponse(Feira feira) {
        this.id = feira.getId();
        this.nome = feira.getNome();
        this.supermercado = feira.getSupermercado();
        this.status = feira.getStatus();
        this.totalPrevisto = feira.getTotalPrevisto();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getSupermercado() {
        return supermercado;
    }

    public StatusFeira getStatus() {
        return status;
    }

    public BigDecimal getTotalPrevisto() {
        return totalPrevisto;
    }
}
