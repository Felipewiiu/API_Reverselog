package com.company.reverselog.domain.product.dto;

import com.company.reverselog.domain.product.entity.Produto;

public record DadosListagemProdutos(Long id, String nome, String modelo, Integer ncm, Boolean ativo) {
    public DadosListagemProdutos(Produto produto){
        this(
                produto.getId(),
                produto.getNome(),
                produto.getModelo(),
                produto.getNcm(),
                produto.getAtivo());
    }

}
