package br.com.dbc.vemser.controllers.documentacao;

import br.com.dbc.vemser.model.dtos.request.ProfissionalMentorRequestDTO;
import br.com.dbc.vemser.model.dtos.response.ProfissionalMentorResponseCompletoDTO;
import br.com.dbc.vemser.model.dtos.response.ProfissionalMentorResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

public interface IProfissionalMentorController {

    @Operation(summary = "Cria um profissional mentor", description = "Cria um profissional mentor")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Profissional mentor criado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Erro na requisição"),
                    @ApiResponse(responseCode = "403", description = "Acesso negado"),
                    @ApiResponse(responseCode = "500", description = "Falha inesperada no servidor")
            }
    )
    ResponseEntity<ProfissionalMentorResponseDTO> create(@PathVariable Long idUsuario, @Valid @RequestBody ProfissionalMentorRequestDTO profissionalMentorRequestDTO) throws Exception;


    @Operation(summary = "Lista todos profissionais mentores", description = "Lista todos profissionais mentorres do sistema")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Profissionais mentores listados com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Acesso negado"),
                    @ApiResponse(responseCode = "500", description = "Falha inesperada no servidor")
            }
    )
    ResponseEntity<List<ProfissionalMentorResponseDTO>> listAll() throws Exception;

    @Operation(summary = "Lista um profissional mentor", description = "Lista um profissional mentor do sistema")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Profissional mentor listado com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Acesso negado"),
                    @ApiResponse(responseCode = "500", description = "Falha inesperada no servidor")
            }
    )
    ResponseEntity<ProfissionalMentorResponseCompletoDTO> getById(@PathVariable Long idProfissionalMentor) throws Exception;

    @Operation(summary = "Atualiza um profissional mentor", description = "Atualiza um profissional mentor do sistema")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Profissional mentor atualizado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Erro na requisição"),
                    @ApiResponse(responseCode = "403", description = "Acesso negado"),
                    @ApiResponse(responseCode = "500", description = "Falha inesperada no servidor")
            }
    )
    ResponseEntity<ProfissionalMentorResponseDTO> update(@PathVariable Long idProfissionalMentor, @Valid @RequestBody ProfissionalMentorRequestDTO profissionalMentorRequestDTO) throws Exception;

    @Operation(summary = "Deleta um profissional mentor", description = "Deleta um profissional mentor do sistema")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Profissional mentor deletado com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Acesso negado"),
                    @ApiResponse(responseCode = "500", description = "Falha inesperada no servidor")
            }
    )
    ResponseEntity<Void> delete(@PathVariable Long idProfissionalMentor) throws Exception;


}
