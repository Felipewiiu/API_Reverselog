package com.company.reverselog.service;

import com.company.reverselog.domain.cliente.Cliente;
import com.company.reverselog.domain.cliente.ClienteRepository;
import com.company.reverselog.domain.produto.ProdutoRepository;
import com.company.reverselog.domain.requestProduct.RequestProduct;
import com.company.reverselog.domain.requestProduct.RequestProductsRepository;
import com.company.reverselog.domain.solicitacao.RequestDetailData;
import com.company.reverselog.domain.solicitacao.RequestRegistrationData;
import com.company.reverselog.domain.solicitacao.Solicitacao;
import com.company.reverselog.domain.solicitacao.SolicitacaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
@Transactional
public class MakeRequest {
    @Autowired
    private ProdutoRepository productRepository;


    @Autowired
    private SolicitacaoRepository solicitacaoRepository;

    @Autowired
    private RequestProductsRepository requestProductsRepository;
    @Autowired
    private ClienteRepository clienteRepository;


    public RequestDetailData request(RequestRegistrationData data) {
        Solicitacao solicitacao = new Solicitacao();

        Cliente custumer = clienteRepository.findById(data.cliente_id())
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado com o ID: " + data.cliente_id()));

        data.produto().stream().forEach(product -> {
            var productTarget = productRepository.getReferenceById(product.id_produto());
            RequestProduct requestProductList = new RequestProduct(solicitacao, productTarget, product.quantidade());
            requestProductsRepository.save(requestProductList);

        });

        solicitacao.setNf_compra(data.nf_compra());
        solicitacao.setDescricao_defeito(data.descricao_defeito());
        solicitacao.setCliente(custumer);
        solicitacao.setStatus(data.status());
        solicitacao.setData(LocalDateTime.now());

        solicitacaoRepository.save(solicitacao);

        return new RequestDetailData(solicitacao);

    }

}
