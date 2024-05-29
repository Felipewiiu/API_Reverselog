package com.company.reverselog.domain.cliente.entity;

import com.company.reverselog.domain.cliente.dto.CustomerDetailData;
import com.company.reverselog.domain.cliente.dto.CustomerRegistrationData;
import com.company.reverselog.domain.endereco.entity.Endereco;
import com.company.reverselog.domain.solicitacao.entity.Solicitacao;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Table(name = "clientes")
@Entity(name = "Cliente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String telefone;

    private String cpf;

    private String cnpj;
    @JsonIgnore
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Solicitacao> solicitacoes = new ArrayList<>();

    @Embedded
    private Endereco endereco;
    private Boolean ativo = true;

    public Cliente(CustomerRegistrationData data) {
        this.email = data.email();
        this.telefone = data.telefone();
        this.cpf = data.cpf();
        this.cnpj = data.cnpj();
        this.endereco = new Endereco(data.endereco());
    }

    public void updateCustumerData(CustomerDetailData data) {
        if(data.email() != null){
            this.email = data.email();
        }
        if(data.telefone() != null){
            this.telefone = data.telefone();
        }
        if(data.cpf() != null){
            this.cpf = data.cpf();
        }
        if(data.cnpj() != null){
            this.cnpj = data.cnpj();
        }
        if(data.endereco() != null){
            this.endereco.updateAddress(data.endereco());
        }
        if(data.ativo() != null){
            this.ativo = data.ativo();
        }
    }

    public void deleteCustumer() {
        this.ativo = false;
    }
}
