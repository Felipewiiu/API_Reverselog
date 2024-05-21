package com.company.reverselog.service;

import com.company.reverselog.domain.produto.ProdutoRepository;
import com.company.reverselog.domain.solicitacao.SolicitacaoRepository;
import com.company.reverselog.domain.solicitacao.DetailRequestData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToMakeRequest {

    @Autowired
    private SolicitacaoRepository requestRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

//    public DetailRequestData toMakeRequests (){
//
//    }
}
