package com.company.reverselog.domain.request.dto;

import com.company.reverselog.domain.request.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record RequestRegistrationData(

        @NotNull(message = "O campo nf_RMA não pode ser nulo")
        @NotBlank(message = "O campo nf_RMA não pode estar em branco")
        String nf_RMA,

        @NotNull(message = "O campo numero_nf não pode ser nulo")
        Integer numero_nf,

        List<DataProducts> produto,

        String cliente_id
) {

}
