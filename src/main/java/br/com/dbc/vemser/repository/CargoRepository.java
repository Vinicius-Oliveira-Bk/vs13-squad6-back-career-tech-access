package br.com.dbc.vemser.repository;

import br.com.dbc.vemser.model.entities.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {
    Cargo findByNome(String nome);
}
