package br.com.dbc.vemser.repository;

import br.com.dbc.vemser.model.entities.ProfissionalMentor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfissionalMentorRepository extends JpaRepository<ProfissionalMentor, Long> {}
