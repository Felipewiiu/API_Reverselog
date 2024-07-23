package com.company.reverselog.domain.custumer.repository;

import com.company.reverselog.domain.custumer.entity.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Page<Cliente> findAllByAtivoTrue(Pageable pageable);

    Optional<Cliente> findByEmail(String email);
}
