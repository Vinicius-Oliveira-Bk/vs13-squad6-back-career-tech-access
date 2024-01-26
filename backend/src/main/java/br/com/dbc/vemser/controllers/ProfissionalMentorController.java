package br.com.dbc.vemser.controllers;

import br.com.dbc.vemser.model.dtos.request.ProfissionalMentorRequestDTO;
import br.com.dbc.vemser.model.dtos.response.ProfissionalMentorResponseDTO;
import br.com.dbc.vemser.services.ProfissionalMentorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/profissional-mentor")
@RequiredArgsConstructor
@Slf4j
@Validated
public class ProfissionalMentorController {

    private final ProfissionalMentorService profissionalMentorService;

    @PostMapping("/{idUsuario}")
    public ResponseEntity<ProfissionalMentorResponseDTO> create(@PathVariable Long idUsuario, @Valid @RequestBody ProfissionalMentorRequestDTO profissionalMentorRequestDTO) throws Exception {
        log.info("Criando Profissional Mentor...");
        ProfissionalMentorResponseDTO profissionalMentorCriado = profissionalMentorService.create(idUsuario, profissionalMentorRequestDTO);
        log.info(">>> Profissional Mentor criado com sucesso <<<");
        return new ResponseEntity<>(profissionalMentorCriado, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProfissionalMentorResponseDTO>> listAll() throws Exception {
        log.info("Buscando Profissional Mentor...");
        List<ProfissionalMentorResponseDTO> listaProfissionalMentor = profissionalMentorService.listAll();
        log.info(">>> Profissionais Mentores listados <<<");
        return ResponseEntity.ok().body(listaProfissionalMentor);
    }

    @GetMapping("/{idProfissionalMentor}")
    public ResponseEntity<ProfissionalMentorResponseDTO> getById(@PathVariable Long idProfissionalMentor) throws Exception {
        log.info("Buscando Profissional Mentor...");
        ProfissionalMentorResponseDTO profissionalMentor = profissionalMentorService.getById(idProfissionalMentor);
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
