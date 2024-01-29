package br.com.dbc.vemser.controllers.documentacao;

import br.com.dbc.vemser.model.dtos.request.ProfissionalRealocacaoRequestDTO;
import br.com.dbc.vemser.model.dtos.response.ProfissionalRealocacaoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

public interface IProfissionalRealocacaoControllerDoc {

    @Operation(summary = "Lista todos profissionais realocação", description = "Lista todos profissionais realocação do sistema")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Profissionais realocação listados com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Acesso negado"),
                    @ApiResponse(responseCode = "500", description = "Falha inesperada no servidor")
            }
    )
    ResponseEntity<List<ProfissionalRealocacaoResponseDTO>> listAll() throws Exception;

    @Operation(summary = "Criar um profissional realocação", description = "Criar um profissional realocação no sistema")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Profissional realocação criado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Erro na requisição"),
                    @ApiResponse(responseCode = "403", description = "Acesso negado"),
                    @ApiResponse(responseCode = "500", description = "Falha inesperada no servidor")
            }
    )
    ResponseEntity<ProfissionalRealocacaoResponseDTO> create(@PathVariable Long idProfissionalRealocacao, @Valid @RequestBody ProfissionalRealocacaoRequestDTO profissionalRealocacaoRequestDTO) throws Exception;

    @Operation(summary = "Lista um profissional realocação", description = "Lista um profissional realocação do sistema")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Profissional realocação listado com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Acesso negado"),
                    @ApiResponse(responseCode = "500", description = "Falha inesperada no servidor")
            }
    )
    ResponseEntity<ProfissionalRealocacaoResponseDTO> listById(@PathVariable Long idProfissionalRealocacao) throws Exception;

    @Operation(summary = "Atualiza um profissional realocação", description = "Atualiza um profissional realocação do sistema")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Profissional realocação atualizado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Erro na requisição"),
                    @ApiResponse(responseCode = "403", description = "Acesso negado"),
                    @ApiResponse(responseCode = "500", description = "Falha inesperada no servidor")
            }
    )
    ResponseEntity<ProfissionalRealocacaoResponseDTO> update(@PathVariable("idProfissionalRealocacao") Long idProfissionalRealocacao, @Valid @RequestBody ProfissionalRealocacaoRequestDTO profissionalRealocacaoRequestDTO) throws Exception;

    @Operation(summary = "Deleta um profissional realocação", description = "Deleta um profissional realocação do sistema")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Profissional realocação deletado com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Acesso negado"),
                    @ApiResponse(responseCode = "500", description = "Falha inesperada no servidor")
            }
    )
    ResponseEntity<Void> delete(@PathVariable("idProfissionalRealocacao") Long idProfissionalRealocacao) throws Exception;

}
