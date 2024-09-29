package com.projetocloud.projetocloud.controller;

import com.projetocloud.projetocloud.model.Cartao;
import com.projetocloud.projetocloud.model.Transacao;
import com.projetocloud.projetocloud.repository.CartaoRepository;
import com.projetocloud.projetocloud.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cartoes")
public class CartaoController {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private TransacaoRepository transacaoRepository;

    // Criar um novo cartão
    @PostMapping
    public ResponseEntity<Cartao> criarCartao(@RequestBody Cartao cartao) {
        Cartao novoCartao = cartaoRepository.save(cartao);
        return ResponseEntity.ok(novoCartao);
    }

    // Listar todos os cartões
    @GetMapping
    public ResponseEntity<List<Cartao>> listarCartoes() {
        List<Cartao> cartoes = cartaoRepository.findAll();
        return ResponseEntity.ok(cartoes);
    }

    // Buscar cartão por ID
    @GetMapping("/{id}")
    public ResponseEntity<Cartao> buscarCartaoPorId(@PathVariable int id) {
        Optional<Cartao> cartao = cartaoRepository.findById(id);
        if (cartao.isPresent()) {
            return ResponseEntity.ok(cartao.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Buscar transações associadas a um cartão específico
    @GetMapping("/{id}/transacoes")
    public ResponseEntity<List<Transacao>> buscarTransacoesPorCartao(@PathVariable int id) {
        Optional<Cartao> cartao = cartaoRepository.findById(id);
        if (cartao.isPresent()) {
            List<Transacao> transacoes = cartao.get().getTransacoes();
            return ResponseEntity.ok(transacoes);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Atualizar um cartão
    @PutMapping("/{id}")
    public ResponseEntity<Cartao> atualizarCartao(@PathVariable int id, @RequestBody Cartao cartaoAtualizado) {
        Optional<Cartao> cartaoExistente = cartaoRepository.findById(id);
        if (cartaoExistente.isPresent()) {
            Cartao cartao = cartaoExistente.get();
            cartao.setAtivo(cartaoAtualizado.getAtivo());
            cartao.setCVV(cartaoAtualizado.getCVV());
            cartao.setLimite(cartaoAtualizado.getLimite());
            cartao.setNumero(cartaoAtualizado.getNumero());
            cartaoRepository.save(cartao);
            return ResponseEntity.ok(cartao);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Deletar um cartão
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCartao(@PathVariable int id) {
        Optional<Cartao> cartao = cartaoRepository.findById(id);
        if (cartao.isPresent()) {
            cartaoRepository.delete(cartao.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

