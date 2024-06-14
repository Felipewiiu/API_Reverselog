package com.company.reverselog.domain.request.repository;

import com.company.reverselog.domain.request.entity.Solicitacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Long> {
}
