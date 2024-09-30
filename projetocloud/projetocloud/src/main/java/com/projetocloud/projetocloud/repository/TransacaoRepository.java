package com.projetocloud.projetocloud.repository;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.projetocloud.projetocloud.model.Cartao;
import com.projetocloud.projetocloud.model.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, Integer> {

    @Query("SELECT t FROM Transacao t where t.cartao.id = ?1 and t.dataTransacao >= ?2 and t.dataTransacao <= ?3")
    List<Transacao> getCartaoData(int cartao_id, LocalDateTime inicio, LocalDateTime fim);
}
