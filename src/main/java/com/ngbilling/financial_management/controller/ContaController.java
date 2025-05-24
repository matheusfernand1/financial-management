package com.ngbilling.financial_management.controller;

import com.ngbilling.financial_management.model.Conta;
import com.ngbilling.financial_management.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/conta")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> criarConta(@RequestBody Map<String, Object> payload) {
        Long numeroConta = Long.valueOf(payload.get("numero_conta").toString());
        Double saldo = Double.valueOf(payload.get("saldo").toString());
        if (contaService.buscarConta(numeroConta) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("erro", "Conta já existe"));
        }
        try {
            Conta conta = contaService.criarConta(numeroConta, saldo);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of("numero_conta", conta.getNumero(), "saldo", conta.getSaldo()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> buscarConta(@RequestParam("numero_conta") Long numeroConta) {
        Conta conta = contaService.buscarConta(numeroConta);
        if (conta == null) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("erro", "Conta não encontrada"));
        }
        return ResponseEntity.ok(Map.of("numero_conta", conta.getNumero(), "saldo", conta.getSaldo()));
    }
}
