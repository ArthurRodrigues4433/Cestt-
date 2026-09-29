package com.cestto.cestto.service;

import com.cestto.cestto.domain.StatusFeira;
import com.cestto.cestto.repository.FeiraRepository;
import org.springframework.stereotype.Service;
import com.cestto.cestto.domain.Feira;

import java.util.List;


@Service
public class FeiraService {

    private final FeiraRepository feiraRepository;

    public FeiraService(FeiraRepository feiraRepository) {
        this.feiraRepository = feiraRepository;
    }

    public Feira criarFeira(String nome, String supermercado) {

        if (feiraRepository.findByStatus(StatusFeira.EM_ANDAMENTO).isPresent()) {
            return  null;
        }

        Feira novaFeira = new Feira(nome, supermercado);
        feiraRepository.save(novaFeira);

        return novaFeira;
    }

    public List<Feira> getFeiras() {
        return feiraRepository.findAll();
    }

    public Feira getFeiraPorId(Long id) {
        return feiraRepository.findById(id).orElse(null);
    }

    public Feira finalizarFeiraPorId(Long id) {
        Feira feira = getFeiraPorId(id);

        if (feira != null) {
            boolean finalizada = feira.finalizar();
            if (finalizada) {
                feiraRepository.save(feira);
                return feira;
            }
        }

        return  null;
    }

    public Feira cancelarFeiraPorId(Long id) {
        Feira feira = getFeiraPorId(id);
        if (feira != null) {
            boolean cancelada = feira.cancelar();
            if (cancelada){
                feiraRepository.save(feira);
                return feira;
            }
        }

        return  null;
    }

    public boolean deletarFeiraPorId(Long id) {
        Feira feira = getFeiraPorId(id);
        if (feira != null)  {
            if (feira.getStatus() == StatusFeira.CANCELADA){
                feiraRepository.delete(feira);
                return true;
            }
        }

        return  false;
    }
}