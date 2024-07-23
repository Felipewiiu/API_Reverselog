package com.company.reverselog.domain.request.dto;

import com.company.reverselog.domain.custumer.entity.Cliente;
import com.company.reverselog.domain.request.entity.Solicitacao;
import com.company.reverselog.domain.request.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Arrays;

public record RequestDetailData(

        byte[] nf_compra,

        @NotBlank(message = "O campo descição de defeito não pode estar em branco ou nulo")
        String descricao_defeito,

        LocalDateTime data,

        Status status,

        Cliente cliente

) {
    public RequestDetailData(Solicitacao request) {
        this(
                request.getNf_compra(),
                request.getDescricao_defeito(),
                request.getData(),
                request.getStatus(),
                request.getCliente()
        );
    }
}
