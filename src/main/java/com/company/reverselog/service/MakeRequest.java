package com.company.reverselog.service;

import com.company.reverselog.domain.cliente.ClienteRepository;
import com.company.reverselog.domain.produto.Produto;
import com.company.reverselog.domain.produto.ProdutoRepository;
import com.company.reverselog.domain.solicitacao.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class MakeRequest {
    @Autowired
    private ProdutoRepository productRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private SolicitacaoRepository solicitacaoRepository;


    public RequestDetailData Request(RequestRegistrationData data) {
        List<Produto> products = new ArrayList<>();

       productRepository.findAllById(data.produto_id())
               .stream()
               .forEach(p -> products.add(p));

        var cliente = clienteRepository.getReferenceById(data.cliente_id());

        System.out.println(products);

        Solicitacao solicitacao = new Solicitacao(data.nf_compra(),products, data.descricao_defeito(),cliente);

        solicitacaoRepository.save(solicitacao);

        return new RequestDetailData(solicitacao);

    }

}
