package com.company.reverselog.domain.request.dto;

import com.company.reverselog.domain.request.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record RequestRegistrationData(

        String nf_compra,

        List<QuantityProducts> produto,

        @NotBlank(message = "O campo descrição de defeito não pode ser null ou vazio")
        String descricao_defeito,

        String cliente_id
) {

}
