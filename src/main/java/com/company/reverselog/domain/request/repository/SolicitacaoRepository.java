package com.company.reverselog.domain.request.repository;

import com.company.reverselog.domain.custumer.entity.Cliente;
import com.company.reverselog.domain.request.entity.Solicitacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Long> {

    @Query("SELECT s FROM Solicitacao s join s.cliente c where c.email = :email")
    Page<Solicitacao> findByEmail(@Param("email") String email,  Pageable pageable);

    @Query("SELECT s FROM Solicitacao s WHERE s.cliente.id = :clienteId")
    Page<Solicitacao> findByClienteId(@Param("clienteId") Long clienteId, Pageable pageable);
}

