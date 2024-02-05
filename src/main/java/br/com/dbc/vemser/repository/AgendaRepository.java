package br.com.dbc.vemser.repository;

import br.com.dbc.vemser.model.entities.Agenda;
import br.com.dbc.vemser.model.enums.StatusAgendaEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

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

    @Query(value = "SELECT * FROM AGENDA AG\n" +
            "    LEFT JOIN CLIENTE CL on CL.ID = AG.ID_CLIENTE\n" +
            "    LEFT JOIN USUARIO U1 on U1.ID = CL.ID_USUARIO\n" +
            "    LEFT JOIN CONTATO CO1 on CO1.ID = U1.ID\n" +
            "    LEFT JOIN USUARIO_ENDERECO UE1 on U1.ID = UE1.ID_USUARIO\n" +
            "    LEFT JOIN ENDERECO E1 on E1.ID = UE1.ID_ENDERECO\n" +
            "    LEFT JOIN AREA_INTERESSE AI on AI.ID_CLIENTE = CL.ID\n" +
            "    LEFT JOIN PROFISSIONAL_MENTOR PM on PM.ID = AG.ID_MENTOR\n" +
            "    LEFT JOIN USUARIO U2 on U2.ID = PM.ID_USUARIO\n" +
            "    LEFT JOIN CONTATO CO2 on CO2.ID = U2.ID\n" +
            "    LEFT JOIN USUARIO_ENDERECO UE2 on U2.ID = UE2.ID_USUARIO\n" +
            "    LEFT JOIN ENDERECO E2 on E2.ID = UE2.ID_ENDERECO\n" +
            "    LEFT JOIN AREA_ATUACAO AA on AA.ID_PROFISSIONAL = PM.ID\n" +
            "WHERE :idAgenda = -1 OR AG.ID = :idAgenda", nativeQuery = true)
    Set<Agenda> relatorioAgenda(Long idAgenda);
}
