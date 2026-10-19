package com.cestto.cestto.service;

import com.cestto.cestto.domain.StatusFeira;
import com.cestto.cestto.domain.Usuario;
import com.cestto.cestto.repository.FeiraRepository;
import com.cestto.cestto.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import com.cestto.cestto.domain.Feira;

import java.util.List;


@Service
public class FeiraService {

    private final FeiraRepository feiraRepository;

    private final UsuarioRepository usuarioRepository;

    public FeiraService(
            FeiraRepository feiraRepository,
            UsuarioRepository usuarioRepository
    ) {
        this.feiraRepository = feiraRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Feira criarFeira(String nome, String supermercado, Long userId) {

        Usuario usuario = usuarioRepository.findById(userId).orElse(null);

        if (usuario == null) {
            return null;
        }

        if (feiraRepository
                .findByUsuarioAndStatus(usuario, StatusFeira.EM_ANDAMENTO)
                .isPresent()) {
            return null;
        }

        Feira novaFeira = new Feira(nome, supermercado);
        novaFeira.setUsuario(usuario);
        feiraRepository.save(novaFeira);

        return novaFeira;
    }

    public List<Feira> listarFeiras(Long userId) {
        return feiraRepository.findByUsuarioId(userId);
    }

    public Feira getFeiraPorId(Long id, Long userId) {
        return feiraRepository.findByIdAndUsuarioId(id, userId)
                .orElse(null);
    }

    public Feira finalizarFeiraPorId(Long id, Long userId) {
        Feira feira = getFeiraPorId(id, userId);

        if (feira != null) {
            boolean finalizada = feira.finalizar();

            if (finalizada) {
                feiraRepository.save(feira);
                return feira;
            }
        }

        return null;
    }

    public Feira cancelarFeiraPorId(Long id, Long userId) {
        Feira feira = getFeiraPorId(id, userId);

        if (feira != null) {
            boolean cancelada = feira.cancelar();

            if (cancelada) {
                feiraRepository.save(feira);
                return feira;
            }
        }

        return null;
    }

    public boolean deletarFeiraPorId(Long id, Long userId) {
        Feira feira = getFeiraPorId(id, userId);

        if (feira != null) {
            if (feira.getStatus() == StatusFeira.CANCELADA) {
                feiraRepository.delete(feira);
                return true;
            }
        }

        return false;
    }
}