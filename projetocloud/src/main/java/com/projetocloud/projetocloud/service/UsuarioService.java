package com.projetocloud.projetocloud.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.projetocloud.projetocloud.model.Cartao;
import com.projetocloud.projetocloud.model.Usuario;
import org.springframework.stereotype.Service;



@Service
public class UsuarioService {
    private static List<Usuario> database = new ArrayList<>();

    public Usuario criarUsuario(String nome, String cpf, LocalDateTime dataNascimento) {
        Usuario usuario = new Usuario();

        //TODO: Validar CPF
        usuario.setCpf(cpf);

        usuario.setNome(nome);
        usuario.setDataNascimento(dataNascimento);
        usuario.setId(UUID.randomUUID());

        database.add(usuario);

        return usuario;
    }

    public class ValidaCPF {

        public static boolean isCPF(String CPF) {
            // Remover caracteres especiais (pontos e traço)
            CPF = CPF.replace(".", "").replace("-", "");

            // Verifica se o CPF tem 11 dígitos
            if (CPF.length() != 11) {
                return false;
            }

            // Verifica se todos os dígitos são iguais (CPFs inválidos conhecidos)
            if (CPF.matches("(\\d)\\1{10}")) {
                return false;
            }

            // Calcula o primeiro dígito verificador
            int soma = 0, peso = 10;
            for (int i = 0; i < 9; i++) {
                soma += (CPF.charAt(i) - '0') * peso--;
            }
            int primeiroDigito = 11 - (soma % 11);
            if (primeiroDigito > 9) {
                primeiroDigito = 0;
            }

            // Verifica se o primeiro dígito verificador é válido
            if (primeiroDigito != (CPF.charAt(9) - '0')) {
                return false;
            }

            // Calcula o segundo dígito verificador
            soma = 0;
            peso = 11;
            for (int i = 0; i < 10; i++) {
                soma += (CPF.charAt(i) - '0') * peso--;
            }
            int segundoDigito = 11 - (soma % 11);
            if (segundoDigito > 9) {
                segundoDigito = 0;
            }

            // Verifica se o segundo dígito verificador é válido
            return segundoDigito == (CPF.charAt(10) - '0');
        }

        public static void main(String[] args) {
            String cpf = "123.456.789-09"; // Exemplo de CPF

            if (isCPF(cpf)) {
                System.out.println("CPF válido");
            } else {
                System.out.println("CPF inválido");
            }
        }
    }

    public Usuario buscaUsuario(UUID id) {
        return this.findUsuario(id);
    }

    public void associarCartao(Cartao cartao, UUID id) throws Exception {
        //Buscar usuario

        Usuario usuario = this.findUsuario(id);

        //Valida se encontrou o usuario
        if (usuario == null) {
            throw new Exception("Não encontrei o usuario");
        }

        //valida se o cartão está ativo
        if (cartao.getAtivo() == false) {
            throw new Exception("Não posso associar um cartão inativo ao usuário");
        }

        //Associa um cartão a um usuario
        usuario.associarCartao(cartao);

    }

    private Usuario findUsuario(UUID id) {
        for (Usuario item : database) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

}