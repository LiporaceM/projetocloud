package com.projetocloud.projetocloud.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity

public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Column
    @NotBlank(message = "Campo nome é obrigatório.")
    private String nome;

    @Column
    @NotBlank(message = "Campo cpf é obrigatório.")
    private String cpf;

    @Column
    @NotBlank(message = "Campo data é obrigatório")
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

    @OneToMany
    @JoinColumn(referencedColumnName = "id", name = "usuario_id")
    private List<Cartao> cartoes;

    public void associarCartao(Cartao cartao) {
        this.cartoes.add(cartao);
    }
}
