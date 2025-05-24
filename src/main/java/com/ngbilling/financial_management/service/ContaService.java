package com.ngbilling.financial_management.service;

import com.ngbilling.financial_management.model.Conta;
import com.ngbilling.financial_management.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContaService {

    @Autowired
    ContaRepository contaRepository;

    public Conta criarConta(String titular) {
        Conta conta = new Conta(null, titular);
        return contaRepository.save(conta);
    }

    public Conta criarConta(Long numero, Double saldo) {
        if (contaRepository.existsById(numero)) {
            throw new IllegalArgumentException("Conta já existe");
        }
        Conta conta = new Conta(numero, saldo);
        return contaRepository.save(conta);
    }

    public Conta buscarConta(Long numero) {
        Optional<Conta> conta = contaRepository.findById(numero);
        return conta.orElse(null);
    }
}
