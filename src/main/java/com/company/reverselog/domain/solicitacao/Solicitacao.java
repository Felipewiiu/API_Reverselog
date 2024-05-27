package com.company.reverselog.domain.solicitacao;

import com.company.reverselog.domain.cliente.Cliente;
import com.company.reverselog.domain.requestProduct.RequestProduct;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @JsonIgnore
    @OneToMany(mappedBy = "solicitacao", cascade = CascadeType.ALL)
    private List<RequestProduct> requestProducts = new ArrayList<>();

    private String descricao_defeito;

    private LocalDateTime data;

    private Boolean ativo = true;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")// nome da chave estrangeira
    private Cliente cliente;

    public Solicitacao(SolicitacaoDto solicitacaoDto) {
        this.nf_compra = solicitacaoDto.nf_compra();
        this.data = LocalDateTime.now();
        this.descricao_defeito = solicitacaoDto.descricao_defeito();
        this.cliente = solicitacaoDto.cliente();

    }
}
