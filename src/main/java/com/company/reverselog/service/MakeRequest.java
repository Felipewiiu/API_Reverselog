package com.company.reverselog.service;

import com.company.reverselog.domain.cliente.ClienteRepository;
import com.company.reverselog.domain.produto.Produto;
import com.company.reverselog.domain.produto.ProdutoRepository;
import com.company.reverselog.domain.solicitacao.RequestDetailData;
import com.company.reverselog.domain.solicitacao.RequestRegistrationData;
import com.company.reverselog.domain.solicitacao.Solicitacao;
import com.company.reverselog.domain.solicitacao.SolicitacaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
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
        Set<Produto> products = new HashSet<>();

       data.produto_id().stream().forEach(p -> products.add(productRepository.getReferenceById(p)));

        var cliente = clienteRepository.getReferenceById(data.cliente_id());

        System.out.println(products);

        Solicitacao solicitacao = new Solicitacao();
        solicitacao.setNf_compra(data.nf_compra());
        solicitacao.setProduto(products);
        solicitacao.setDescricao_defeito(data.descricao_defeito());
        solicitacao.setData(data.data());
        solicitacao.setStatus(data.status());
        solicitacao.setCliente(cliente);


        solicitacaoRepository.save(solicitacao);

        return new RequestDetailData(solicitacao);

    }

}
