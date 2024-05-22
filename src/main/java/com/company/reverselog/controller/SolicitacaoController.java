package com.company.reverselog.controller;

import com.company.reverselog.domain.solicitacao.RequestRegistrationData;
import com.company.reverselog.domain.solicitacao.Solicitacao;
import com.company.reverselog.domain.solicitacao.SolicitacaoRepository;
<<<<<<< HEAD
import com.company.reverselog.service.ToMakeRequest;
=======
import com.company.reverselog.service.MakeRequest;
>>>>>>> 0db56ae (feat: implementando a solicitacaoRepository)
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
<<<<<<< HEAD
    private ToMakeRequest request;
=======
   private MakeRequest makeRequest;
>>>>>>> 0db56ae (feat: implementando a solicitacaoRepository)

    @GetMapping
    public ResponseEntity<Page<RequestRegistrationData>> findAll(Pageable pageable) {
       return  ResponseEntity.ok().build();
       // precica criar a lógica de solicitações
    }

    @PostMapping
    @Transactional
    public ResponseEntity registrationRequest(@RequestBody RequestRegistrationData request, UriComponentsBuilder uriBuilder){
        var dto = makeRequest.Request(request);

        return  ResponseEntity.ok(dto);
    }

}
