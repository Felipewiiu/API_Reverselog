package com.company.reverselog.controller;

import com.company.reverselog.domain.produto.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoRepository repository;

    @GetMapping
    public ResponseEntity<Page<DadosListagemProdutosAtivos>> listaProdutosAtivos(@PageableDefault(size = 10, sort = {"id"}) Pageable pageable) {
        var page = repository.findAllByAtivoTrue(pageable).map(DadosListagemProdutosAtivos::new);


        return ResponseEntity.ok(page);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<DadosListagemProdutos>> listaTodosProdutos(@PageableDefault(size = 10, sort = {"id"}) Pageable pageable) {
        var page = repository.findAll(pageable).map(DadosListagemProdutos::new);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    @Transactional
    public ResponseEntity cadastraProdutos(@RequestBody @Valid DadosCadastroProdutos dados, UriComponentsBuilder builder) {
        var produto = new Produto(dados);
        repository.save(produto);

        var uri = builder.path("/produtos/{id}").buildAndExpand(produto.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoProduto(produto));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizaProdudos(@RequestBody @Valid DadosAtualizacaoProduto dados) {
        var produto = repository.getReferenceById(dados.id());
        produto.atualizaInformacoesProduto(dados);

        return ResponseEntity.ok(new DadosDetalhamentoProduto(produto));

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletaProduto(@PathVariable Long id) {
        var produto = repository.getReferenceById(id);
        produto.excluir(id);

        return ResponseEntity.noContent().build();
    }
}
