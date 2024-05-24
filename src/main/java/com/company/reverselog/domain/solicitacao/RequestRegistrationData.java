package com.company.reverselog.domain.solicitacao;

import com.company.reverselog.domain.cliente.Cliente;
import com.company.reverselog.domain.produto.Produto;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public record RequestRegistrationData(
        String nf_compra,
        List<Long> produto_id,
        String descricao_defeito,

        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime data,

        Status status,
        Long cliente_id
) {

}
