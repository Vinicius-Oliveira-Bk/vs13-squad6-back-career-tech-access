package br.com.dbc.vemser.repository;

import br.com.dbc.vemser.model.entities.Agenda;
import br.com.dbc.vemser.model.entities.Contato;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {

    @Query(value = """
            SELECT * FROM CONTATO C
            	JOIN USUARIO U ON (C.ID_USUARIO = U.ID)
             	WHERE   (TO_NUMBER(:ID_USUARIO) = U.ID)
                AND   	(TO_NUMBER(:TIPO)     IS NULL     OR TO_NUMBER(:TIPO)       = C.TIPO)
            """,
            countQuery = """
            SELECT COUNT(*) FROM CONTATO C
            	JOIN USUARIO U ON (C.ID_USUARIO = U.ID)
             	WHERE   (TO_NUMBER(:ID_USUARIO) = U.ID)
                AND   	(TO_NUMBER(:TIPO)     IS NULL     OR TO_NUMBER(:TIPO)       = C.TIPO)
            """,
            nativeQuery = true)
    Page<Contato> findAllUsuarioLogado(Pageable pageable,
                                      @Param("TIPO") @Nullable Integer tipo,
                                      @Param("ID_USUARIO") Long idUsuario);

    @Query(value = """
            SELECT * FROM CONTATO C
            	JOIN USUARIO U ON (C.ID_USUARIO = U.ID)
             	WHERE   (TO_NUMBER(:ID_USUARIO) IS NULL     OR TO_NUMBER(:ID_USUARIO) = U.ID)
             	AND     (TO_NUMBER(:ID_CONTATO) IS NULL     OR TO_NUMBER(:ID_CONTATO) = C.ID)
                AND   	(TO_NUMBER(:TIPO)       IS NULL     OR TO_NUMBER(:TIPO)     = C.TIPO)
            """,
            countQuery = """
            SELECT COUNT(*) FROM CONTATO C
            	JOIN USUARIO U ON (C.ID_USUARIO = U.ID)
             	WHERE   (TO_NUMBER(:ID_USUARIO) IS NULL     OR TO_NUMBER(:ID_USUARIO) = U.ID)
             	AND     (TO_NUMBER(:ID_CONTATO) IS NULL     OR TO_NUMBER(:ID_CONTATO) = C.ID)
                AND   	(TO_NUMBER(:TIPO)       IS NULL     OR TO_NUMBER(:TIPO)     = C.TIPO)
            """,
            nativeQuery = true)
    Page<Contato> findAll(Pageable pageable,
                          @Param("TIPO") @Nullable Integer tipo,
                          @Param("ID_CONTATO") @Nullable Long idContato,
                          @Param("ID_USUARIO") @Nullable Long idUsuario);
}
