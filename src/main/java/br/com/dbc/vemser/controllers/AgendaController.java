package br.com.dbc.vemser.controllers;

import br.com.dbc.vemser.controllers.documentacao.IAgendaControllerDoc;
import br.com.dbc.vemser.model.dtos.request.AgendaRequestDTO;
import br.com.dbc.vemser.model.dtos.response.AgendaResponseDTO;
import br.com.dbc.vemser.model.enums.StatusAgendaEnum;
import br.com.dbc.vemser.services.AgendaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/agenda")
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

    @GetMapping
    public ResponseEntity<List<AgendaResponseDTO>> listAll() throws Exception {
        log.info("Buscando horários...");
        return ResponseEntity.ok().body(agendaService.listAll());
    }

    @GetMapping("/{idCliente}/cliente")
    public ResponseEntity<List<AgendaResponseDTO>> listAllByCliente(@PathVariable("idCliente") @NotNull Long idCliente) throws Exception {
        log.info("Buscando horários...");
        return ResponseEntity.ok().body(agendaService.listAllByCliente(idCliente));
    }

    @GetMapping("/{idProfissional}/profissional")
    public ResponseEntity<List<AgendaResponseDTO>> listAllByProfissional(@PathVariable("idProfissional") @NotNull Long idProfissional) throws Exception {
        log.info("Buscando horários...");
        return ResponseEntity.ok().body(agendaService.listAllByProfissional(idProfissional));
    }

    @GetMapping("/{statusAgendaEnum}/status")
    public ResponseEntity<List<AgendaResponseDTO>> listAllByStatus(@PathVariable("statusAgendaEnum") @NotNull StatusAgendaEnum statusAgendaEnum) throws Exception {
        log.info("Buscando horários...");
        return ResponseEntity.ok().body(agendaService.listAllByStatus(statusAgendaEnum));
    }

    @GetMapping("/{idAgenda}")
    public ResponseEntity<AgendaResponseDTO> getById(@PathVariable Long idAgenda) throws Exception {
        log.info("Buscando horário...");
        return ResponseEntity.ok().body(agendaService.getById(idAgenda));
    }

    @PutMapping("reagendar/{idAgenda}/{idNovaAgenda}")
    public ResponseEntity<AgendaResponseDTO> reagendarHorario(@NotNull @PathVariable("idAgenda") Long id, @NotNull @PathVariable("idNovaAgenda") Long idNovaAgenda) throws Exception {
        log.info("Atualizando horário...");
        return ResponseEntity.ok().body(agendaService.reagendarHorario(id, idNovaAgenda));
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
}

