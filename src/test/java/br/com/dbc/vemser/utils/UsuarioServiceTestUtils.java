package br.com.dbc.vemser.utils;

import br.com.dbc.vemser.model.dtos.request.UsuarioRequestAdminDTO;
import br.com.dbc.vemser.model.dtos.request.UsuarioRequestDTO;
import br.com.dbc.vemser.model.dtos.response.CargoResponseDTO;
import br.com.dbc.vemser.model.dtos.response.UsuarioResponseCompletoDTO;
import br.com.dbc.vemser.model.dtos.response.UsuarioResponseDTO;
import br.com.dbc.vemser.model.entities.Cargo;
import br.com.dbc.vemser.model.entities.Contato;
import br.com.dbc.vemser.model.entities.Endereco;
import br.com.dbc.vemser.model.entities.Usuario;
import br.com.dbc.vemser.model.enums.CargoEnum;
import br.com.dbc.vemser.model.enums.TipoEnum;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UsuarioServiceTestUtils {

    public static Usuario createUsuarioDefault() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Usuário Padrão");
        usuario.setCpf("12345678900");
        usuario.setDataNascimento(LocalDate.parse("2000-01-01"));
        usuario.setEmail("usuario@usuario.com");
        usuario.setSenha("12345678");
        usuario.setEhPcd('S');
        usuario.setTipoDeficiencia("Física");
        usuario.setCertificadoDeficienciaGov("http://certificado.com");
        usuario.setImagemDocumento("http://imagem.com");
        usuario.setAtivo(true);
        usuario.setCargos(Set.of(createCargoUsuario()));
        usuario.setEnderecos(List.of(createEndereco()));
        usuario.setContatos(List.of(createContato()));

        return usuario;
    }

    public static Cargo createCargoUsuario() {
        Cargo cargo = new Cargo();

        cargo.setIdCargo(1);
        cargo.setNome("USUARIO");

        return cargo;
    }

    public static UsuarioRequestAdminDTO createUsuarioRequestAdminDTO() {
        UsuarioRequestAdminDTO usuarioRequestAdminDTO = new UsuarioRequestAdminDTO();

        usuarioRequestAdminDTO.setNome("Usuário Padrão");
        usuarioRequestAdminDTO.setCpf("12345678900");
        usuarioRequestAdminDTO.setDataNascimento(LocalDate.parse("2000-01-01"));
        usuarioRequestAdminDTO.setEmail("usuario@usuario.com");
        usuarioRequestAdminDTO.setSenha("12345678");
        usuarioRequestAdminDTO.setEhPcd('S');
        usuarioRequestAdminDTO.setTipoDeficiencia("Física");
        usuarioRequestAdminDTO.setCertificadoDeficienciaGov("http://certificado.com");
        usuarioRequestAdminDTO.setImagemDocumento("http://imagem.com");
        usuarioRequestAdminDTO.setCargos(Set.of(CargoEnum.ROLE_USUARIO));

        return usuarioRequestAdminDTO;
    }

    public static UsuarioResponseCompletoDTO createUsuarioResponseCompletoDTO() {
        UsuarioResponseCompletoDTO usuarioResponseCompletoDTO = new UsuarioResponseCompletoDTO();

        usuarioResponseCompletoDTO.setId(1L);
        usuarioResponseCompletoDTO.setNome("Usuário Padrão");
        usuarioResponseCompletoDTO.setCpf("12345678900");
        usuarioResponseCompletoDTO.setDataNascimento(LocalDate.parse("2000-01-01"));
        usuarioResponseCompletoDTO.setEmail("usuario@usuario.com");
        usuarioResponseCompletoDTO.setSenha("12345678");
        usuarioResponseCompletoDTO.setEhPcd('S');
        usuarioResponseCompletoDTO.setTipoDeficiencia("Física");
        usuarioResponseCompletoDTO.setCertificadoDeficienciaGov("http://certificado.com");
        usuarioResponseCompletoDTO.setImagemDocumento("http://imagem.com");
        usuarioResponseCompletoDTO.setAtivo(true);
        usuarioResponseCompletoDTO.setCargos(Set.of(new CargoResponseDTO("USUARIO")));
        usuarioResponseCompletoDTO.setEnderecos(new ArrayList<>());
        usuarioResponseCompletoDTO.setContatos(new ArrayList<>());

        return usuarioResponseCompletoDTO;
    }

    public static UsuarioRequestDTO createUsuarioRequestDTO() {
        UsuarioRequestDTO usuarioRequestDTO = new UsuarioRequestDTO();

        usuarioRequestDTO.setNome("Usuário Padrão");
        usuarioRequestDTO.setCpf("12345678900");
        usuarioRequestDTO.setDataNascimento(LocalDate.parse("2000-01-01"));
        usuarioRequestDTO.setEmail("usuario@usuario.com");
        usuarioRequestDTO.setSenha("12345678");
        usuarioRequestDTO.setEhPcd('S');
        usuarioRequestDTO.setTipoDeficiencia("Física");
        usuarioRequestDTO.setCertificadoDeficienciaGov("http://certificado.com");
        usuarioRequestDTO.setImagemDocumento("http://imagem.com");

        return usuarioRequestDTO;
    }

    public static UsuarioResponseDTO createUsuarioResponseDTO() {
        UsuarioResponseDTO usuarioResponseDTO = new UsuarioResponseDTO();

        usuarioResponseDTO.setId(1L);
        usuarioResponseDTO.setNome("Usuário Padrão");
        usuarioResponseDTO.setCpf("12345678900");
        usuarioResponseDTO.setDataNascimento(LocalDate.parse("2000-01-01"));
        usuarioResponseDTO.setEmail("usuario@usuario.com");
        usuarioResponseDTO.setAtivo(true);
        usuarioResponseDTO.setEhPcd('S');
        usuarioResponseDTO.setTipoDeficiencia("Física");
        usuarioResponseDTO.setCertificadoDeficienciaGov("http://certificado.com");
        usuarioResponseDTO.setImagemDocumento("http://imagem.com");

        return usuarioResponseDTO;
    }

    public static Usuario createBaseUser() {
        Usuario usuario =  createUsuarioDefault();
        usuario.setCargos(new HashSet<>());

        return usuario;
    }

    public static Endereco createEndereco() {
        Endereco endereco = new Endereco();

        endereco.setId(1L);
        endereco.setLogradouro("Rua 1");
        endereco.setNumero("123");
        endereco.setComplemento("Casa");
        endereco.setCidade("Cidade");
        endereco.setCep("12345678");
        endereco.setEstado("Estado");
        endereco.setPais("País");
        endereco.setTipo(TipoEnum.COMERCIAL);
        endereco.setUsuarios(new ArrayList<>());

        return endereco;
    }

    public static Contato createContato() {
        Contato contato = new Contato();
        contato.setId(1L);
        contato.setTelefone("123456789");

        return contato;
    }
}
