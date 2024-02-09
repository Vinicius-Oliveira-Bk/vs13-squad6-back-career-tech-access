package br.com.dbc.vemser.repository;

import br.com.dbc.vemser.model.entities.Agenda;
import br.com.dbc.vemser.model.enums.StatusAgendaEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
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

    @Query(value = """
            SELECT * FROM AGENDA A
            	JOIN PROFISSIONAL_MENTOR PM ON (A.ID_MENTOR = PM.ID)
                LEFT JOIN CLIENTE C ON (A.ID_CLIENTE = C.ID)
             	WHERE   (TO_NUMBER(:ID_MENTOR)  IS NULL     OR TO_NUMBER(:ID_MENTOR)    = A.ID_MENTOR)
                AND   	(TO_NUMBER(:ID_CLIENTE) IS NULL     OR TO_NUMBER(:ID_CLIENTE)   = A.ID_CLIENTE)
                AND   	(TO_NUMBER(:STATUS)     IS NULL     OR TO_NUMBER(:STATUS)       = A.STATUS)
                AND     (TO_NUMBER(:ID_AGENDA)  IS NULL     OR TO_NUMBER(:ID_AGENDA)    = A.ID)
            """,
            countQuery = """
            SELECT COUNT(*) FROM AGENDA A
                JOIN PROFISSIONAL_MENTOR PM ON (A.ID_MENTOR = PM.ID)
                LEFT JOIN CLIENTE C ON (A.ID_CLIENTE = C.ID)
                WHERE   (TO_NUMBER(:ID_MENTOR)  IS NULL     OR TO_NUMBER(:ID_MENTOR)    = A.ID_MENTOR)
                AND   	(TO_NUMBER(:ID_CLIENTE) IS NULL     OR TO_NUMBER(:ID_CLIENTE)   = A.ID_CLIENTE)
                AND   	(TO_NUMBER(:STATUS)     IS NULL     OR TO_NUMBER(:STATUS)       = A.STATUS)
                AND     (TO_NUMBER(:ID_AGENDA)  IS NULL     OR TO_NUMBER(:ID_AGENDA)    = A.ID)
            """,
            nativeQuery = true)
    Page<Agenda> findAll(Pageable pageable,
                         @Param("ID_AGENDA") @Nullable Long idAgenda,
                         @Param("STATUS") @Nullable Integer status,
                         @Param("ID_MENTOR") @Nullable Long idProfissionalMentor,
                         @Param("ID_CLIENTE") @Nullable Long idCliente);



    @Query(value = """
            SELECT * FROM AGENDA A
            	JOIN PROFISSIONAL_MENTOR PM ON (A.ID_MENTOR = PM.ID)
                LEFT JOIN CLIENTE C ON (A.ID_CLIENTE = C.ID)
             	WHERE   (TO_NUMBER(:ID_MENTOR)  IS NULL     OR TO_NUMBER(:ID_MENTOR)    = A.ID_MENTOR)
                AND   	(TO_NUMBER(:STATUS)     IS NULL     OR TO_NUMBER(:STATUS)       = A.STATUS)
                AND   	(TO_NUMBER(:ID_CLIENTE) = A.ID_CLIENTE)
            """,
            countQuery = """
            SELECT COUNT(*) FROM AGENDA A
                JOIN PROFISSIONAL_MENTOR PM ON (A.ID_MENTOR = PM.ID)
                LEFT JOIN CLIENTE C ON (A.ID_CLIENTE = C.ID)
                WHERE   (TO_NUMBER(:ID_MENTOR)  IS NULL     OR TO_NUMBER(:ID_MENTOR)    = A.ID_MENTOR)
                AND   	(TO_NUMBER(:STATUS)     IS NULL     OR TO_NUMBER(:STATUS)       = A.STATUS)
                AND   	(TO_NUMBER(:ID_CLIENTE) = A.ID_CLIENTE)
            """,
            nativeQuery = true)
    Page<Agenda> findAllClienteLogado(Pageable pageable,
                                      @Param("STATUS") @Nullable Integer status,
                                      @Param("ID_MENTOR") @Nullable Long idProfissionalMentor,
                                      @Param("ID_CLIENTE") Long idCliente);


    @Query(value = """
            SELECT * FROM AGENDA A
            	JOIN PROFISSIONAL_MENTOR PM ON (A.ID_MENTOR = PM.ID)
                LEFT JOIN CLIENTE C ON (A.ID_CLIENTE = C.ID)
             	WHERE   (TO_NUMBER(:ID_MENTOR) = A.ID_MENTOR)
                AND   	(TO_NUMBER(:STATUS)     IS NULL     OR TO_NUMBER(:STATUS)       = A.STATUS)
            """,
            countQuery = """
            SELECT COUNT(*) FROM AGENDA A
                JOIN PROFISSIONAL_MENTOR PM ON (A.ID_MENTOR = PM.ID)
                LEFT JOIN CLIENTE C ON (A.ID_CLIENTE = C.ID)
                WHERE   (TO_NUMBER(:ID_MENTOR) = A.ID_MENTOR)
                AND   	(TO_NUMBER(:STATUS)     IS NULL     OR TO_NUMBER(:STATUS)       = A.STATUS)
            """,
            nativeQuery = true)
    Page<Agenda> findAllProfissionalLogado(Pageable pageable,
                                           @Param("STATUS") @Nullable Integer status,
                                           @Param("ID_MENTOR") Long idProfissionalMentor);


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
