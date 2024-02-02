package br.com.dbc.vemser.controllers.documentacao;

import br.com.dbc.vemser.model.dtos.request.UsuarioRequestDTO;
import br.com.dbc.vemser.model.dtos.response.UsuarioResponseCompletoDTO;
import br.com.dbc.vemser.model.dtos.response.UsuarioResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;


public interface IUsuarioControllerDoc {

    @Operation(summary = "Criação do usuário", description = "Cria o usuário com os dados informados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cria o usuário com os dados informados"),
            @ApiResponse(responseCode = "400", description = "Erro de validação"),
            @ApiResponse(responseCode = "403", description = "Acesso negado"),
            @ApiResponse(responseCode = "500", description = "Falha inesperada no servidor")
    })
    ResponseEntity<UsuarioResponseDTO> create(@Valid @RequestBody UsuarioRequestDTO usuarioRequestDTO) throws Exception;

    @Operation(summary = "Listagem de todos os usuários", description = "Lista todos os usuários cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista todos os usuários cadastrados"),
            @ApiResponse(responseCode = "403", description = "Acesso negado"),
            @ApiResponse(responseCode = "500", description = "Falha inesperada no servidor")
    })
    ResponseEntity<Page<UsuarioResponseDTO>> listAll(@PageableDefault(page = 0, size = 10, sort = {"nome"}) Pageable pageable) throws Exception;

    @Operation(summary = "Lista o usuário pelo ID", description = "Lista o usuário cadastrado pelo ID informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista o usuário cadastrado pelo ID informado"),
            @ApiResponse(responseCode = "403", description = "Acesso negado"),
            @ApiResponse(responseCode = "500", description = "Falha inesperada no servidor")
    })
    ResponseEntity<UsuarioResponseCompletoDTO> listById(Long idUsuario) throws Exception;

    @Operation(summary = "Atualização do usuário", description = "Atualiza o usuário com os dados informados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atualiza o usuário com os dados informados"),
            @ApiResponse(responseCode = "400", description = "Erro de validação"),
            @ApiResponse(responseCode = "403", description = "Acesso negado"),
            @ApiResponse(responseCode = "500", description = "Falha inesperada no servidor")
    })
    ResponseEntity<UsuarioResponseDTO> update(@PathVariable("idUsuario") Long id, @Valid @RequestBody UsuarioRequestDTO usuarioRequestDTO) throws Exception;

    @Operation(summary = "Exclusão do usuário", description = "Exclui o usuário com o ID informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exclui o usuário com o ID informado"),
            @ApiResponse(responseCode = "403", description = "Acesso negado"),
            @ApiResponse(responseCode = "500", description = "Falha inesperada no servidor")
    })
    ResponseEntity<Void> delete(Long id) throws Exception;

}
