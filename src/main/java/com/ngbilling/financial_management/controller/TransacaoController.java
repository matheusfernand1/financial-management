package com.ngbilling.financial_management.controller;

import com.ngbilling.financial_management.exception.ContaNaoEncontradaException;
import com.ngbilling.financial_management.exception.SaldoInsuficienteException;
import com.ngbilling.financial_management.model.Conta;
import com.ngbilling.financial_management.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> realizarTransacao(@RequestBody Map<String, Object> payload) {
        Long numeroConta = Long.valueOf(payload.get("numero_conta").toString());
        Double valor = Double.valueOf(payload.get("valor").toString());
        String formaPagamento = payload.get("forma_pagamento").toString();
        try {
            Conta conta = transacaoService.realizarTransacao(numeroConta, valor, formaPagamento);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of("numero_conta", conta.getNumero(), "saldo", conta.getSaldo()));
        } catch (ContaNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        }
    }
}
