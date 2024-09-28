package com.projetocloud.projetocloud.model;


import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Column
    public Boolean ativo;

    @Column
    public int CVV;

    @Column
    public double limite;

    @Column
    public String numero;

    @OneToMany
    @JoinColumn(referencedColumnName = "id", name = "cartao_id")
    public List<Transacao> transacoes;


}
