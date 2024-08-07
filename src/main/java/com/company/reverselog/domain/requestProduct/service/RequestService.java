package com.company.reverselog.domain.requestProduct.service;

import com.company.reverselog.domain.custumer.entity.Cliente;
import com.company.reverselog.domain.custumer.repository.ClienteRepository;
import com.company.reverselog.domain.product.repository.ProdutoRepository;
import com.company.reverselog.domain.product.service.ChangeBase64ForByte;
import com.company.reverselog.domain.requestProduct.dto.RequestByCostumerEmailDto;
import com.company.reverselog.domain.requestProduct.entity.RequestProduct;
import com.company.reverselog.domain.requestProduct.repository.RequestProductsRepository;
import com.company.reverselog.domain.request.dto.RequestDetailData;
import com.company.reverselog.domain.request.dto.RequestRegistrationData;
import com.company.reverselog.domain.request.entity.Solicitacao;
import com.company.reverselog.domain.request.repository.SolicitacaoRepository;
import com.company.reverselog.domain.requestProduct.dto.DataListRequestDto;
import com.company.reverselog.exception.exception.ControllerNotFoundExeption;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class RequestService {
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

        Cliente custumer = clienteRepository.findByEmail(data.cliente_id())
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado com o ID: " + data.cliente_id()));

        data.produto().stream().forEach(product -> {
            var productTarget = productRepository.getReferenceById(product.id());
            RequestProduct requestProductList = new RequestProduct(solicitacao, productTarget, product.amount(), product.description());
            requestProductsRepository.save(requestProductList);

        });

        solicitacao.setNf_RMA(ChangeBase64ForByte.changeBase64(data.nf_RMA()));
        solicitacao.setCliente(custumer);
        solicitacao.setData(LocalDateTime.now());
        solicitacao.setNumero_nf(data.numero_nf());

        solicitacaoRepository.save(solicitacao);

        return new RequestDetailData(solicitacao);

    }

    public Page<RequestByCostumerEmailDto> findRequesActivetByEmail(String email, Pageable pageable) {

       Page<Solicitacao> request = solicitacaoRepository.findByEmail(email, pageable);

       Page<RequestByCostumerEmailDto> dtoPage = request.map(this::convertToDto);
       return dtoPage;
    }

    private DataListRequestDto toDTO(Solicitacao solicitacao) {
        return new DataListRequestDto(
                solicitacao.getId(),
                solicitacao.getCliente(),
                solicitacao.getStatus(),
                solicitacao.getNf_RMA(),
                solicitacao.getData()
        );
    }

    private RequestByCostumerEmailDto convertToDto(Solicitacao solicitacao) {
        return new RequestByCostumerEmailDto(solicitacao);
    }
}
