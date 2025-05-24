package com.ngbilling.financial_management.service;

import com.ngbilling.financial_management.model.Conta;
import com.ngbilling.financial_management.model.Transacao;
import com.ngbilling.financial_management.repository.ContaRepository;
import com.ngbilling.financial_management.repository.TransacaoRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class TransacaoService {

    @Autowired
    ContaRepository contaRepository;

    @Autowired
    TransacaoRepository transacaoRepository;

    @Autowired
    ContaService contaService;

    public Conta realizarTransacao(Long numeroConta, Double valor, String formaPagamento) {
        Conta conta = contaRepository.findById(numeroConta).orElse(null);
        if (conta == null) {
            throw new RuntimeException("Conta não encontrada");
        }
        double taxa = calcularTaxa(valor, formaPagamento);
        double valorTotal = valor + taxa;
        if (conta.getSaldo() < valorTotal) {
            throw new RuntimeException("Saldo insuficiente");
        }
        conta.setSaldo(conta.getSaldo() - valorTotal);
        contaRepository.save(conta);

        Transacao transacao = new Transacao(null, conta.getNumero(), valorTotal, formaPagamento);
        transacaoRepository.save(transacao);

        return conta;
    }

    private double calcularTaxa(Double valor, String formaPagamento) {
        String forma = formaPagamento.toUpperCase();
        return switch (forma) {
            case "D" -> valor * 0.03;
            case "C" -> valor * 0.05;
            case "P" -> 0.0;
            default -> throw new RuntimeException("Forma de pagamento inválida");
        };
    }
}
