package com.company.reverselog.domain.requestProduct.entity;

import com.company.reverselog.domain.product.entity.Produto;
import com.company.reverselog.domain.request.entity.Solicitacao;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "RequestProduct")
@Table(name = "tb_solicitacao_produto")
@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@EqualsAndHashCode(of = "{id}")
public class RequestProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_requestProducts;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_solicitacao_PK")
    private Solicitacao solicitacao;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_produto_PK")
    private Produto produto;

    private Integer quantidade;

    private String descricao;

    public RequestProduct(Solicitacao request, Produto product, int amount, String description) {
        this.solicitacao = request;
        this.produto = product;
        this.quantidade = amount;
        this.descricao = description;
    }
}
