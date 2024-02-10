package br.com.dbc.vemser.controllers.documentacao;

import br.com.dbc.vemser.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.model.dtos.request.AgendaRequestDTO;
import br.com.dbc.vemser.model.dtos.response.AgendaResponseDTO;
import br.com.dbc.vemser.model.dtos.response.RelatorioAgendaDTO;
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
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

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
    ResponseEntity<Page<AgendaResponseDTO>> listAll(@PageableDefault(page = 0, size = 10, sort = {"data_inicio"}) Pageable pageable,
                                                    @RequestParam(required = false) Long idAgenda,
                                                    @RequestParam(required = false) StatusAgendaEnum statusAgendaEnum,
                                                    @RequestParam(required = false) Long idProfissionalMentor,
                                                    @RequestParam(required = false) Long idCliente) throws Exception;

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


    @Operation(summary = "Relatório de agenda", description = "Relatório de agenda")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Relatório gerado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Erro de validação"),
                    @ApiResponse(responseCode = "403", description = "Acesso negado"),
                    @ApiResponse(responseCode = "500", description = "Falha inesperada no servidor")
            }
    )
    ResponseEntity<Set<RelatorioAgendaDTO>> relatorioAgenda(@RequestParam(required = false) Long idAgenda);

    @Operation(summary = "Alterar horário da agenda pelo seu id", description = "Altera o horário da agenda pelo idAgenda informado")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Relatório gerado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Erro de validação"),
                    @ApiResponse(responseCode = "403", description = "Acesso negado"),
                    @ApiResponse(responseCode = "500", description = "Falha inesperada no servidor")
            }
    )
    ResponseEntity<AgendaResponseDTO> alterarHorario(@PathVariable("idAgenda") @NotNull Long idAgenda, @RequestBody @Valid AgendaRequestDTO agendaRequestDTO) throws Exception;

    @Operation(summary = "Marcar o status da agenda para presente", description = "Marca o status da agenda para presente")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Relatório gerado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Erro de validação"),
                    @ApiResponse(responseCode = "403", description = "Acesso negado"),
                    @ApiResponse(responseCode = "500", description = "Falha inesperada no servidor")
            }
    )
    ResponseEntity<String> marcarPresente(@PathVariable("idAgenda") @NotNull Long idAgenda) throws Exception;

    @Operation(summary = "Listar a agenda dos profissionais mentores", description = "Lista a agenda dos profissionais mentores por status (DISPONIVEL/AGENDADO/PRESENTE)")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Relatório gerado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Erro de validação"),
                    @ApiResponse(responseCode = "403", description = "Acesso negado"),
                    @ApiResponse(responseCode = "500", description = "Falha inesperada no servidor")
            }
    )
    ResponseEntity<Page<AgendaResponseDTO>> listAllAgendasProfissional(@PageableDefault(page = 0, size = 10, sort = {"data_inicio"}) Pageable pageable,
                                                                       @RequestParam(required = false) StatusAgendaEnum statusAgendaEnum) throws RegraDeNegocioException;

    @Operation(summary = "Listar a agenda do cliente por status", description = "Lista a agenda do cliente por status (DISPONIVEL/AGENDADO/PRESENTE)")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Relatório gerado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Erro de validação"),
                    @ApiResponse(responseCode = "403", description = "Acesso negado"),
                    @ApiResponse(responseCode = "500", description = "Falha inesperada no servidor")
            }
    )
    ResponseEntity<Page<AgendaResponseDTO>> listAllAgendasCliente(@PageableDefault(page = 0, size = 10, sort = {"data_inicio"}) Pageable pageable,
                                                                  @RequestParam(required = false) StatusAgendaEnum statusAgendaEnum,
                                                                  @RequestParam(required = false) Long idProfissionalMentor) throws RegraDeNegocioException;
}
