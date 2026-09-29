package com.cestto.cestto.service;

import com.cestto.cestto.domain.Feira;
import com.cestto.cestto.domain.Produto;
import com.cestto.cestto.domain.StatusFeira;
import com.cestto.cestto.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {this.produtoRepository = produtoRepository;}

    public Produto criarProduto(
            Feira feira,
            String nome,
            int quantidade,
            int quantidadeMinimaAtacado,
            BigDecimal precoAtacado,
            BigDecimal precoVarejo) {

        if (quantidadeMinimaAtacado > 0
                && (precoAtacado == null || precoAtacado.compareTo(BigDecimal.ZERO) <= 0)) {
            return  null;
        }

        Produto novoProduto = new Produto(
                nome,
                quantidade,
                quantidadeMinimaAtacado,
                precoAtacado,
                precoVarejo
        );

        boolean adicionado = feira.adicionarProduto(novoProduto);

        if (!adicionado) {
            return null;
        }

        produtoRepository.save(novoProduto);
        return novoProduto;
    }

    public Produto buscarProduto(Long produtoId, Feira feira) {
        return produtoRepository.findByProdutoIdAndFeira(produtoId, feira).orElse(null);
    }

    public Produto alterarProduto(
            Long produtoId,
            Feira feira,
            String novoNome,
            int novaQuantidade,
            int novaQuantidadeMinimaAtacado,
            BigDecimal novoPrecoAtacado,
            BigDecimal novoPrecoVarejo
    ) {

        if (feira.getStatus() != StatusFeira.EM_ANDAMENTO) {
            return null;
        }

        Produto produto= buscarProduto(produtoId, feira );
        if (produto == null) {
            return null;
        }

        produto.alterarProduto(
                novoNome,
                novaQuantidade,
                novaQuantidadeMinimaAtacado,
                novoPrecoAtacado,
                novoPrecoVarejo
        );

        produtoRepository.save(produto);
        return produto;
    }

    public boolean removerProduto(Long produtoId, Feira feira) {
        Produto produto = buscarProduto(produtoId, feira);

        if (produto == null) {
            return false;
        }

        if (feira.getStatus() != StatusFeira.EM_ANDAMENTO){
            return false;
        }

        produtoRepository.delete(produto);
        return true;
    }
}
