package br.com.dbc.vemser.repository;

import br.com.dbc.vemser.model.entities.ProfissionalMentor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfissionalMentorRepository extends JpaRepository<ProfissionalMentor, Long> {
    Optional<ProfissionalMentor> findByUsuario_Id(Long id);
}
