package com.company.reverselog.domain.endereco;

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


    public void updateAdrress(Endereco endereco) {
        if(this.logradouro != null) {
            this.logradouro = endereco.getLogradouro();
        }
        if(this.bairro != null) {
            this.bairro = endereco.getBairro();
        }
        if(this.cep != null) {
            this.cep = endereco.getCep();
        }
        if(this.numero != null) {
            this.numero = endereco.getNumero();
        }
        if(this.complemento != null) {
            this.complemento = endereco.getComplemento();
        }
        if(this.cidade != null) {
            this.cidade = endereco.getCidade();
        }
        if(this.uf != null) {
            this.uf = endereco.getUf();
        }
    }
}
