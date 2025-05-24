package com.ngbilling.financial_management.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Conta {
    @Id
    private Long numero;
    private String titular;
    private Double saldo;

    public Conta(Long numero, String titular) {
        this.numero = numero;
        this.titular = titular;
        this.saldo = 0.0;
    }

    public Conta(Long numero, Double saldo) {
        this.numero = numero;
        this.saldo = saldo;
        this.titular = null;
    }
}