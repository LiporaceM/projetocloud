package com.projetocloud.projetocloud.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private Boolean ativo;

    @Column
    private int CVV;

    @Column
    private double limite;

    @Column
    private String numero;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "usuario_id") // Chave estrangeira que aponta para Usuario
    private Usuario usuario;

    @OneToMany(mappedBy = "cartao")
    private List<Transacao> transacoes;
}



