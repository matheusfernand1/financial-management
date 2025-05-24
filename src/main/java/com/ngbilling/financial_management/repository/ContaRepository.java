package com.ngbilling.financial_management.repository;

import com.ngbilling.financial_management.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, Long> {
}