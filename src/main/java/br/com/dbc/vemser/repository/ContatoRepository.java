package br.com.dbc.vemser.repository;

import br.com.dbc.vemser.model.entities.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {


    List<Contato> findByUsuario_Id(Long id);
}
