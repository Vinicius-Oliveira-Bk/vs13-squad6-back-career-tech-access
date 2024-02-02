package br.com.dbc.vemser.controllers.documentacao;

import br.com.dbc.vemser.model.dtos.request.AgendaRequestDTO;
import br.com.dbc.vemser.model.dtos.response.AgendaResponseDTO;
import br.com.dbc.vemser.model.enums.StatusAgendaEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface IAgendaControllerDoc {

    @Operation(summary = "Cria um horário", description = "Cria um horário na agenda de um profissional")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Horário criado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Erro de validação"),
                    @ApiResponse(responseCode = "403", description = "Acesso negado"),
                    @ApiResponse(responseCode = "500", description = "Falha inesperada no servidor")
            }
    )
    ResponseEntity<AgendaResponseDTO> create(@NotNull @PathVariable("idProfissionalMentor") Long idProfissionalMentor, @Valid @RequestBody AgendaRequestDTO agendaRequestDTO) throws Exception;

    @Operation(summary = "Agenda um horário", description = "Agenda um cliente a um horário já disponível")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Agendamento realizado"),
                    @ApiResponse(responseCode = "403", description = "Acesso negado"),
                    @ApiResponse(responseCode = "500", description = "Falha inesperada no servidor")
            }
    )
    ResponseEntity<AgendaResponseDTO> agendarHorario(@PathVariable("idAgenda") @NotNull Long idAgenda, @PathVariable("idCliente") @NotNull Long idCliente) throws Exception;


    @Operation(summary = "Lista todos os horários", description = "Lista todos os horários")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Horários listados com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Acesso negado"),
                    @ApiResponse(responseCode = "500", description = "Falha inesperada no servidor")
            }
    )
    public ResponseEntity<Page<AgendaResponseDTO>> listAll(@PageableDefault(page = 0, size = 10, sort = {"dataHoraInicio"}) Pageable pageable) throws Exception;

    @Operation(summary = "Cancela um horário", description = "Cancela o agendamento e volta a situação do horário para disponível.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Cancelamento realizado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Erro de validação"),
                    @ApiResponse(responseCode = "403", description = "Acesso negado"),
                    @ApiResponse(responseCode = "500", description = "Falha inesperada no servidor")
            }
    )
    ResponseEntity<Void> cancelarHorario(@NotNull @PathVariable("idAgenda") Long id) throws Exception;

    @Operation(summary = "Reagenda um cliente", description = "Altera o agendamento do cliente para outra agenda disponível.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Reagendamento realizado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Erro de validação"),
                    @ApiResponse(responseCode = "403", description = "Acesso negado"),
                    @ApiResponse(responseCode = "500", description = "Falha inesperada no servidor")
            }
    )
    ResponseEntity<AgendaResponseDTO> reagendarHorario(@NotNull @PathVariable("idAgenda") Long id, @NotNull @PathVariable("idNovaAgenda") Long idNovaAgenda) throws Exception;

    @Operation(summary = "Busca agenda pelo id", description = "Busca agenda pelo id.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Agenda encontrada"),
                    @ApiResponse(responseCode = "400", description = "Erro de validação"),
                    @ApiResponse(responseCode = "403", description = "Acesso negado"),
                    @ApiResponse(responseCode = "500", description = "Falha inesperada no servidor")
            }
    )
    ResponseEntity<AgendaResponseDTO> getById(@PathVariable Long idAgenda) throws Exception;

    @Operation(summary = "Busca Agendas por Status", description = "Busca Agendas por Status")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Agendas listadas"),
                    @ApiResponse(responseCode = "400", description = "Erro de validação"),
                    @ApiResponse(responseCode = "403", description = "Acesso negado"),
                    @ApiResponse(responseCode = "500", description = "Falha inesperada no servidor")
            }
    )
    ResponseEntity<List<AgendaResponseDTO>> listAllByStatus(@PathVariable("statusAgendaEnum") @NotNull StatusAgendaEnum statusAgendaEnum) throws Exception;
    @Operation(summary = "Busca Agendas por profissional", description = "Busca Agendas por profissional")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Agendas listadas"),
                    @ApiResponse(responseCode = "400", description = "Erro de validação"),
                    @ApiResponse(responseCode = "403", description = "Acesso negado"),
                    @ApiResponse(responseCode = "500", description = "Falha inesperada no servidor")
            }
    )
    ResponseEntity<List<AgendaResponseDTO>> listAllByProfissional(@PathVariable("idProfissional") @NotNull Long idProfissional) throws Exception;


        @Operation(summary = "Busca Agendas por cliente", description = "Busca Agendas por cliente")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Agendas listadas"),
                    @ApiResponse(responseCode = "400", description = "Erro de validação"),
                    @ApiResponse(responseCode = "403", description = "Acesso negado"),
                    @ApiResponse(responseCode = "500", description = "Falha inesperada no servidor")
            }
    )
    ResponseEntity<List<AgendaResponseDTO>> listAllByCliente(@PathVariable("idCliente") @NotNull Long idCliente) throws Exception;



    @Operation(summary = "Exclui uma agenda", description = "Exclui uma agenda recebendo o id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Agenda excluída com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Erro de validação"),
                    @ApiResponse(responseCode = "403", description = "Acesso negado"),
                    @ApiResponse(responseCode = "500", description = "Falha inesperada no servidor")
            }
    )
    @DeleteMapping("/{idAgenda}")
    ResponseEntity<Void> delete(@PathVariable("idAgenda") Long id) throws Exception;








}
