package com.company.reverselog.service;

import com.company.reverselog.domain.cliente.ClienteRepository;
import com.company.reverselog.domain.produto.Produto;
import com.company.reverselog.domain.produto.ProdutoRepository;
import com.company.reverselog.domain.requestProduct.RequestProduct;
import com.company.reverselog.domain.requestProduct.RequestProductsRepository;
import com.company.reverselog.domain.solicitacao.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class MakeRequest {
    @Autowired
    private ProdutoRepository productRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private SolicitacaoRepository solicitacaoRepository;

    @Autowired
    private RequestProductsRepository requestProductsRepository;
    @Autowired
    private ProdutoRepository produtoRepository;


    public RequestDetailData Request(RequestRegistrationData data) {
        Solicitacao solicitacao = new Solicitacao();

        data.produto().stream().forEach(product -> {

            var productTarget = productRepository.getReferenceById(product.id_produto());

            RequestProduct requestProductList = new RequestProduct(solicitacao, productTarget, product.quantidade());

            requestProductsRepository.save(requestProductList);

        });


        solicitacaoRepository.save(solicitacao);

        return new RequestDetailData(solicitacao);

    }

}
