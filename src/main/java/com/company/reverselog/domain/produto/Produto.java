package com.company.reverselog.domain.produto;

import com.company.reverselog.domain.solicitacao.Solicitacao;
import jakarta.persistence.*;
import lombok.*;

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

    private Integer numero_de_serie;

    private Integer ncm;

    private Boolean ativo = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "solicitacao_id")
    private Solicitacao solicitacao;

    public Produto(DadosCadastroProdutos dados) {
        this.nome = dados.nome();
        this.modelo = dados.modelo();
        this.numero_de_serie = dados.numero_de_serie();
        this.ncm = dados.ncm();
        this.ativo = true;
    }


    public void atualizaInformacoesProduto(DadosAtualizacaoProduto produto) {
        if(produto.nome() != null){
            this.nome = produto.nome();
        }
        if(produto.modelo() != null){
            this.modelo = produto.modelo();
        }
        if(produto.numero_de_serie() != null){
            this.numero_de_serie = produto.numero_de_serie();
        }
        if(produto.ncm() != null){
            this.ncm = produto.ncm();
        }
        if(produto.ativo() != null){
            this.ativo = produto.ativo();
        }

    }

    public void excluir(Long id) {
        this.ativo = false;
    }
}
