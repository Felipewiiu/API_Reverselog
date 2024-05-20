package com.company.reverselog.domain.solicitacao;

import com.company.reverselog.domain.cliente.Cliente;
import com.company.reverselog.domain.produto.Produto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "solicitacoes")
@Entity(name = "Solicitacao")
@Getter
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



}
