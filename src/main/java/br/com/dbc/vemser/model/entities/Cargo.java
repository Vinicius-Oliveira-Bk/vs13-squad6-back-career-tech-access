package br.com.dbc.vemser.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
<<<<<<< Updated upstream
import lombok.*;
=======
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
>>>>>>> Stashed changes
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "CARGO")
@NoArgsConstructor
@AllArgsConstructor
public class Cargo implements GrantedAuthority {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private int idCargo;

    @Column(name = "NOME")
    private String nome;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "USUARIO_CARGO",
            joinColumns = @JoinColumn(name = "ID_CARGO"),
            inverseJoinColumns = @JoinColumn(name = "ID_USUARIO")
    )
    private Set<Usuario> usuarios;

    @Override
    public String getAuthority() {
        return nome;
    }
}
