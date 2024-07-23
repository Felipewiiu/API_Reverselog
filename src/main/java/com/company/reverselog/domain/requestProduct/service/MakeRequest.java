package com.company.reverselog.domain.requestProduct.service;

import com.company.reverselog.domain.custumer.entity.Cliente;
import com.company.reverselog.domain.custumer.repository.ClienteRepository;
import com.company.reverselog.domain.product.repository.ProdutoRepository;
import com.company.reverselog.domain.product.service.ChangeBase64ForByte;
import com.company.reverselog.domain.requestProduct.entity.RequestProduct;
import com.company.reverselog.domain.requestProduct.repository.RequestProductsRepository;
import com.company.reverselog.domain.request.dto.RequestDetailData;
import com.company.reverselog.domain.request.dto.RequestRegistrationData;
import com.company.reverselog.domain.request.entity.Solicitacao;
import com.company.reverselog.domain.request.repository.SolicitacaoRepository;
import com.company.reverselog.domain.requestProduct.dto.DataListRequestDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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

    public Page<DataListRequestDto> findAllRequest(Pageable pageable) {
        Page<Solicitacao> requestList = solicitacaoRepository.findAll(pageable);

        return requestList.map(this::toDTO);
    }


    public RequestDetailData saveRequest(RequestRegistrationData data) {
        Solicitacao solicitacao = new Solicitacao();

        Cliente custumer = clienteRepository.findById(data.cliente_id())
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado com o ID: " + data.cliente_id()));

        data.produto().stream().forEach(product -> {
            var productTarget = productRepository.getReferenceById(product.id_produto());
            RequestProduct requestProductList = new RequestProduct(solicitacao, productTarget, product.quantidade());
            requestProductsRepository.save(requestProductList);

        });

        solicitacao.setNf_compra(ChangeBase64ForByte.changeBase64(data.nf_compra()));
        solicitacao.setDescricao_defeito(data.descricao_defeito());
        solicitacao.setCliente(custumer);
        solicitacao.setStatus(data.status());
        solicitacao.setData(LocalDateTime.now());

        solicitacaoRepository.save(solicitacao);

        return new RequestDetailData(solicitacao);

    }

    private DataListRequestDto toDTO(Solicitacao solicitacao) {
        return new DataListRequestDto(
                solicitacao.getId(),
                solicitacao.getCliente(),
                solicitacao.getStatus(),
                solicitacao.getNf_compra(),
                solicitacao.getData()
        );
    }

}
