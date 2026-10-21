package com.cestto.cestto.service;

import com.cestto.cestto.domain.Feira;
import com.cestto.cestto.domain.ItemConferencia;
import com.cestto.cestto.domain.Produto;
import com.cestto.cestto.domain.StatusConferencia;
import com.cestto.cestto.dto.ItemNotaDTO;
import com.cestto.cestto.repository.ItemConferenciaRepository;
import com.cestto.cestto.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ItemConferenciaService {

    private final ItemConferenciaRepository itemConferenciaRepository;

    private final ProdutoRepository produtoRepository;

    public ItemConferenciaService(
            ItemConferenciaRepository itemConferenciaRepository,
            ProdutoRepository produtoRepository) {

        this.itemConferenciaRepository = itemConferenciaRepository;
        this.produtoRepository = produtoRepository;
    }

    public ItemConferencia criarItemConferencia(
            Feira feira,
            String nome,
            String codigoProduto,
            int quantidadeCobrada,
            BigDecimal precoUnitarioCobrado,
            BigDecimal subtotalCobrado) {

        Produto produto = produtoRepository
                .findByCodigoProdutoAndFeira(codigoProduto, feira)
                .orElse(null);

        if (produto == null) {
            return null;
        }

        StatusConferencia status;

        if (produto.getPrecoEscolhido().compareTo(precoUnitarioCobrado) == 0) {
            status = StatusConferencia.CONFERIDO;
        } else {
            status = StatusConferencia.DIVERGENTE;
        }

        ItemConferencia novoItem = new ItemConferencia(
                feira,
                produto,
                nome,
                codigoProduto,
                quantidadeCobrada,
                precoUnitarioCobrado,
                subtotalCobrado,
                status
        );

        itemConferenciaRepository.save(novoItem);

        return novoItem;
    }

    public List<Produto> criarConferencia(Feira feira) {
        return produtoRepository.findByFeira(feira);
    }

    public List<ItemConferencia> conferir(
            Feira feira,
            List<ItemNotaDTO> itensNota) {

        List<Produto> produtosDaFeira =
                produtoRepository.findByFeira(feira);

        List<ItemConferencia> resultado =
                new java.util.ArrayList<>();

        for (Produto produto : produtosDaFeira) {

            ItemNotaDTO itemEncontrado = null;

            for (ItemNotaDTO itemNota : itensNota) {

                if (produto.getCodigoProduto()
                        .equals(itemNota.getCodigoProduto())) {

                    itemEncontrado = itemNota;
                    break;
                }
            }

            if (itemEncontrado == null) {

                ItemConferencia itemAusente =
                        new ItemConferencia(
                                feira,
                                produto,
                                produto.getNome(),
                                produto.getCodigoProduto(),
                                0,
                                null,
                                null,
                                StatusConferencia.AUSENTE_NA_NOTA
                        );

                itemConferenciaRepository.save(itemAusente);
                resultado.add(itemAusente);

            } else {

                StatusConferencia status;

                if (produto.getPrecoEscolhido()
                        .compareTo(itemEncontrado.getPrecoUnitario()) == 0
                        && produto.getQuantidade() == itemEncontrado.getQuantidade()) {

                    status = StatusConferencia.CONFERIDO;

                } else {

                    status = StatusConferencia.DIVERGENTE;
                }

                ItemConferencia item =
                        new ItemConferencia(
                                feira,
                                produto,
                                itemEncontrado.getNome(),
                                itemEncontrado.getCodigoProduto(),
                                itemEncontrado.getQuantidade(),
                                itemEncontrado.getPrecoUnitario(),
                                itemEncontrado.getSubtotal(),
                                status
                        );

                itemConferenciaRepository.save(item);
                resultado.add(item);
            }
        }

        for (ItemNotaDTO itemNota : itensNota) {

            boolean encontradoNaFeira = false;

            for (Produto produto : produtosDaFeira) {

                if (itemNota.getCodigoProduto()
                        .equals(produto.getCodigoProduto())) {

                    encontradoNaFeira = true;
                    break;
                }
            }

            if (!encontradoNaFeira) {

                ItemConferencia itemNaoPlanejado =
                        new ItemConferencia(
                                feira,
                                null,
                                itemNota.getNome(),
                                itemNota.getCodigoProduto(),
                                itemNota.getQuantidade(),
                                itemNota.getPrecoUnitario(),
                                itemNota.getSubtotal(),
                                StatusConferencia.NAO_PLANEJADO
                        );

                itemConferenciaRepository.save(itemNaoPlanejado);

                resultado.add(itemNaoPlanejado);
            }
        }

        return resultado;
    }
}
