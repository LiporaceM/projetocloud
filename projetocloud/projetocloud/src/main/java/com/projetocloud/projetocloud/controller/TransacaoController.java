package com.projetocloud.projetocloud.controller;

import com.projetocloud.projetocloud.model.Transacao;
import com.projetocloud.projetocloud.model.Usuario;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/transacao")
public class TransacaoController {
    private static List<Transacao> Transacoes = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<Transacao>> getTransacao() { return new ResponseEntity(Transacoes, HttpStatus.OK);}

    @GetMapping("{id}")
    public ResponseEntity<Transacao> getTransacaoById(@PathVariable("id") int id) {
        Transacao response = null;

        for (Transacao transacao : Transacoes) {
            if (transacao.getId() == id) {
                response = transacao;
                break;
            }
        }

        if (response == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<Transacao> saveTransacao(@Valid @RequestBody Transacao transacao) {
        Transacoes.add(transacao);
        return new ResponseEntity<>(transacao, HttpStatus.CREATED);
    }
}