package com.company.reverselog.domain.product.dto;

import com.company.reverselog.domain.product.entity.Produto;

import java.util.Arrays;

public record DadosDetalhamentoProduto(Long id, String nome, String modelo, Integer ncm, Boolean ativo, byte[] image) {
    public DadosDetalhamentoProduto(Produto produto){
        this(
                produto.getId(),
                produto.getNome(),
                produto.getModelo(),
                produto.getNcm(),
                produto.getAtivo(),
                Arrays.toString(produto.getImage()).getBytes()
        );
    }
}
