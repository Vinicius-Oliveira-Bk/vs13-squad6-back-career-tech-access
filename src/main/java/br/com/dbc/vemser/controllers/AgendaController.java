package br.com.dbc.vemser.controllers;

import br.com.dbc.vemser.controllers.documentacao.IAgendaControllerDoc;
import br.com.dbc.vemser.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.model.dtos.request.AgendaRequestDTO;
import br.com.dbc.vemser.model.dtos.response.AgendaResponseDTO;
import br.com.dbc.vemser.model.dtos.response.RelatorioAgendaDTO;
import br.com.dbc.vemser.model.entities.Agenda;
import br.com.dbc.vemser.model.enums.StatusAgendaEnum;
import br.com.dbc.vemser.services.AgendaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/agenda")
@Tag(name = "Agenda")
@Validated
@Slf4j
public class AgendaController implements IAgendaControllerDoc {

    private final AgendaService agendaService;

    @PostMapping("/{idProfissionalMentor}")
    public ResponseEntity<AgendaResponseDTO> create(@NotNull @PathVariable("idProfissionalMentor") Long idProfissionalMentor, @Valid @RequestBody AgendaRequestDTO agendaRequestDTO) throws Exception {
        log.info("Disponibilizando Horário...");
        return new ResponseEntity<>(agendaService.cadastrarHorario(idProfissionalMentor, agendaRequestDTO), HttpStatus.CREATED);
    }

    @PutMapping("agendar/{idAgenda}/{idCliente}")
    public ResponseEntity<AgendaResponseDTO> agendarHorario(@PathVariable("idAgenda") @NotNull Long idAgenda, @PathVariable("idCliente") @NotNull Long idCliente) throws Exception {
        log.info("Agendando Cliente...");
        return new ResponseEntity<>(agendaService.agendarHorario(idAgenda, idCliente), HttpStatus.OK);
    }

    @PutMapping("marcar-presente/{idAgenda}")
    public ResponseEntity<String> marcarPresente(@PathVariable("idAgenda") @NotNull Long idAgenda) throws Exception {
        log.info("Agendando Cliente...");
        return new ResponseEntity<>(agendaService.marcarPresente(idAgenda), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<AgendaResponseDTO>> listAll(@PageableDefault(page = 0, size = 10, sort = {"data_inicio"}) Pageable pageable,
                                                           @RequestParam(required = false) Long idAgenda,
                                                           @RequestParam(required = false) StatusAgendaEnum statusAgendaEnum,
                                                           @RequestParam(required = false) Long idProfissionalMentor,
                                                           @RequestParam(required = false) Long idCliente) throws Exception {
        log.info("Buscando horários...");
        return ResponseEntity.ok().body(agendaService.listAll(pageable, idAgenda, statusAgendaEnum, idProfissionalMentor, idCliente));
    }

    @GetMapping("/cliente")
    public ResponseEntity<Page<AgendaResponseDTO>> listAllAgendasCliente(@PageableDefault(page = 0, size = 10, sort = {"data_inicio"}) Pageable pageable,
                                                           @RequestParam(required = false) StatusAgendaEnum statusAgendaEnum,
                                                           @RequestParam(required = false) Long idProfissionalMentor) throws RegraDeNegocioException {
        log.info("Buscando horários...");
        return ResponseEntity.ok().body(agendaService.listAllAgendasClienteLogado(pageable, statusAgendaEnum, idProfissionalMentor));
    }

    @GetMapping("/profissional")
    public ResponseEntity<Page<AgendaResponseDTO>> listAllAgendasProfissional(@PageableDefault(page = 0, size = 10, sort = {"data_inicio"}) Pageable pageable,
                                                           @RequestParam(required = false) StatusAgendaEnum statusAgendaEnum) throws RegraDeNegocioException {
        log.info("Buscando horários...");
        return ResponseEntity.ok().body(agendaService.listAllAgendasProfissionalLogado(pageable, statusAgendaEnum));
    }

    @PutMapping("alterar-horario/{idAgenda}")
    public ResponseEntity<AgendaResponseDTO> alterarHorario(@PathVariable("idAgenda") @NotNull Long idAgenda, @RequestBody @Valid AgendaRequestDTO agendaRequestDTO) throws Exception {
        log.info("Agendando Cliente...");
        return new ResponseEntity<>(agendaService.alterarHorario(idAgenda, agendaRequestDTO), HttpStatus.OK);
    }

    @PutMapping("cancelar/{idAgenda}")
    public ResponseEntity<Void> cancelarHorario(@NotNull @PathVariable("idAgenda") Long id) throws Exception {
        log.info("Cancelando horário...");
        agendaService.cancelarHorario(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{idAgenda}")
    public ResponseEntity<Void> delete(@PathVariable("idAgenda") Long id) throws Exception {
        log.info("Deletando horário...");
        agendaService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/relatorio")
    public ResponseEntity<Set<RelatorioAgendaDTO>> relatorioAgenda(@RequestParam(required = false) Long idAgenda) {
        log.info("Gerando relatório...");
        return ResponseEntity.ok().body(agendaService.relatorioAgenda(idAgenda));
    }
}

