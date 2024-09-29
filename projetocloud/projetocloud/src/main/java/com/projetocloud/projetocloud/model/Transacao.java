package com.projetocloud.projetocloud.model;


import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Column
    public LocalDateTime dataTransacao;

    @Column
    public double valor;

    @Column
    public String comerciante;

    // Adicione a relação ManyToOne com o Cartao
    @ManyToOne
    @JoinColumn(name = "cartao_id") // Chave estrangeira que aponta para Cartao
    private Cartao cartao;
}

