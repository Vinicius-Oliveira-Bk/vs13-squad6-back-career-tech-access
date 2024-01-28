package br.com.dbc.vemser.controllers.documentacao;

import br.com.dbc.vemser.model.dtos.request.EstudanteRequestDTO;
import br.com.dbc.vemser.model.dtos.response.EstudanteResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

public interface IEstudanteControllerDoc {

    @Operation(summary = "Cria um estudante", description = "Cria um estudante")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Estudante criado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Erro na requisição"),
                    @ApiResponse(responseCode = "403", description = "Acesso negado"),
                    @ApiResponse(responseCode = "500", description = "Falha inesperada no servidor")
            }
    )
    ResponseEntity<EstudanteResponseDTO> create(@PathVariable Long idCliente, @Valid @RequestBody EstudanteRequestDTO estudanteRequestDTO) throws Exception;

    @Operation(summary = "Lista um estudante", description = "Lista um estudante")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Estudante listado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Erro na requisição"),
                    @ApiResponse(responseCode = "403", description = "Acesso negado"),
                    @ApiResponse(responseCode = "500", description = "Falha inesperada no servidor")
            }
    )
    ResponseEntity<List<EstudanteResponseDTO>> listAll() throws Exception;

    @Operation(summary = "Lista todos estudantes", description = "Lista todos estudantes do servidor")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Estudante listado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Erro na requisição"),
                    @ApiResponse(responseCode = "403", description = "Acesso negado"),
                    @ApiResponse(responseCode = "500", description = "Falha inesperada no servidor")
            }
    )
    ResponseEntity<EstudanteResponseDTO> listById(@PathVariable Long idEstudante) throws Exception;

    @Operation(summary = "Atualiza um estudante", description = "Atualiza um estudante")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Estudante atualizado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Erro na requisição"),
                    @ApiResponse(responseCode = "403", description = "Acesso negado"),
                    @ApiResponse(responseCode = "500", description = "Falha inesperada no servidor")
            }
    )
    ResponseEntity<EstudanteResponseDTO> update(@PathVariable("idEstudante") Long idEstudante, @Valid @RequestBody EstudanteRequestDTO estudanteRequestDTO) throws Exception;

    @Operation(summary = "Deleta um estudante", description = "Deleta um estudante")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Estudante deletado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Erro na requisição"),
                    @ApiResponse(responseCode = "403", description = "Acesso negado"),
                    @ApiResponse(responseCode = "500", description = "Falha inesperada no servidor")
            }
    )
    ResponseEntity<Void> delete(@PathVariable("idEstudante") Long idEstudante) throws Exception;

}
