package com.ngbilling.financial_management.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long numeroConta;
    private Double valor;
    private String tipo;
    private LocalDateTime dataHora;

    public Transacao(Long id, Long numeroConta, Double valor, String tipo) {
        this.id = id;
        this.numeroConta = numeroConta;
        this.valor = valor;
        this.tipo = tipo;
        this.dataHora = LocalDateTime.now();
    }
}

