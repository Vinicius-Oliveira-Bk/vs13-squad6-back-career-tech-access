package br.com.dbc.vemser.repository;

import br.com.dbc.vemser.model.entities.Notificacao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NotificacaoLogRepository extends MongoRepository<Notificacao, String> {

    List<Notificacao> findAllByDiaHora(LocalDateTime diaHora);

    List<Notificacao> findAll();

    List<Notificacao> findByDiaHoraBetween(LocalDateTime dataInicio, LocalDateTime dataFim);
}
