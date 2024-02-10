package br.com.dbc.vemser.repository;

import br.com.dbc.vemser.model.entities.Endereco;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    @Query(value = """
            SELECT * FROM ENDERECO 			E
                JOIN	USUARIO_ENDERECO 	UE	ON	(E.ID			=	UE.ID_ENDERECO)
                JOIN 	USUARIO 			U 	ON 	(UE.ID_USUARIO 	= 	U.ID)
                WHERE   (TO_NUMBER(:ID_USUARIO) 	IS NULL     OR TO_NUMBER(:ID_USUARIO) 	= U.ID)
                AND     (TO_NUMBER(:ID_ENDERECO)	IS NULL     OR TO_NUMBER(:ID_ENDERECO) 	= E.ID)
                AND   	(TO_NUMBER(:TIPO)       	IS NULL     OR TO_NUMBER(:TIPO)     	= E.TIPO)
            """,
            countQuery = """
            SELECT COUNT(*) FROM ENDERECO 			E
                JOIN	USUARIO_ENDERECO 	UE	ON	(E.ID			=	UE.ID_ENDERECO)
                JOIN 	USUARIO 			U 	ON 	(UE.ID_USUARIO 	= 	U.ID)
                WHERE   (TO_NUMBER(:ID_USUARIO) 	IS NULL     OR TO_NUMBER(:ID_USUARIO) 	= U.ID)
                AND     (TO_NUMBER(:ID_ENDERECO)	IS NULL     OR TO_NUMBER(:ID_ENDERECO) 	= E.ID)
                AND   	(TO_NUMBER(:TIPO)       	IS NULL     OR TO_NUMBER(:TIPO)     	= E.TIPO)
            """,
            nativeQuery = true)
    Page<Endereco> findAll(Pageable pageable,
                           @Param("ID_USUARIO")     @Nullable Long idUsuario,
                           @Param("ID_ENDERECO")    @Nullable Long idEndereco,
                           @Param("TIPO")           @Nullable Integer tipo);

    @Query(value = """
            SELECT * FROM ENDERECO 			E
                JOIN	USUARIO_ENDERECO 	UE	ON	(E.ID			=	UE.ID_ENDERECO)
                JOIN 	USUARIO 			U 	ON 	(UE.ID_USUARIO 	= 	U.ID)
                WHERE   (TO_NUMBER(:ID_USUARIO) = U.ID)
                AND   	(TO_NUMBER(:TIPO)       	IS NULL     OR TO_NUMBER(:TIPO)     	= E.TIPO)
            """,
            countQuery = """
            SELECT * FROM ENDERECO 			E
                JOIN	USUARIO_ENDERECO 	UE	ON	(E.ID			=	UE.ID_ENDERECO)
                JOIN 	USUARIO 			U 	ON 	(UE.ID_USUARIO 	= 	U.ID)
                WHERE   (TO_NUMBER(:ID_USUARIO) = U.ID)
                AND   	(TO_NUMBER(:TIPO)       	IS NULL     OR TO_NUMBER(:TIPO)     	= E.TIPO)
            """,
            nativeQuery = true)
    Page<Endereco> findAllUsuario(Pageable pageable,
                                  @Param("ID_USUARIO")  Long idUsuario,
                                  @Param("TIPO")        @Nullable Integer tipo);
}
