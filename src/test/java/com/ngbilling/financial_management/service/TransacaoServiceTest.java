package com.ngbilling.financial_management.service;

import com.ngbilling.financial_management.model.Conta;
import com.ngbilling.financial_management.model.Transacao;
import com.ngbilling.financial_management.repository.ContaRepository;
import com.ngbilling.financial_management.repository.TransacaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TransacaoServiceTest {

    private ContaRepository contaRepository;
    private TransacaoRepository transacaoRepository;
    private ContaService contaService;
    private TransacaoService transacaoService;

    @BeforeEach
    void setUp() {
        contaRepository = Mockito.mock(ContaRepository.class);
        transacaoRepository = Mockito.mock(TransacaoRepository.class);
        contaService = Mockito.mock(ContaService.class);

        transacaoService = new TransacaoService();
        transacaoService.contaRepository = contaRepository;
        transacaoService.transacaoRepository = transacaoRepository;
        transacaoService.contaService = contaService;
    }

    @Test
    void realizarTransacaoDebitoComTaxa() {
        Conta conta = new Conta(1L, 100.0);
        when(contaRepository.findById(1L)).thenReturn(Optional.of(conta));
        when(contaRepository.save(any(Conta.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(transacaoRepository.save(any(Transacao.class))).thenReturn(null);

        Conta result = transacaoService.realizarTransacao(1L, 10.0, "D");
        assertEquals(89.7, result.getSaldo(), 0.0001);
    }

    @Test
    void realizarTransacaoCreditoComTaxa() {
        Conta conta = new Conta(1L, 100.0);
        when(contaRepository.findById(1L)).thenReturn(Optional.of(conta));
        when(contaRepository.save(any(Conta.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(transacaoRepository.save(any(Transacao.class))).thenReturn(null);

        Conta result = transacaoService.realizarTransacao(1L, 20.0, "C");
        assertEquals(79.0, result.getSaldo(), 0.0001);
    }

    @Test
    void realizarTransacaoPixSemTaxa() {
        Conta conta = new Conta(1L, 100.0);
        when(contaRepository.findById(1L)).thenReturn(Optional.of(conta));
        when(contaRepository.save(any(Conta.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(transacaoRepository.save(any(Transacao.class))).thenReturn(null);

        Conta result = transacaoService.realizarTransacao(1L, 30.0, "P");
        assertEquals(70.0, result.getSaldo(), 0.0001);
    }

    @Test
    void realizarTransacaoContaNaoEncontrada() {
        when(contaRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> transacaoService.realizarTransacao(2L, 10.0, "D"));
    }

    @Test
    void realizarTransacaoSaldoInsuficiente() {
        Conta conta = new Conta(1L, 5.0);
        when(contaRepository.findById(1L)).thenReturn(Optional.of(conta));
        assertThrows(RuntimeException.class, () -> transacaoService.realizarTransacao(1L, 10.0, "D"));
    }

    @Test
    void realizarTransacaoFormaPagamentoInvalida() {
        Conta conta = new Conta(1L, 100.0);
        when(contaRepository.findById(1L)).thenReturn(Optional.of(conta));
        assertThrows(RuntimeException.class, () -> transacaoService.realizarTransacao(1L, 10.0, "X"));
    }
}
