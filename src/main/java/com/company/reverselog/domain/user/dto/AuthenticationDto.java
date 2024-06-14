package com.company.reverselog.domain.user.dto;

public record AuthenticationDto(
        String login,
        String senha
) {
}
