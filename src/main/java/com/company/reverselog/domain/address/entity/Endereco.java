package com.company.reverselog.domain.address.entity;

import com.company.reverselog.domain.address.dto.AddressData;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Endereco {
    private String logradouro;
    private String bairro;
    private String cep;
    private String numero;
    private String complemento;
    private String cidade;
    private String uf;


    public Endereco(AddressData endereco) {
        this.logradouro = endereco.logradouro();
        this.bairro = endereco.bairro();
        this.cep = endereco.cep();
        this.numero = endereco.numero();
        this.complemento = endereco.complemento();
        this.cidade = endereco.cidade();
        this.uf = endereco.uf();
    }


     public void updateAddress(Endereco endereco) {
        if (endereco.getLogradouro() != null) {
            this.logradouro = endereco.getLogradouro();
        }
        if (endereco.getBairro() != null) {
            this.bairro = endereco.getBairro();
        }
        if (endereco.getCep() != null) {
            this.cep = endereco.getCep();
        }
        if (endereco.getNumero() != null) {
            this.numero = endereco.getNumero();
        }
        if (endereco.getComplemento() != null) {
            this.complemento = endereco.getComplemento();
        }
        if (endereco.getCidade() != null) {
            this.cidade = endereco.getCidade();
        }
        if (endereco.getUf() != null) {
            this.uf = endereco.getUf();
        }
    }
}
