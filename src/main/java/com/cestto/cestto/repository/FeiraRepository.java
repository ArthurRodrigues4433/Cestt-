package com.cestto.cestto.repository;

import com.cestto.cestto.domain.Feira;
import com.cestto.cestto.domain.StatusFeira;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FeiraRepository extends JpaRepository<Feira, Long> {

    Optional<Feira> findByStatus(StatusFeira status);

}
