package com.projetocloud.projetocloud.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

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

    // Adicionando a referência ao usuário
    @ManyToOne
    @JoinColumn(name = "usuario_id") // Chave estrangeira que aponta para Usuario
    private Usuario usuario;

    @OneToMany(mappedBy = "cartao")
    private List<Transacao> transacoes;
}



