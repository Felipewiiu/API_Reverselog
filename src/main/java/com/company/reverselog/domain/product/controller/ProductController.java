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
    @Operation(summary = "Lista todos os produtos ativos")
    public ResponseEntity<Page<DadosListagemProdutosAtivos>> listaProdutosAtivos(@PageableDefault(size = 10, sort = {"id"}) Pageable pageable) {
        Page<DadosListagemProdutosAtivos> products = productService.findAllProductsActive(pageable);

        return ResponseEntity.ok(products);
    }

    @GetMapping("/all")
    @Operation(summary = "Lista todos os produtos ativos e inativos")
    @Secured("ROULE_ADMIN")
    public ResponseEntity<Page<DadosListagemProdutos>> listaTodosProdutos(@PageableDefault(size = 10, sort = {"id"}) Pageable pageable) {
        Page<DadosListagemProdutos> pageProduct = productService.findAll(pageable);

        return ResponseEntity.ok(pageProduct);
    }

    @PostMapping
    @Transactional
    @Secured("ROULE_ADMIN")
    public ResponseEntity<DadosDetalhamentoProduto> cadastraProdutos(@RequestBody @Valid DadosCadastroProdutos dados, UriComponentsBuilder builder) {
        DadosDetalhamentoProduto produto = productService.saveProduct(dados);

        var uri = builder.path("/produtos/{id}").buildAndExpand(produto.id()).toUri();

        return ResponseEntity.created(uri).body(produto);
    }

    @PutMapping
    @Transactional
    @Secured("ROULE_ADMIN")
    public ResponseEntity atualizaProdudos(@RequestBody @Valid DadosAtualizacaoProduto dados) {
        DadosDetalhamentoProduto productUpdated = productService.updateProduct(dados);

        return ResponseEntity.ok(productUpdated);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Secured("ROULE_ADMIN")
    public ResponseEntity deletaProduto(@PathVariable Long id) {
        productService.deleteProduct(id);
        
        return ResponseEntity.noContent().build();
    }
}
