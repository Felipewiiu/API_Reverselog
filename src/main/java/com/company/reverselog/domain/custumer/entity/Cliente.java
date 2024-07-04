package com.company.reverselog.domain.custumer.entity;

import com.company.reverselog.domain.custumer.dto.CustomerDetailData;
import com.company.reverselog.domain.custumer.dto.CustomerRegistrationData;
import com.company.reverselog.domain.custumer.dto.CustumerDTO;
import com.company.reverselog.domain.address.entity.Endereco;
import com.company.reverselog.domain.request.entity.Solicitacao;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Table(name = "clientes")
@Entity(name = "Cliente")
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

//    @Column(unique = true)
    private String email;

    private String password;

    private String telefone;

    @Column(unique = true)
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

    public Cliente(CustumerDTO data) {
        this.name = data.name();
        this.email = data.email();
        this.telefone = data.telefone();
        this.cpf = data.cpf();
        this.cnpj = data.cnpj();
        this.endereco = new Endereco(
                data.logradouro(),
                data.bairro(),
                data.cep(),
                data.numero(),
                data.complemento(),
                data.cidade(),
                data.uf()
                );

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
