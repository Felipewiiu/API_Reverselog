package com.company.reverselog.domain.solicitacao.repository;

import com.company.reverselog.domain.solicitacao.entity.Solicitacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Long> {
}
