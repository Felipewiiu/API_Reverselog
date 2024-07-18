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
    private ProdutoRepository productRepository;

    public Page<DadosListagemProdutosAtivos> findAllProductsActive(Pageable pageable) {
        Page<Produto> page = productRepository.findAllByAtivoTrue(pageable);

        return page.map(this::toDTO);
    }

    public Page<DadosListagemProdutos> findAll(Pageable pageable) {
        Page<Produto> product = productRepository.findAll(pageable);

        return product.map(this::toListagemProductDTO);
    }

    public DadosDetalhamentoProduto saveProduct(DadosCadastroProdutos dados){
        var produto = new Produto(dados);
        productRepository.save(produto);

        return this.toDetalhamentoProductDTO(produto);
    }

    public DadosDetalhamentoProduto updateProduct(DadosAtualizacaoProduto dados){
        Produto produto = productRepository.getReferenceById(dados.id());
        produto.atualizaInformacoesProduto(dados);

        return this.toDetalhamentoProductDTO(produto);
    }

    public void deleteProduct(Long id){
        var produto = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
        produto.excluir(id);
    }

    public DadosDetalhamentoProduto findProductById(Long id) {
        var product = productRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Produto não encontrado na base de dados"));

        return this.toDetalhamentoProductDTO(product);

    }

    private DadosListagemProdutosAtivos toDTO(Produto produto) {
        return new DadosListagemProdutosAtivos(
                produto.getId(),
                produto.getNome(),
                produto.getModelo(),
                produto.getNcm(),
                produto.getImage()
        );
    }



    private DadosListagemProdutos toListagemProductDTO(Produto produto) {
        return new DadosListagemProdutos(
                produto.getId(),
                produto.getNome(),
                produto.getModelo(),
                produto.getNcm(),
                produto.getAtivo()
        );
    }

    private DadosDetalhamentoProduto toDetalhamentoProductDTO(Produto produto) {
        return new DadosDetalhamentoProduto(
                produto.getId(),
                produto.getNome(),
                produto.getModelo(),
                produto.getNcm(),
                produto.getAtivo(),
                produto.getImage()

        );
    }


}
