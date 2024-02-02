package br.com.dbc.vemser.controllers.documentacao;

import br.com.dbc.vemser.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.model.dtos.request.EnderecoRequestDTO;
import br.com.dbc.vemser.model.dtos.response.EnderecoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface IEnderecoControllerDoc {

    @Operation(summary = "Criar endereço", description = "Cria um endereço para o usuário no banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Endereço criado com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Acesso negado"),
                    @ApiResponse(responseCode = "500", description = "Falha inesperada no servidor")
            }
    )
    @PostMapping("/{idUsuario}")
    ResponseEntity<EnderecoResponseDTO> create(@NotNull @PathVariable("idUsuario") Long idUsuario, @Valid @RequestBody EnderecoRequestDTO enderecoRequestDTO) throws Exception;

    @Operation(summary = "Listar endereços", description = "Lista todos os endereços do banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Lista de endereços retornados com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Erro de validação"),
                    @ApiResponse(responseCode = "403", description = "Acesso negado"),
                    @ApiResponse(responseCode = "500", description = "Falha inesperada no servidor")
            }
    )
    @GetMapping
    ResponseEntity<List<EnderecoResponseDTO>> listAll() throws BancoDeDadosException;

    @Operation(summary = "Listar um endereço", description = "Lista o endereço do banco pela sua id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Endereço retornado com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Acesso negado"),
                    @ApiResponse(responseCode = "500", description = "Falha inesperada no servidor")
            }
    )
    @GetMapping("/{idEndereco}")
    ResponseEntity<EnderecoResponseDTO> listById(@PathVariable Long idEndereco) throws Exception;

    @Operation(summary = "Atualizar endereço", description = "Atualiza um endereço no banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Erro de validação"),
                    @ApiResponse(responseCode = "403", description = "Acesso negado"),
                    @ApiResponse(responseCode = "500", description = "Falha inesperada no servidor")
            }
    )
    @PutMapping("/{idEndereco}")
    ResponseEntity<EnderecoResponseDTO> update(@PathVariable("idEndereco") Long id, @Valid @RequestBody EnderecoRequestDTO enderecoRequestDTO) throws Exception;

    @Operation(summary = "Remover endereço", description = "Remove um endereço do banco pelo id do endereço")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Endereço removido com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Acesso negado"),
                    @ApiResponse(responseCode = "500", description = "Falha inesperada no servidor")
            }
    )
    @DeleteMapping("/{idEndereco}")
    ResponseEntity<Void> delete(@PathVariable("idEndereco") Long id) throws Exception;



}
