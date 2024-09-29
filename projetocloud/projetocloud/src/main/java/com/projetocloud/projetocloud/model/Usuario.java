package com.projetocloud.projetocloud.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


@Data
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @NotBlank(message = "Campo nome é obrigatório.")
    private String nome;

    @Column
    @NotBlank(message = "Campo cpf é obrigatório.")
    private String cpf;

    @Column
    @NotNull(message = "Campo data é obrigatório")
    private LocalDateTime dataNascimento;

    @Column
    @NotBlank(message = "Campo e-mail é obrigatório.")
    @Email(message = "Campo e-mail não está em formato adequado.")
    private String email;

    @Column
    @NotBlank(message = "Campo endereço é obrigatório.")
    private String endereco;

    @Column
    @NotBlank(message = "Campo celular é obrigatório.")
    private String celular;

    @OneToMany(mappedBy = "usuario") // 'usuario' é o nome da propriedade na classe Cartao
    private List<Cartao> cartoes;

    public void associarCartao(Cartao cartao) {
        this.cartoes.add(cartao);
    }
}

