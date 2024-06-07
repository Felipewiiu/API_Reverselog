package com.company.reverselog.domain.cliente.controller;

import com.company.reverselog.domain.cliente.dto.CustomerDetailData;
import com.company.reverselog.domain.cliente.dto.CustumerDTO;
import com.company.reverselog.domain.cliente.dto.DadosListagemClientes;
import com.company.reverselog.domain.cliente.service.ClienteService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/clientes")
@SecurityRequirement(name = "bearer-key")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/ativo")
    public ResponseEntity<Page<DadosListagemClientes>> listCostumerActive(@PageableDefault(size = 10, sort = {"id"}) Pageable pageable){
        Page<DadosListagemClientes> dadosListagemClientes = clienteService.fildAllActive(pageable);

        return  ResponseEntity.ok(dadosListagemClientes);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<DadosListagemClientes>> listAllCostumers(@PageableDefault(size = 5,sort = {"id"}) Pageable pageable){
        Page<DadosListagemClientes> dadosListagemClientes = clienteService.findAllCustumer(pageable);

        return ResponseEntity.ok(dadosListagemClientes);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<CustumerDTO> registerCustomer(@RequestBody @Valid CustumerDTO data, UriComponentsBuilder builder){
        CustumerDTO custumer = clienteService.saveCustumer(data);

        var uri = builder.path("/clientes/{id}").buildAndExpand(custumer.id()).toUri();

        return ResponseEntity.created(uri).body(custumer);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<CustomerDetailData> updateCustumer(@PathVariable Long id, @RequestBody @Valid CustomerDetailData data){
       CustomerDetailData custumer = clienteService.updateCustumer(id, data);

        return ResponseEntity.ok(custumer);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteCustumer(@PathVariable Long id){
        clienteService.deleteCustumer(id);

        return ResponseEntity.noContent().build();
    }


}
