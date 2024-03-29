package br.com.dbc.vemser.controllers.documentacao;

import br.com.dbc.vemser.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.model.dtos.request.ClienteRequestDTO;
import br.com.dbc.vemser.model.dtos.response.ClienteResponseCompletoDTO;
import br.com.dbc.vemser.model.dtos.response.ClienteResponseDTO;
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
import javax.validation.constraints.NotNull;
import java.util.List;

public interface IClienteControllerDoc {

    @Operation(summary = "Cria um cliente", description = "Cria um cliente recebendo o id de um usuário")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Cliente criado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Erro de validação"),
                    @ApiResponse(responseCode = "403", description = "Acesso negado"),
                    @ApiResponse(responseCode = "500", description = "Falha inesperada no servidor")
            }
    )
    ResponseEntity<ClienteResponseDTO> createClienteAdmin(@Valid @RequestBody ClienteRequestDTO clienteRequestDTO, @NotNull @PathVariable("idUsuario") Long idUsuario) throws Exception;

    @Operation(summary = "Cria um cliente", description = "Cria um cliente recebendo o id de um usuário")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Cliente criado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Erro de validação"),
                    @ApiResponse(responseCode = "403", description = "Acesso negado"),
                    @ApiResponse(responseCode = "500", description = "Falha inesperada no servidor")
            }
    )
    ResponseEntity<ClienteResponseDTO> createCliente(@Valid @RequestBody ClienteRequestDTO clienteRequestDTO) throws Exception;

    @Operation(summary = "Lista todos os clientes", description = "Lista todos os clientes cadastrados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Clientes listados com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Acesso negado"),
                    @ApiResponse(responseCode = "500", description = "Falha inesperada no servidor")
            }
    )
    public ResponseEntity<Page<ClienteResponseDTO>> listAll(@PageableDefault(page = 0, size = 10, sort = {"usuario_nome"}) Pageable pageable) throws BancoDeDadosException;

    @Operation(summary = "Lista o cliente pelo ID", description = "Lista o cliente cadastrado pelo ID informado")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Cliente listado com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Acesso negado"),
                    @ApiResponse(responseCode = "500", description = "Falha inesperada no servidor")
            }
    )
    ResponseEntity<ClienteResponseCompletoDTO> listById(@PathVariable Long idCliente) throws Exception;

    @Operation(summary = "Atualiza um cliente", description = "Atualiza um cliente recebendo o id de um usuário")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Erro de validação"),
                    @ApiResponse(responseCode = "403", description = "Acesso negado"),
                    @ApiResponse(responseCode = "500", description = "Falha inesperada no servidor")
            }
    )
    ResponseEntity<ClienteResponseDTO> update(@PathVariable("idCliente") Long id, @Valid @RequestBody ClienteRequestDTO clienteRequestDTO) throws Exception;

    @Operation(summary = "Exclui um cliente", description = "Exclui um cliente recebendo o id de um usuário")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Cliente excluído com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Erro de validação"),
                    @ApiResponse(responseCode = "403", description = "Acesso negado"),
                    @ApiResponse(responseCode = "500", description = "Falha inesperada no servidor")
            }
    )
    ResponseEntity<Void> delete(Long idCliente) throws Exception;
}
