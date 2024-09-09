package com.projetocloud.projetocloud.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class Usuario {
    private UUID id;
    @NotBlank(message = "Campo nome é obrigatório.")
    private String nome;

    @NotBlank(message = "Campo cpf é obrigatório.")
    private String cpf;

    @NotBlank(message = "Campo data é obrigatório")
    private LocalDateTime dataNascimento;

    @NotBlank(message = "Campo e-mail é obrigatório.")
    @Email(message = "Campo e-mail não está em formato adequado.")
    private String email;

    @NotBlank(message = "Campo endereço é obrigatório.")
    private String endereco;

    @NotBlank(message = "Campo celular é obrigatório.")
    private String celular;

    private List<Cartao> cartoes;

    public void associarCartao(Cartao cartao) {
        this.cartoes.add(cartao);
    }
}
