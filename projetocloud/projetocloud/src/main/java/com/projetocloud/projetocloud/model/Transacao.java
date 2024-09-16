package com.projetocloud.projetocloud.model;


import java.time.LocalDateTime;
import java.util.UUID;

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
}
