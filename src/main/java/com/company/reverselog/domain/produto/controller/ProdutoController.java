package com.company.reverselog.domain.produto.controller;

import com.company.reverselog.domain.produto.dto.*;
import com.company.reverselog.domain.produto.entity.Produto;
import com.company.reverselog.domain.produto.repository.ProdutoRepository;
import com.company.reverselog.domain.produto.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<Page<DadosListagemProdutosAtivos>> listaProdutosAtivos(@PageableDefault(size = 10, sort = {"id"}) Pageable pageable) {
        Page<DadosListagemProdutosAtivos> products = productService.findAllProductsActive(pageable);

        return ResponseEntity.ok(products);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<DadosListagemProdutos>> listaTodosProdutos(@PageableDefault(size = 10, sort = {"id"}) Pageable pageable) {
        Page<DadosListagemProdutos> pageProduct = productService.findAll(pageable);

        return ResponseEntity.ok(pageProduct);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoProduto> cadastraProdutos(@RequestBody @Valid DadosCadastroProdutos dados, UriComponentsBuilder builder) {
        DadosDetalhamentoProduto produto = productService.saveProduct(dados);

        var uri = builder.path("/produtos/{id}").buildAndExpand(produto.id()).toUri();

        return ResponseEntity.created(uri).body(produto);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizaProdudos(@RequestBody @Valid DadosAtualizacaoProduto dados) {
        DadosDetalhamentoProduto productUpdated = productService.updateProduct(dados);

        return ResponseEntity.ok(productUpdated);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletaProduto(@PathVariable Long id) {
        productService.deleteProduct(id);
        
        return ResponseEntity.noContent().build();
    }
}