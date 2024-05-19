package com.company.reverselog.controller;

import com.company.reverselog.domain.solicitacao.SolicitacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/solicitacao")
public class SolicitacaoController {

    @Autowired
    private SolicitacaoRepository repository;


}
