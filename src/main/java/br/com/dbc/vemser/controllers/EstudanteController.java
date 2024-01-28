package br.com.dbc.vemser.controllers;

import br.com.dbc.vemser.controllers.documentacao.IEstudanteController;
import br.com.dbc.vemser.model.dtos.request.EstudanteRequestDTO;
import br.com.dbc.vemser.model.dtos.response.EstudanteResponseDTO;
import br.com.dbc.vemser.services.EstudanteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/estudante")
@RequiredArgsConstructor
@Slf4j
@Validated
public class EstudanteController implements IEstudanteController {

    private final EstudanteService estudanteService;

    @PostMapping("/{idCliente}")
    public ResponseEntity<EstudanteResponseDTO> create(@PathVariable Long idCliente, @Valid @RequestBody EstudanteRequestDTO estudanteRequestDTO) throws Exception {
        log.info("Criando estudante...");
        EstudanteResponseDTO estudanteCriado = estudanteService.create(estudanteRequestDTO, idCliente);
        log.info(">>> Estudante criado com sucesso <<<");
        return new ResponseEntity<>(estudanteCriado, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<EstudanteResponseDTO>> listAll() throws Exception {
        log.info("Buscando estudante...");
        List<EstudanteResponseDTO> listaEstudante = estudanteService.listAll();
        log.info(">>> Estudantes listados <<<");
        return ResponseEntity.ok().body(listaEstudante);
    }

    @GetMapping("/{idEstudante}")
    public ResponseEntity<EstudanteResponseDTO> listById(@PathVariable Long idEstudante) throws Exception {
        log.info("Buscando estudante...");
        EstudanteResponseDTO estudante = estudanteService.listById(idEstudante);
        log.info(">>> Estudante listado <<<");
        return ResponseEntity.ok().body(estudante);
    }

    @PutMapping("/{idEstudante}")
    public ResponseEntity<EstudanteResponseDTO> update(@PathVariable("idEstudante") Long idEstudante, @Valid @RequestBody EstudanteRequestDTO estudanteRequestDTO) throws Exception {
        log.info("Atualizando estudante...");
        EstudanteResponseDTO atualizaEstudante = estudanteService.update(idEstudante, estudanteRequestDTO);
        log.info(">>> Estudante atualizado com sucesso <<<");
        return ResponseEntity.ok().body(atualizaEstudante);
    }

    @DeleteMapping("/{idEstudante}")
    public ResponseEntity<Void> delete(@PathVariable("idEstudante") Long idEstudante) throws Exception {
        log.info("Deletando estudante...");
        estudanteService.delete(idEstudante);
        log.info(">>> Estudante deletado com sucesso <<<");
        return ResponseEntity.ok().build();
    }

}
