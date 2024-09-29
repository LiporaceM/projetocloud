package com.projetocloud.projetocloud.repository;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.projetocloud.projetocloud.model.Cartao;
import com.projetocloud.projetocloud.model.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, Integer> {
    List<Transacao> findByCartaoAndDataBetween(Cartao cartao, LocalDateTime inicio, LocalDateTime fim);
}
