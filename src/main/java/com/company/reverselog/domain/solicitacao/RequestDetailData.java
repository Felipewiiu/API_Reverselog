package com.company.reverselog.domain.solicitacao;

import com.company.reverselog.domain.cliente.Cliente;
import com.company.reverselog.domain.produto.Produto;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public record RequestDetailData(

        String nf_compra,

        @NotNull
        String descricao_defeito,

        LocalDateTime data,

        Status status,

        Cliente cliente

) {
    public RequestDetailData(Solicitacao request){
        this(

                request.getNf_compra(),
                request.getDescricao_defeito(),
                request.getData(),
                request.getStatus(),
                request.getCliente()
        );
    }
}
