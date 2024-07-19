package com.company.reverselog.domain.product.entity;

import com.company.reverselog.domain.product.dto.DadosAtualizacaoProduto;
import com.company.reverselog.domain.product.dto.DadosCadastroProdutos;
import com.company.reverselog.domain.product.service.ChangeBase64ForByte;
import com.company.reverselog.domain.requestProduct.entity.RequestProduct;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Table(name = "produtos")
@Entity(name = "Produto")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String modelo;

    private Integer ncm;

    private Boolean ativo = true;

    @Lob
    @Column(columnDefinition="MEDIUMBLOB")
    private byte[] image;

    @JsonIgnore
    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private List<RequestProduct> requestProduct = new ArrayList<>();

    public Produto(DadosCadastroProdutos dados) {
        this.nome = dados.nome();
        this.modelo = dados.modelo();
        this.ncm = dados.ncm();
        this.image = ChangeBase64ForByte.changeBase64(dados.image());
        this.ativo = true;
    }

    public Produto(Integer id) {
        this.id = this.id;
    }


    public void atualizaInformacoesProduto(DadosAtualizacaoProduto produto) {
        if(produto.nome() != null){
            this.nome = produto.nome();
        }
        if(produto.modelo() != null){
            this.modelo = produto.modelo();
        }

        if(produto.ncm() != null){
            this.ncm = produto.ncm();
        }
        if(produto.ativo() != null){
            this.ativo = produto.ativo();
        }
        if(produto.image() != null) {
            this.image = ChangeBase64ForByte.changeBase64(produto.image());
        }

    }

    public void excluir(Long id) {
        this.ativo = false;
    }
}
