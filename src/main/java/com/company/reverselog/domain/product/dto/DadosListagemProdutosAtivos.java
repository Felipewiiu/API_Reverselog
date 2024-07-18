package com.company.reverselog.domain.product.dto;

import com.company.reverselog.domain.product.entity.Produto;

public record DadosListagemProdutosAtivos(Long id, String nome, String modelo,  Integer ncm, byte[] image) {
    public DadosListagemProdutosAtivos(Produto produto){
        this(
                produto.getId(),
                produto.getNome(),
                produto.getModelo(),
                produto.getNcm(),
                produto.getImage());
    }
}
