/*package com.projetocloud.projetocloud.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetocloud.projetocloud.exception.AutorizacaoException;
import com.projetocloud.projetocloud.model.Cartao;
import com.projetocloud.projetocloud.model.Transacao;
import com.projetocloud.projetocloud.repository.TransacaoRepository;

@Service
public class AutorizacaoService {

    @Autowired
    private TransacaoRepository transacaoRepository;

    public void autorizarTransacao(Cartao cartao, Transacao transacao) throws AutorizacaoException {
        
        // Regra 1: O valor da transação não deve exceder o limite disponível.
        if (transacao.getValor() > cartao.getLimite()) {
            throw new AutorizacaoException("Limite insuficiente");
        }

        // Regra 2: Nenhuma transação deve ser aceita quando o cartão não está ativo.
        if (!cartao.getAtivo()) {
            throw new AutorizacaoException("Cartão não ativo");
        }

        // Regra 3: Não deve haver mais de 3 transações em um intervalo de 2 minutos.
        List<Transacao> transacoesRecentes = transacaoRepository.findByCartaoAndDataBetween(
            cartao,
            LocalDateTime.now().minusMinutes(2),
            LocalDateTime.now()
        );
        if (transacoesRecentes.size() >= 3) {
            throw new AutorizacaoException("Alta frequência de transações em um pequeno intervalo");
        }

        // Regra 4: Não deve haver mais de 2 transações semelhantes em um intervalo de 2 minutos.
        long transacoesSemelhantes = transacoesRecentes.stream()
            .filter(t -> t.getValor() == (transacao.getValor()) && t.getComerciante().equals(transacao.getComerciante()))
            .count();

        if (transacoesSemelhantes >= 2) {
            throw new AutorizacaoException("Transação duplicada");
        }

        // Se todas as regras forem passadas, autoriza a transação
        transacaoRepository.save(transacao);
    }
}
*/
