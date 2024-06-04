package com.company.reverselog.domain.usuario.dto;

public record AuthenticationDto(
        String login,
        String senha
) {
}
