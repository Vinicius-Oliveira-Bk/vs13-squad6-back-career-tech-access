package br.com.dbc.vemser.repository;

import br.com.dbc.vemser.model.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByCpf(String cpf);

    Usuario findByEmail(String email);


    @Query(value = """
            SELECT * FROM USUARIO U
                LEFT JOIN USUARIO_ENDERECO UE ON U.ID = UE.ID_USUARIO
                LEFT JOIN ENDERECO E ON UE.ID_ENDERECO = E.ID
                LEFT JOIN CONTATO C1 ON U.ID = C1.ID_USUARIO
            WHERE :idUsuario = -1 OR  U.ID = :idUsuario
            """, nativeQuery = true)
    Set<Usuario> relatorioUsuario(Long idUsuario);
}
