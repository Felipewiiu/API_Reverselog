package com.company.reverselog.controller;

import com.company.reverselog.domain.solicitacao.RequestRegistrationData;
import com.company.reverselog.domain.solicitacao.Solicitacao;
import com.company.reverselog.domain.solicitacao.SolicitacaoRepository;
import com.company.reverselog.service.ToMakeRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/solicitacao")
public class SolicitacaoController {

    @Autowired
    private ToMakeRequest request;

    @GetMapping
    public ResponseEntity<Page<RequestRegistrationData>> findAll(Pageable pageable) {
       return  ResponseEntity.ok().build();
       // precica criar a lógica de solicitações
    }

    @PostMapping
    @Transactional
    public ResponseEntity<RequestRegistrationData> registrationRequest(@RequestBody RequestRegistrationData request, UriComponentsBuilder builder){
        return  ResponseEntity.ok().build();
        // precica criar a lógica de solicitações
    }


}
