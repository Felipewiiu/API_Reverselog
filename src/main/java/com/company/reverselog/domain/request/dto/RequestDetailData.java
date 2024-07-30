package com.company.reverselog.domain.request.dto;

import com.company.reverselog.domain.custumer.entity.Cliente;
import com.company.reverselog.domain.request.entity.Solicitacao;
import com.company.reverselog.domain.request.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Arrays;

public record RequestDetailData(
        @NotNull(message = "O campo numero_nf não pode ser nulo")
        Integer numero_nf,

        LocalDateTime data,

        Status status,

        Cliente cliente

) {
    public RequestDetailData(Solicitacao request) {
        this(
                request.getNumero_nf(),
                request.getData(),
                request.getStatus(),
                request.getCliente()
        );
    }
}
