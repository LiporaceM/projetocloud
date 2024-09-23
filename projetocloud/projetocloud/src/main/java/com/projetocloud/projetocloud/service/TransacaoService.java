package com.projetocloud.projetocloud.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetocloud.projetocloud.model.Cartao;
import com.projetocloud.projetocloud.model.Transacao;
import com.projetocloud.projetocloud.repository.CartaoRepository;
import com.projetocloud.projetocloud.repository.TransacaoRepository;

@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository repository;

    @Autowired
    private CartaoRepository cartaoRepository;

    private final long TRANSACTION_TIME_INTERVAL = 3;

    public Transacao autorizacaoTransacao(Cartao cartao, double valor, String comerciante) throws Exception {

        // Cartao está ativo
        if (cartao.getAtivo() == false) {
            throw new Exception("Cartão não está ativo");
        }

        // Cartão tem limite para compra
        if (cartao.getLimite() < valor) {
            throw new Exception("Cartão sem limite para efetuar a compra");
        }

        //Verificar regras
        this.verificarAntifraude(cartao, valor, comerciante);

        //Passou nas regras, criar um nova transação
        Transacao transacao = new Transacao();
        transacao.setComerciante(comerciante);
        transacao.setDataTransacao(LocalDateTime.now());
        transacao.setValor(valor);

        //Salva na base de dados
        repository.save(transacao);

        //Diminui o limite do cartao
        cartao.setLimite(cartao.getLimite() - valor);

        //Associa a transacao ao cartao 
        cartao.getTransacoes().add(transacao);

        //Atualiza a base de dados com a nova transação para o cartao e atualiza o limite
        cartaoRepository.save(cartao);

        return transacao;

    }

    private void verificarAntifraude(Cartao cartao, double valor, String comerciante) throws Exception {

        // Valida se o cartão tem transações nos ultimos 3 minutos
        LocalDateTime localDateTime = LocalDateTime.now().minus(TRANSACTION_TIME_INTERVAL, ChronoUnit.MINUTES);

        List<Transacao> ultimasTransacoes = cartao
                .getTransacoes()
                .stream()
                .filter(x -> x.getDataTransacao().isAfter(localDateTime))
                .toList();

        if (ultimasTransacoes.size() >= 3) {
            throw new Exception("Cartão utilizado muitas vezes em um período curto");
        }

    }

}


