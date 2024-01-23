package br.com.dbc.vemser.controllers;

import br.com.dbc.vemser.model.dtos.request.ProfissionalRealocacaoRequestDTO;
import br.com.dbc.vemser.model.dtos.response.ProfissionalRealocacaoResponseDTO;
import br.com.dbc.vemser.services.ProfissionalRealocacaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/profissionalrealocacao")
@RequiredArgsConstructor
@Slf4j
@Validated
public class ProfissinalRealocacaoController {

    private final ProfissionalRealocacaoService profissionalRealocacaoService;

    public ResponseEntity<ProfissionalRealocacaoResponseDTO> create(@Valid @RequestBody ProfissionalRealocacaoRequestDTO profissionalRealocacaoRequestDTO) throws Exception {
        log.info("Criando profissional realocação...");
        ProfissionalRealocacaoResponseDTO profissionalRealocacaoCriado = profissionalRealocacaoService.create(profissionalRealocacaoRequestDTO);
        log.info(">>> Profissional realocação criado com sucesso <<<");
        return new ResponseEntity<>(profissionalRealocacaoCriado, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProfissionalRealocacaoResponseDTO>> listAll() throws Exception {
        log.info("Buscando profissionais em realocação...");
        List<ProfissionalRealocacaoResponseDTO> listaProfissionalRealocacao = profissionalRealocacaoService.listAll();
        log.info(">>> Profissionais em realocação listados <<<");
        return ResponseEntity.ok().body(listaProfissionalRealocacao);
    }

    @GetMapping("/{idProfissionalRealocacao}")
    public ResponseEntity<ProfissionalRealocacaoResponseDTO> listById(@PathVariable Long idProfissionalRealocacao) throws Exception {
        log.info("Buscando profissional realocação...");
        ProfissionalRealocacaoResponseDTO profissionalRealocacao = profissionalRealocacaoService.listById(idProfissionalRealocacao);
        log.info(">>> Profissional realocação listado <<<");
        return ResponseEntity.ok().body(profissionalRealocacao);
    }

    @PutMapping("/{idProfissionalRealocacao}")
    public ResponseEntity<ProfissionalRealocacaoResponseDTO> update(@PathVariable("idProfissionalRealocacao") Long idProfissionalRealocacao, @Valid @RequestBody ProfissionalRealocacaoRequestDTO profissionalRealocacaoRequestDTO) throws Exception {
        log.info("Atualizando profissional realocação...");
        ProfissionalRealocacaoResponseDTO atualizaProfissionalRealocacao = profissionalRealocacaoService.update(idProfissionalRealocacao, profissionalRealocacaoRequestDTO);
        log.info(">>> Profissional realocação atualizado com sucesso <<<");
        return ResponseEntity.ok().body(atualizaProfissionalRealocacao);
    }

    @DeleteMapping("/{idProfissionalRealocacao}")
    public ResponseEntity<Void> delete(@PathVariable("idProfissionalRealocacao") Long idProfissionalRealocacao) throws Exception {
        log.info("Deletando profissional realocação...");
        profissionalRealocacaoService.delete(idProfissionalRealocacao);
        log.info(">>> Profissional realocação deletado com sucesso <<<");
        return ResponseEntity.ok().build();
    }

}
