package com.cestto.cestto.repository;

import com.cestto.cestto.domain.Feira;
import com.cestto.cestto.domain.StatusFeira;
import com.cestto.cestto.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FeiraRepository extends JpaRepository<Feira, Long> {

    Optional<Feira> findByUsuarioAndStatus(Usuario usuario, StatusFeira status);

    Optional<Feira> findByIdAndUsuarioId(Long id, Long userId);

    List<Feira> findByUsuarioId(Long userId);
}
