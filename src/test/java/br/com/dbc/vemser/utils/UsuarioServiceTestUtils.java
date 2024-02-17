package br.com.dbc.vemser.utils;

import br.com.dbc.vemser.model.dtos.request.UsuarioRequestAdminDTO;
import br.com.dbc.vemser.model.dtos.request.UsuarioRequestDTO;
import br.com.dbc.vemser.model.dtos.response.CargoResponseDTO;
import br.com.dbc.vemser.model.dtos.response.UsuarioResponseCompletoDTO;
import br.com.dbc.vemser.model.dtos.response.UsuarioResponseDTO;
import br.com.dbc.vemser.model.entities.Cargo;
import br.com.dbc.vemser.model.entities.Usuario;
import br.com.dbc.vemser.model.enums.CargoEnum;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class UsuarioServiceTestUtils {

    public static Usuario createUsuarioDefault() {
        return Usuario.builder()
                .id(1L)
                .nome("Usuário Padrão")
                .cpf("12345678900")
                .dataNascimento(LocalDate.parse("2000-01-01"))
                .email("usuario@usuario.com")
                .senha("12345678")
                .ehPcd('S')
                .tipoDeficiencia("Física")
                .certificadoDeficienciaGov("http://certificado.com")
                .imagemDocumento("http://imagem.com")
                .isAtivo(true)
                .cargos(Set.of(createCargoUsuario()))
                .enderecos(new ArrayList<>())
                .contatos(new ArrayList<>())
                .build();
    }

    public static Cargo createCargoUsuario() {
        return Cargo.builder()
                .idCargo(1)
                .nome("USUARIO")
                .build();
    }

    public static UsuarioRequestAdminDTO createUsuarioRequestAdminDTO() {
        return UsuarioRequestAdminDTO.builder()
                .nome("Usuário Padrão")
                .cpf("12345678900")
                .dataNascimento(LocalDate.parse("2000-01-01"))
                .email("usuario@usuario.com")
                .senha("12345678")
                .ehPcd('S')
                .tipoDeficiencia("Física")
                .certificadoDeficienciaGov("http://certificado.com")
                .imagemDocumento("http://imagem.com")
                .cargos(Set.of(CargoEnum.ROLE_USUARIO))
                .build();
    }

    public static UsuarioResponseCompletoDTO createUsuarioResponseCompletoDTO() {
        return UsuarioResponseCompletoDTO.builder()
                .id(1L)
                .nome("Usuário Padrão")
                .cpf("12345678900")
                .dataNascimento(LocalDate.parse("2000-01-01"))
                .email("usuario@usuario.com")
                .senha("12345678")
                .ehPcd('S')
                .tipoDeficiencia("Física")
                .certificadoDeficienciaGov("http://certificado.com")
                .imagemDocumento("http://imagem.com")
                .ativo(true)
                .cargos(Set.of(new CargoResponseDTO("USUARIO")))
                .enderecos(new ArrayList<>())
                .contatos(new ArrayList<>())
                .build();
    }

    public static UsuarioRequestDTO createUsuarioRequestDTO() {
        return UsuarioRequestDTO.builder()
                .nome("Usuário Padrão")
                .cpf("12345678900")
                .dataNascimento(LocalDate.parse("2000-01-01"))
                .email("usuario@usuario.com")
                .senha("12345678")
                .ehPcd('S')
                .tipoDeficiencia("Física")
                .certificadoDeficienciaGov("http://certificado.com")
                .imagemDocumento("http://imagem.com")
                .build();
    }

    public static UsuarioResponseDTO createUsuarioResponseDTO() {
        return UsuarioResponseDTO.builder()
                .id(1L)
                .nome("Usuário Padrão")
                .cpf("12345678900")
                .dataNascimento(LocalDate.parse("2000-01-01"))
                .email("usuario@usuario.com")
                .isAtivo(true)
                .ehPcd('S')
                .tipoDeficiencia("Física")
                .certificadoDeficienciaGov("http://certificado.com")
                .imagemDocumento("http://imagem.com")
                .build();
    }

    public static Usuario createBaseUser() {
        Usuario usuario = createUsuarioDefault();
        usuario.setCargos(new HashSet<>());

        return usuario;
    }
}
