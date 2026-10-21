package com.cestto.cestto.repository;

import com.cestto.cestto.domain.Feira;
import com.cestto.cestto.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    Optional<Produto> findByProdutoIdAndFeira(
            Long produtoId,
            Feira feira
    );

    List<Produto> findByFeira(Feira feira);

    Optional<Produto> findByCodigoProdutoAndFeira(
            String codigoProduto,
            Feira feira
    );
}
