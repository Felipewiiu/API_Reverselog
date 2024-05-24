package com.company.reverselog.domain.solicitacao;

import com.company.reverselog.domain.cliente.Cliente;
import com.company.reverselog.domain.produto.Produto;
import com.google.gson.annotations.SerializedName;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Table(name = "solicitacoes")
@Entity(name = "Solicitacao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Solicitacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nf_compra;

    @OneToMany(mappedBy = "solicitacao", cascade = CascadeType.ALL)
    private List<Produto> produto;

    private String descricao_defeito;

    private LocalDateTime data;

    private Boolean ativo = true;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")

    private Cliente cliente;

    public Solicitacao(String nf, List<Produto> produtos, String descricaoDefeito, Cliente cliente) {
        this.data = LocalDateTime.now();
        this.status = Status.ANALISE_DE_GARANTIA;
        this.nf_compra = nf;
        this.produto = produtos;
        this.descricao_defeito = descricaoDefeito;
        this.cliente = cliente;
    }


    public Solicitacao(SolicitacaoDto requestDto) {
        this.nf_compra = requestDto.nf_compra();
        this.produto = requestDto.produto_list();
        this.descricao_defeito = requestDto.descricao_defeito();
        this.data = LocalDateTime.now();
        this.status = requestDto.status();
        this.cliente = requestDto.cliente();
    }


    public void addProduct (Produto product){
        this.produto.add(product);
    }


}
