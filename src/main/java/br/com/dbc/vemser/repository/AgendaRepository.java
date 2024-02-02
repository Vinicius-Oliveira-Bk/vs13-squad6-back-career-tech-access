package br.com.dbc.vemser.repository;

import br.com.dbc.vemser.model.entities.Agenda;
import br.com.dbc.vemser.model.enums.StatusAgendaEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long> {


//    @Query(value = "select * " +
//            "         from PESSOA " +
//            "        where upper(nome) like upper(:nome)",
//            countQuery = "select count(*) " +
//                    "         from PESSOA " +
//                    "        where upper(nome) like upper(:nome)", nativeQuery = true)
//    Page<PessoaEntity> getPorQualquerNomePaginadoNativo(String nome, Pageable pageable);
    List<Agenda> findByCliente_Id(Long id);

    List<Agenda> findByProfissionalMentor_Id(Long id);

    List<Agenda> findByStatusAgendaEnum(StatusAgendaEnum statusAgendaEnum);
}
