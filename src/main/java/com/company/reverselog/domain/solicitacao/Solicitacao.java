package com.company.reverselog.domain.solicitacao;

import com.company.reverselog.domain.cliente.Cliente;
import com.company.reverselog.domain.produto.Produto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
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
    private Set<Produto> produto;

    private String descricao_defeito;

    private LocalDateTime data;

    private Boolean ativo = true;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")

    private Cliente cliente;

    public Solicitacao(RequestDetailData requestRegistrationData) {
        this.nf_compra = requestRegistrationData.nf_compra();
        this.produto = requestRegistrationData.produtos_id();
        this.descricao_defeito = requestRegistrationData.descricao_defeito();
        this.data = requestRegistrationData.data();
        this.status = requestRegistrationData.status();
        this.cliente = requestRegistrationData.cliente();
    }

    public Solicitacao(String s, Set<Produto> products, String s1, LocalDateTime data, Status status, Cliente cliente) {
    }


    public void addProduct (Produto product){
        this.produto.add(product);
    }


}
