package com.company.reverselog.domain.product.service;

import com.company.reverselog.domain.product.dto.*;
import com.company.reverselog.domain.product.entity.Produto;
import com.company.reverselog.domain.product.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProdutoRepository repository;

    public Page<DadosListagemProdutosAtivos> findAllProductsActive(Pageable pageable) {
        Page<Produto> page = repository.findAllByAtivoTrue(pageable);

        return page.map(this::toDTO);
    }

    public Page<DadosListagemProdutos> findAll(Pageable pageable) {
        Page<Produto> product = repository.findAll(pageable);

        return product.map(this::toListagemProductDTO);
    }

    public DadosDetalhamentoProduto saveProduct(DadosCadastroProdutos dados){
        var produto = new Produto(dados);
        repository.save(produto);

        return this.toDetalhamentoProductDTO(produto);
    }

    public DadosDetalhamentoProduto updateProduct(DadosAtualizacaoProduto dados){
        Produto produto = repository.getReferenceById(dados.id());
        produto.atualizaInformacoesProduto(dados);

        return this.toDetalhamentoProductDTO(produto);
    }

    public void deleteProduct(Long id){
        var produto = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
        produto.excluir(id);
    }

    private DadosListagemProdutosAtivos toDTO(Produto produto) {
        return new DadosListagemProdutosAtivos(
                produto.getId(),
                produto.getNome(),
                produto.getModelo(),
                produto.getNumero_de_serie(),
                produto.getNcm()
        );
    }

    private DadosListagemProdutos toListagemProductDTO(Produto produto) {
        return new DadosListagemProdutos(
                produto.getId(),
                produto.getNome(),
                produto.getModelo(),
                produto.getNumero_de_serie(),
                produto.getNcm(),
                produto.getAtivo()
        );
    }

    private DadosDetalhamentoProduto toDetalhamentoProductDTO(Produto produto) {
        return new DadosDetalhamentoProduto(
                produto.getId(),
                produto.getNome(),
                produto.getModelo(),
                produto.getNumero_de_serie(),
                produto.getNcm(),
                produto.getAtivo()

        );
    }

}
