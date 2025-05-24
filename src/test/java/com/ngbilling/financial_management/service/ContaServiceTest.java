package com.ngbilling.financial_management.service;

import com.ngbilling.financial_management.model.Conta;
import com.ngbilling.financial_management.repository.ContaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ContaServiceTest {

    private ContaRepository contaRepository;
    private ContaService contaService;

    @BeforeEach
    void setUp() {
        contaRepository = Mockito.mock(ContaRepository.class);
        contaService = new ContaService();
        contaService.contaRepository = contaRepository;
    }

    @Test
    void criarContaComTitular() {
        Conta contaMock = new Conta(1L, "Titular Teste");
        when(contaRepository.save(any(Conta.class))).thenReturn(contaMock);

        Conta conta = contaService.criarConta("Titular Teste");
        assertNotNull(conta);
        assertEquals("Titular Teste", conta.getTitular());
    }

    @Test
    void criarContaComNumeroESaldo() {
        when(contaRepository.existsById(1L)).thenReturn(false);
        Conta contaMock = new Conta(1L, 100.0);
        when(contaRepository.save(any(Conta.class))).thenReturn(contaMock);

        Conta conta = contaService.criarConta(1L, 100.0);
        assertNotNull(conta);
        assertEquals(1L, conta.getNumero());
        assertEquals(100.0, conta.getSaldo());
    }

    @Test
    void criarContaDuplicadaLancaExcecao() {
        when(contaRepository.existsById(1L)).thenReturn(true);
        assertThrows(IllegalArgumentException.class, () -> contaService.criarConta(1L, 100.0));
    }

    @Test
    void buscarContaExistente() {
        Conta contaMock = new Conta(1L, 100.0);
        when(contaRepository.findById(1L)).thenReturn(Optional.of(contaMock));

        Conta conta = contaService.buscarConta(1L);
        assertNotNull(conta);
        assertEquals(1L, conta.getNumero());
    }

    @Test
    void buscarContaInexistente() {
        when(contaRepository.findById(2L)).thenReturn(Optional.empty());
        Conta conta = contaService.buscarConta(2L);
        assertNull(conta);
    }
}
