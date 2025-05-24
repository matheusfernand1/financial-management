package com.ngbilling.financial_management.repository;

import com.ngbilling.financial_management.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
}