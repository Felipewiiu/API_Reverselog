package com.company.reverselog.domain.request.entity;

import com.company.reverselog.domain.custumer.entity.Cliente;
import com.company.reverselog.domain.product.service.ChangeBase64ForByte;
import com.company.reverselog.domain.requestProduct.entity.RequestProduct;
import com.company.reverselog.domain.request.Status;
import com.company.reverselog.domain.request.dto.SolicitacaoDto;
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

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] nf_compra;

    @JsonIgnore
    @OneToMany(mappedBy = "solicitacao", cascade = CascadeType.ALL)
    private List<RequestProduct> requestProducts = new ArrayList<>();

    private String descricao_defeito;

    private LocalDateTime data;

    private Boolean ativo = true;

    @Enumerated(EnumType.STRING)
    private Status status = Status.ANALISE_DE_GARANTIA;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")// nome da chave estrangeira
    private Cliente cliente;



}
