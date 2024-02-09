package br.com.dbc.vemser.controllers;

import br.com.dbc.vemser.controllers.documentacao.IProfissionalMentorControllerDoc;
import br.com.dbc.vemser.model.dtos.request.ProfissionalMentorRequestDTO;
import br.com.dbc.vemser.model.dtos.response.ProfissionalMentorResponseCompletoDTO;
import br.com.dbc.vemser.model.dtos.response.ProfissionalMentorResponseDTO;
import br.com.dbc.vemser.services.ProfissionalMentorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/profissional-mentor")
@Tag(name = "Mentor")
@RequiredArgsConstructor
@Slf4j
@Validated
public class ProfissionalMentorController implements IProfissionalMentorControllerDoc {

    private final ProfissionalMentorService profissionalMentorService;

    @PostMapping("/criar-profissional-admin/{idUsuario}")
    public ResponseEntity<ProfissionalMentorResponseDTO> create(@PathVariable Long idUsuario, @Valid @RequestBody ProfissionalMentorRequestDTO profissionalMentorRequestDTO) throws Exception {
        log.info("Criando Profissional Mentor...");
        ProfissionalMentorResponseDTO profissionalMentorCriado = profissionalMentorService.create(idUsuario, profissionalMentorRequestDTO);
        log.info(">>> Profissional Mentor criado com sucesso <<<");
        return new ResponseEntity<>(profissionalMentorCriado, HttpStatus.OK);
    }

    @PostMapping("/criar-profissional")
    public ResponseEntity<ProfissionalMentorResponseDTO> create(@Valid @RequestBody ProfissionalMentorRequestDTO profissionalMentorRequestDTO) throws Exception {
        log.info("Criando Profissional Mentor...");
        ProfissionalMentorResponseDTO profissionalMentorCriado = profissionalMentorService.create(null, profissionalMentorRequestDTO);
        log.info(">>> Profissional Mentor criado com sucesso <<<");
        return new ResponseEntity<>(profissionalMentorCriado, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<ProfissionalMentorResponseDTO>> listAll(@PageableDefault(page = 0, size = 10, sort = {"id"}) Pageable pageable) throws Exception {
        log.info("Buscando Profissional Mentor...");
        Page<ProfissionalMentorResponseDTO> paginaProfissionalMentor = profissionalMentorService.listAll(pageable);
        log.info(">>> Profissionais Mentores listados <<<");
        return ResponseEntity.ok().body(paginaProfissionalMentor);
    }

    @GetMapping("/{idProfissionalMentor}")
    public ResponseEntity<ProfissionalMentorResponseCompletoDTO> getById(@PathVariable Long idProfissionalMentor) throws Exception {
        log.info("Buscando Profissional Mentor...");
        ProfissionalMentorResponseCompletoDTO profissionalMentor = profissionalMentorService.getById(idProfissionalMentor);
        log.info(">>> Profissional Mentor listado <<<");
        return ResponseEntity.ok().body(profissionalMentor);
    }

    @PutMapping("/{idProfissionalMentor}")
    public ResponseEntity<ProfissionalMentorResponseDTO> update(@PathVariable("idProfissionalMentor") Long idProfissionalMentor, @Valid @RequestBody ProfissionalMentorRequestDTO profissionalMentorRequestDTO) throws Exception {
        log.info("Atualizando Profissional Mentor...");
        ProfissionalMentorResponseDTO atualizaProfissionalMentor = profissionalMentorService.update(idProfissionalMentor, profissionalMentorRequestDTO);
        log.info(">>> Profissional Mentor atualizado com sucesso <<<");
        return ResponseEntity.ok().body(atualizaProfissionalMentor);
    }

    @DeleteMapping("/{idProfissionalMentor}")
    public ResponseEntity<Void> delete(@PathVariable("idProfissionalMentor") Long idProfissionalMentor) throws Exception {
        log.info("Deletando Profissional Mentor...");
        profissionalMentorService.delete(idProfissionalMentor);
        log.info(">>> Profissional Mentor deletado com sucesso <<<");
        return ResponseEntity.ok().build();
    }
}
