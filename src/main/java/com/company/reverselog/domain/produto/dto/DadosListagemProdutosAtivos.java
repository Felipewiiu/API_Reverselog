package com.company.reverselog.domain.produto.dto;

import com.company.reverselog.domain.produto.entity.Produto;

public record DadosListagemProdutosAtivos(Long id, String nome, String modelo, Integer numero_de_serie, Integer ncm) {
    public DadosListagemProdutosAtivos(Produto produto){
        this(
                produto.getId(),
                produto.getNome(),
                produto.getModelo(),
                produto.getNumero_de_serie(),
                produto.getNcm());
    }
}
