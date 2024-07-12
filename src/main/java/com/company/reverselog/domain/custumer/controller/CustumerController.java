package com.company.reverselog.domain.custumer.controller;

import com.company.reverselog.domain.custumer.dto.*;
import com.company.reverselog.domain.custumer.entity.Cliente;
import com.company.reverselog.domain.custumer.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/clientes")
public class CustumerController {

    @Autowired
    private ClienteService clienteService;

    @SecurityRequirement(name = "bearer-key")
    @GetMapping("/ativo")
    @Operation(summary = "Lista todos os clientes ativos no sistema")
    public ResponseEntity<Page<DadosListagemClientes>> listCostumerActive(@PageableDefault(size = 10, sort = {"id"}) Pageable pageable){
        Page<DadosListagemClientes> dadosListagemClientes = clienteService.fildAllActive(pageable);

        return  ResponseEntity.ok(dadosListagemClientes);
    }

    @GetMapping("/all")
    @SecurityRequirement(name = "bearer-key")
    @Operation(summary = "Lista todos os cliente ativos e inativos no sistema")
    public ResponseEntity<Page<DadosListagemClientes>> listAllCostumers(@PageableDefault(size = 5,sort = {"id"}) Pageable pageable){
        Page<DadosListagemClientes> dadosListagemClientes = clienteService.findAllCustumer(pageable);

        return ResponseEntity.ok(dadosListagemClientes);
    }

    @GetMapping("/get-email")
    @SecurityRequirement(name = "bearer-key")
    @Operation(summary = "Lista cliente por email")
    public ResponseEntity findCustomerByEmail (@RequestParam("email") EmailCustomerDto email){
        CustumerDTO cliente = clienteService.findCustomerByEmail(email);
        System.out.println("--------> passou");
        return  ResponseEntity.ok(cliente);
    }

    @PostMapping("/post")
    @Transactional
    @Operation(summary = "Cadastra um novo clientes no sistema")
    public ResponseEntity<CustumerDTO> registerCustomer(@RequestBody @Valid CustumerDTO data, UriComponentsBuilder builder){
        CustumerDTO custumer = clienteService.saveCustumer(data);

        var uri = builder.path("/clientes/{id}").buildAndExpand(custumer.id()).toUri();

        return ResponseEntity.created(uri).body(custumer);
    }

    @PutMapping("/update/{id}")
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    @Operation(summary = "Atualiza os registro de um cliente no sistema")
    public ResponseEntity<CustomerDetailData> updateCustumer(@PathVariable Long id, @RequestBody @Valid CustomerDetailData data){
       CustomerDetailData custumer = clienteService.updateCustumer(id, data);

        return ResponseEntity.ok(custumer);
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    @Operation(summary = "Inativa um cliente no sistema")
    public ResponseEntity<Void> deleteCustumer(@PathVariable Long id){
        clienteService.deleteCustumer(id);

        return ResponseEntity.noContent().build();
    }

    @Transactional
    @SecurityRequirement(name = "bearer-key")
    @Operation(summary = "Atualiza os registro de um cliente no sistema pelo seu email")
    @PutMapping("/update-custumer")
    public ResponseEntity<CustomerUpdateRegister>  updateCustumerByEmail(@RequestParam("email") String email, @RequestBody @Valid CustomerUpdateRegister data) {
        CustomerUpdateRegister custumer = clienteService.updateCustumerByEmail(email, data);

        return ResponseEntity.ok(custumer);
    }



}
