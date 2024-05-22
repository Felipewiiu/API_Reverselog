package com.company.reverselog.service;

import com.company.reverselog.domain.cliente.ClienteRepository;
import com.company.reverselog.domain.produto.DadosCadastroProdutos;
import com.company.reverselog.domain.produto.DadosDetalhamentoProduto;
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
import java.util.TreeSet;
import java.util.stream.Collectors;

@Service
public class MakeRequest {
    @Autowired
    private ProdutoRepository productRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private SolicitacaoRepository solicitacaoRepository;

    @Transactional
    public  RequestDetailData Request(RequestRegistrationData data) {
        Set<Produto> products = new HashSet<>();

        for ( Long products_id : data.produto_id()){
            var productUnit = productRepository.getReferenceById(products_id);
            products.add(productUnit);
        }


        var cliente = clienteRepository.getReferenceById(data.cliente_id());// retorna um optional

       Solicitacao solicitacao = new Solicitacao(data.nf_compra(), products, data.descricao_defeito(), data.data(), data.status(), cliente);


       solicitacaoRepository.save(solicitacao);

       return new RequestDetailData(solicitacao);

    }




}
