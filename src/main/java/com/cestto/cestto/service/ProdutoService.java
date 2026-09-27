package com.cestto.cestto.service;

import com.cestto.cestto.domain.Feira;
import com.cestto.cestto.domain.Produto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

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
}
