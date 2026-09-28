package com.cestto.cestto.service;

import com.cestto.cestto.domain.Feira;
import com.cestto.cestto.domain.Produto;
import com.cestto.cestto.domain.StatusFeira;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProdutoService {

    private Long proximoId = 1L;

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
                this.proximoId,
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

        proximoId++;
        return novoProduto;
    }

    public Produto buscarProduto(Feira feira, Long produtoId) {

        Produto produtoEncontrado = null;

        for (Produto produto : feira.getProdutos()) {
            if (produto.getProdutoId().equals(produtoId)) {
                produtoEncontrado = produto;
                break;
            }
        }

        return produtoEncontrado;
    }

    public Produto alterarProduto(
            Feira feira,
            Long produtoId,
            String novoNome,
            int novaQuantidade,
            int novaQuantidadeMinimaAtacado,
            BigDecimal novoPrecoAtacado,
            BigDecimal novoPrecoVarejo
    ) {

        if (feira.getStatus() != StatusFeira.EM_ANDAMENTO) {
            return null;
        }

        Produto produto= buscarProduto(feira, produtoId );
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

        return produto;
    }

    public boolean removerProduto(Feira feira, Long produtoId) {
        if (feira.getStatus() != StatusFeira.EM_ANDAMENTO) {
            return false;
        }

        for (Produto produto : feira.getProdutos()) {
            if (produto.getProdutoId().equals(produtoId)) {
                feira.getProdutos().remove(produto);
                return true;
            }
        }

        return false;
    }
}
