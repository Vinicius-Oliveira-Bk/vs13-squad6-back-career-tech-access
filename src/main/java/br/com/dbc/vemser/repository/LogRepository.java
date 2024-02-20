package br.com.dbc.vemser.repository;

import br.com.dbc.vemser.model.entities.Log;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LogRepository extends MongoRepository<Log, String> {

    List<Log> findAllByStatusCode(String statusCode);

    List<Log> findAll();

    List<Log> findByDataBetween(LocalDate dataStart, LocalDate dataEnd);


}
