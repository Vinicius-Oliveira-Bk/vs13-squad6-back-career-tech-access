package br.com.dbc.vemser.controllers.documentacao;

import br.com.dbc.vemser.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.model.dtos.request.ContatoRequestDTO;
import br.com.dbc.vemser.model.dtos.response.ContatoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface IContatoControllerDoc {

    @Operation(summary = "Criar contato", description = "Cria um contato no banco para o usuário informado")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Contato criado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Erro de validação"),
                    @ApiResponse(responseCode = "403", description = "Acesso negado"),
                    @ApiResponse(responseCode = "500", description = "Falha inesperada no servidor")
            }
    )
    ResponseEntity<ContatoResponseDTO> create(@NotNull @PathVariable("idUsuario") Long idUsuario, @Valid @RequestBody ContatoRequestDTO contatoRequestDTO) throws Exception;

    @Operation(summary = "Listar contatos", description = "Lista todos os contatos do banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Contatos retornados com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Acesso negado"),
                    @ApiResponse(responseCode = "500", description = "Falha inesperada no servidor")
            }
    )
    public ResponseEntity<Page<ContatoResponseDTO>> listAll(@PageableDefault(page = 0, size = 10, sort = {"id"}) Pageable pageable) throws BancoDeDadosException;

    @Operation(summary = "Atualizar contato", description = "Atualiza um contato no banco através do id de contato")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Contato atualizado com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Acesso negado"),
                    @ApiResponse(responseCode = "500", description = "Falha inesperada no servidor")
            }
    )
    ResponseEntity<ContatoResponseDTO> update(@PathVariable("idContato") Long id, @Valid @RequestBody ContatoRequestDTO contatoRequestDTO) throws Exception;

    @Operation(summary = "Listar os contatos de um usuário", description = "Lista o(s) contato(s) de um usuário específico do banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Contato listado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Erro de validação"),
                    @ApiResponse(responseCode = "403", description = "Acesso negado"),
                    @ApiResponse(responseCode = "500", description = "Falha inesperada no servidor")
            }
    )
    ResponseEntity<ContatoResponseDTO> listById(@PathVariable Long idContato) throws Exception;

    @Operation(summary = "Remover contato", description = "Remove um contato do banco pelo id do contato")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Contato removido com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Acesso negado"),
                    @ApiResponse(responseCode = "500", description = "Falha inesperada no servidor")
            }
    )
    ResponseEntity<Void> delete(@PathVariable("idContato") Long id) throws Exception;

}
