package com.company.reverselog.domain.requestProduct.entity;

import com.company.reverselog.domain.produto.entity.Produto;
import com.company.reverselog.domain.requestProduct.dto.RequestProductDto;
import com.company.reverselog.domain.solicitacao.entity.Solicitacao;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_produto_PK")
    private Produto produto;

    private Integer quantidade;


    public RequestProduct(RequestProductDto requestProductDto) {
        
    }


    public RequestProduct(Solicitacao solicitacao, Produto product, int quantidade) {
        this.solicitacao = solicitacao;
        this.produto = product;
        this.quantidade = quantidade;
    }
}
