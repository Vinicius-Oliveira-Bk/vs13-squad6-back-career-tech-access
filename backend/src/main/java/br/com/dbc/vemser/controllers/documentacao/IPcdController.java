package br.com.dbc.vemser.controllers.documentacao;

import br.com.dbc.vemser.model.dtos.request.PcdRequestDTO;
import br.com.dbc.vemser.model.dtos.response.PcdResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface IPcdController {

    @Operation(summary = "Lista todos os usuários PCD", description = "Lista todos os usuários PCD")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Usuários PCD listados com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Acesso negado"),
                    @ApiResponse(responseCode = "500", description = "Falha inesperada no servidor")
            }
    )
    ResponseEntity<Iterable<PcdResponseDTO>> listAll() throws Exception;

    @Operation(summary = "Cria um usuário PCD", description = "Cria um usuário PCD")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Usuário PCD criado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Erro na requisição"),
                    @ApiResponse(responseCode = "403", description = "Acesso negado"),
                    @ApiResponse(responseCode = "500", description = "Falha inesperada no servidor")
            }
    )
    ResponseEntity<PcdResponseDTO> create(@PathVariable Long idPcs, @Valid @RequestBody PcdRequestDTO pcdRequestDTO) throws Exception;

    @Operation(summary = "Lista um usuário PCD", description = "Lista um usuário PCD")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Usuário PCD listado com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Acesso negado"),
                    @ApiResponse(responseCode = "500", description = "Falha inesperada no servidor")
            }
    )
    ResponseEntity<PcdResponseDTO> listById(@PathVariable Long idPcd) throws Exception;

    @Operation(summary = "Atualiza um usuário PCD", description = "Atualiza um usuário PCD")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Usuário PCD atualizado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Erro na requisição"),
                    @ApiResponse(responseCode = "403", description = "Acesso negado"),
                    @ApiResponse(responseCode = "500", description = "Falha inesperada no servidor")
            }
    )
    ResponseEntity<PcdResponseDTO> update(@PathVariable("idPcd") Long idPcd, @Valid @RequestBody PcdRequestDTO pcdRequestDTO) throws Exception;

    @Operation(summary = "Deleta um usuário PCD", description = "Deleta um usuário PCD")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Usuário PCD deletado com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Acesso negado"),
                    @ApiResponse(responseCode = "500", description = "Falha inesperada no servidor")
            }
    )
    ResponseEntity<Void> delete(@PathVariable("idPcd") Long idPcd) throws Exception;

}
