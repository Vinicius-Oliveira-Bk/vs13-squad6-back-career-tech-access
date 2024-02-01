package br.com.dbc.vemser.model.entities;

import br.com.dbc.vemser.model.enums.TipoUsuarioEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "USUARIO")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USUARIO")
    @SequenceGenerator(name = "SEQ_USUARIO", sequenceName = "SEQ_USUARIO", allocationSize = 1)
    @Column(name = "id")
    private Long id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;
    @Column(name = "cpf")
    private String cpf;
    @Column(name = "email")
    private String email;
    @Column(name = "senha")
    private String senha;
    @Column(name = "eh_pcd")
    private Character ehPcd;
    @Column(name = "tipo_deficiencia")
    private String tipoDeficiencia;
    @Column(name = "certificado_deficiencia_gov")
    private String certificadoDeficienciaGov;
    @Column(name = "tipo_usuario")
    private TipoUsuarioEnum tipoUsuario;
    @Column(name = "imagem_documento")
    private String imagemDocumento;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contato> contatos;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "USUARIO_ENDERECO",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_endereco")
    )private List<Endereco> enderecos;

    @OneToOne(mappedBy = "usuario")
    private ProfissionalMentor profissionalMentor;

    @OneToOne(mappedBy = "usuario")
    private Cliente cliente;
}