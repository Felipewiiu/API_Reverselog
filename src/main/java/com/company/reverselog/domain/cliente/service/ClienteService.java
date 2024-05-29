package com.company.reverselog.domain.cliente.service;

import com.company.reverselog.controller.exception.ControllerNotFoundExeption;
import com.company.reverselog.domain.cliente.dto.CustomerDetailData;
import com.company.reverselog.domain.cliente.dto.CustomerRegistrationData;
import com.company.reverselog.domain.cliente.dto.CustumerDTO;
import com.company.reverselog.domain.cliente.dto.DadosListagemClientes;
import com.company.reverselog.domain.cliente.entity.Cliente;
import com.company.reverselog.domain.cliente.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;

    public Page<DadosListagemClientes> fildAllActive(Pageable pageable){
        Page<Cliente> custumer = repository.findAllByAtivoTrue(pageable);

        return custumer.map(this::toDTO);
    }

    public Page<DadosListagemClientes> findAllCustumer(Pageable pageable){
        Page<Cliente> custumer = repository.findAll(pageable);

        return custumer.map(this::toDTO);
    }

    public CustumerDTO saveCustumer(CustumerDTO data){
        Cliente custumer = new Cliente(data);

        repository.save(custumer);

        return customerRegistrationDataDTO(custumer);
    }

    public CustomerDetailData updateCustumer(Long id,CustomerDetailData data){
        var custumer = repository.findById(id)
                .orElseThrow(() -> new ConcurrencyFailureException("Cliente não está cadastrado na base de dados"));

        custumer.updateCustumerData(data);

        return new CustomerDetailData(custumer);
    }

    public void deleteCustumer(Long id){
        Cliente custumer = repository.findById(id)
                .orElseThrow(() -> new ControllerNotFoundExeption("Cliente não está cadastrado na base de dados"));

        custumer.deleteCustumer();
    }

    private DadosListagemClientes toDTO(Cliente cliente){
        return new DadosListagemClientes(
                cliente.getId(),
                cliente.getEmail(),
                cliente.getTelefone(),
                cliente.getCpf(),
                cliente.getCnpj(),
                cliente.getSolicitacoes(),
                cliente.getEndereco(),
                cliente.getAtivo()
        );
    }

    private CustumerDTO customerRegistrationDataDTO (Cliente cliente){
        return new CustumerDTO(
                cliente.getId(),
                cliente.getEmail(),
                cliente.getTelefone(),
                cliente.getCpf(),
                cliente.getCnpj(),
                cliente.getEndereco()
        );
    }
}
