package com.company.reverselog.controller;

import com.company.reverselog.controller.exception.ControllerNotFoundExeption;
import com.company.reverselog.domain.cliente.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteRepository repository;
    @GetMapping("/ativo")
    public ResponseEntity<Page<DadosListagemClientes>> listCostumerActive(@PageableDefault(size = 10, sort = {"id"}) Pageable pageable){
        var costumer = repository.findAllByAtivoTrue(pageable).map(DadosListagemClientes::new);
        return  ResponseEntity.ok(costumer);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<DadosListagemClientes>> listAllcCostumers(@PageableDefault(size = 5,sort = {"id"}) Pageable pageable){
        var costumers = repository.findAll(pageable).map(DadosListagemClientes::new);
        return ResponseEntity.ok(costumers);
    }

    @PostMapping
    @Transactional
    public ResponseEntity registerCustomer(@RequestBody @Valid CustomerRegistrationData data, UriComponentsBuilder builder){
        var costumer = new Cliente(data);
        repository.save(costumer);

        var uri = builder.path("/clientes/{id}").buildAndExpand(costumer.getId()).toUri();
        return ResponseEntity.created(uri).body(new CustomerDetailData(costumer));
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateCustumer(@RequestBody @Valid CustomerDetailData data){
        var custumer = repository.findById(data.id())
                .orElseThrow(() -> new ConcurrencyFailureException("Cliente não está cadastrado na base de dados"));

        custumer.updateCustumerData(data);
        return ResponseEntity.ok(new CustomerDetailData(custumer));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteCustumer(@PathVariable Long id){
        var custumer = repository.findById(id)
                .orElseThrow(() -> new ControllerNotFoundExeption("Cliente não está cadastrado na base de dados"));

        custumer.deleteCustumer();

        return ResponseEntity.noContent().build();

    }


}
