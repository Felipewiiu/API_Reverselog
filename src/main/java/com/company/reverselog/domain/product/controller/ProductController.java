package com.company.reverselog.domain.product.controller;

import com.company.reverselog.domain.product.dto.*;
import com.company.reverselog.domain.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearer-key")
public class ProductController {

    private final ProductService productService;


    @GetMapping
    @Operation(summary = "Lista todos os produtos ativos no sistema")
    public ResponseEntity<Page<DadosListagemProdutosAtivos>> listaProdutosAtivos(@PageableDefault(size = 10, sort = {"id"}) Pageable pageable) {
        Page<DadosListagemProdutosAtivos> products = productService.findAllProductsActive(pageable);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/all")
    @Operation(summary = "Lista todos os produtos ativos e inativos no sistema")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Page<DadosListagemProdutos>> listaTodosProdutos(@PageableDefault(size = 10, sort = {"id"}) Pageable pageable) {
        Page<DadosListagemProdutos> pageProduct = productService.findAll(pageable);
        return ResponseEntity.ok(pageProduct);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoProduto> findProductByID(@PathVariable Long id){
        DadosDetalhamentoProduto product = productService.findProductById(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping
    @Transactional
    @Secured("ROLE_ADMIN")
    @Operation(summary = "Cadatra um produto no sistema")
    public ResponseEntity<DadosDetalhamentoProduto> cadastraProdutos(@RequestBody @Valid DadosCadastroProdutos dados, UriComponentsBuilder builder) {
        DadosDetalhamentoProduto produto = productService.saveProduct(dados);
        var uri = builder.path("/produtos/{id}").buildAndExpand(produto.id()).toUri();
        return ResponseEntity.created(uri).body(produto);
    }

    @PutMapping
    @Transactional
    @Secured("ROLE_ADMIN")
    @Operation(summary = "Atuliza informação de um produto no sistema")
    public ResponseEntity<DadosDetalhamentoProduto> atualizaProdudos(@RequestBody @Valid DadosAtualizacaoProduto dados) {
        DadosDetalhamentoProduto productUpdated = productService.updateProduct(dados);
        return ResponseEntity.ok(productUpdated);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Secured("ROLE_ADMIN")
    @Operation(summary = "Inativa um cliente no sistema")
    public ResponseEntity<Void> deletaProduto(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
