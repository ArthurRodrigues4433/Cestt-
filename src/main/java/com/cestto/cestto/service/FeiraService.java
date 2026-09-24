package com.cestto.cestto.service;

import com.cestto.cestto.domain.StatusFeira;
import org.springframework.stereotype.Service;
import com.cestto.cestto.domain.Feira;

import java.util.ArrayList;
import java.util.List;

@Service
public class FeiraService {

    private final List<Feira> feiras = new ArrayList<>();

    private Long proximoId = 1l;

    public Feira criarFeira(String nome, String supermercado) {

        Feira novaFeira = new Feira(this.proximoId, nome, supermercado);

        for (Feira f :feiras){
            if (f.getStatus() == StatusFeira.EM_ANDAMENTO) {
                return null;
            }
        }

        feiras.add(novaFeira);
        proximoId++;
        return novaFeira;
    }

    public List<Feira> getFeiras() {
        return feiras;
    }

    public Feira getFeiraPorId(Long id) {
        for (Feira f : feiras){
            if (f.getId().equals(id)){
                return f;
            }
        }

        return null;
    }
}