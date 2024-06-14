package com.company.reverselog.domain.product.dto;

import com.company.reverselog.domain.product.entity.Produto;

public record DadosListagemProdutos(Long id, String nome, String modelo, Integer numero_de_serie, Integer ncm, Boolean ativo) {
    public DadosListagemProdutos(Produto produto){
        this(
                produto.getId(),
                produto.getNome(),
                produto.getModelo(),
                produto.getNumero_de_serie(),
                produto.getNcm(),
                produto.getAtivo());
    }

}
