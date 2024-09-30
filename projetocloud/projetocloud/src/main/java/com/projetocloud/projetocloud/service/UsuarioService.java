package com.projetocloud.projetocloud.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetocloud.projetocloud.exception.UsuarioException;
import com.projetocloud.projetocloud.model.Cartao;
import com.projetocloud.projetocloud.model.Usuario;
import com.projetocloud.projetocloud.repository.CartaoRepository;
import com.projetocloud.projetocloud.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CartaoRepository cartaoRepository;

    public Usuario criarUsuario(Usuario usuario) throws UsuarioException {
        Optional<Usuario> optUsuario = usuarioRepository.findUsuarioByCpf(usuario.getCpf());
    
        if (optUsuario.isPresent()) {
            throw new UsuarioException("Usuario com cpf informado já cadastrado");
        }
    
        // Insere o usuário na base de dados primeiro
        Usuario usuarioCriado = usuarioRepository.save(usuario);
    
        
        // Verifica e salva cada cartão associado ao usuário
        List<Cartao> cartoes = new ArrayList<>(usuario.getCartoes());
        for (Cartao cartao : cartoes) {
        try {
            associarCartao(cartao, usuarioCriado);
        } catch (Exception e) {
            e.printStackTrace();
        }
}

    
        return usuarioCriado;
    }
    

    public Usuario buscaUsuario(int id) {
        return this.findUsuario(id);
    }

    public void associarCartao(Cartao cartao, Usuario usuario) throws Exception {
        // Verifica se o cartão está ativo
        if (!cartao.getAtivo()) {
            throw new Exception("Não posso associar um cartão inativo ao usuário");
        }
    
        // Associa o usuário ao cartão
        cartao.setUsuario(usuario); // Adiciona a referência do usuário no cartão
        usuario.associarCartao(cartao); // Adiciona o cartão à lista de cartões do usuário
    
        // Salva o cartão no banco de dados
        cartaoRepository.save(cartao);
    }

    private Usuario findUsuario(int id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.orElse(null);
    }
}
