package com.company.reverselog.domain.custumer.service;

import com.company.reverselog.domain.custumer.dto.CustomerDetailData;
import com.company.reverselog.domain.custumer.dto.CustomerUpdateRegister;
import com.company.reverselog.domain.custumer.dto.CustumerDTO;
import com.company.reverselog.domain.custumer.dto.DadosListagemClientes;
import com.company.reverselog.domain.custumer.entity.Cliente;
import com.company.reverselog.domain.custumer.repository.ClienteRepository;
import com.company.reverselog.domain.user.entity.Usuario;
import com.company.reverselog.domain.user.repository.UsuarioRepository;
import com.company.reverselog.exception.exception.ControllerNotFoundExeption;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;
    private final UsuarioRepository userRepository;

    public Page<DadosListagemClientes> fildAllActive(Pageable pageable) {
        Page<Cliente> custumer = repository.findAllByAtivoTrue(pageable);

        return custumer.map(this::toDTO);
    }

    public Page<DadosListagemClientes> findAllCustumer(Pageable pageable) {
        Page<Cliente> custumer = repository.findAll(pageable);

        return custumer.map(this::toDTO);
    }

    public CustumerDTO saveCustumer(CustumerDTO data) {

        String passworHash = BCrypt.hashpw(data.password(), BCrypt.gensalt());

        Cliente custumer = new Cliente(data);

        Usuario usuario = new Usuario(data.email(), passworHash);

        userRepository.save(usuario);
        repository.save(custumer);

        return customerRegistrationDataDTO(custumer);
    }

    public CustomerDetailData updateCustumer(Long id, CustomerDetailData data) {
        Cliente custumer = repository.findById(id)
                .orElseThrow(() -> new ConcurrencyFailureException("Cliente não está cadastrado na base de dados"));

        custumer.updateCustumerData(data);

        return new CustomerDetailData(custumer);
    }

    public void deleteCustumer(Long id) {
        Cliente custumer = repository.findById(id)
                .orElseThrow(() -> new ControllerNotFoundExeption("Cliente não está cadastrado na base de dados"));

        custumer.deleteCustumer();
    }

    public CustomerUpdateRegister updateCustumerByEmail(String email, CustomerUpdateRegister data) {
        Cliente custumer = repository.findByEmail(email);
        System.out.println(custumer);
        custumer.updateCustumerData(data);
        return new CustomerUpdateRegister(custumer);
    }

    private DadosListagemClientes toDTO(Cliente cliente) {
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

    private CustumerDTO customerRegistrationDataDTO(Cliente cliente) {
        return new CustumerDTO(
                cliente.getId(),
                cliente.getName(),
                cliente.getEmail(),
                cliente.getTelefone(),
                cliente.getCpf(),
                cliente.getCnpj(),
                cliente.getEndereco().getLogradouro(),
                cliente.getEndereco().getBairro(),
                cliente.getEndereco().getCep(),
                cliente.getEndereco().getNumero(),
                cliente.getEndereco().getComplemento(),
                cliente.getEndereco().getCidade(),
                cliente.getEndereco().getUf(),
                cliente.getPassword()
        );
    }
}
