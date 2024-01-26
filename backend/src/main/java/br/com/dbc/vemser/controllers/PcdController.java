package br.com.dbc.vemser.controllers;

import br.com.dbc.vemser.controllers.documentacao.IPcdController;
import br.com.dbc.vemser.model.dtos.request.PcdRequestDTO;
import br.com.dbc.vemser.model.dtos.response.PcdResponseDTO;
import br.com.dbc.vemser.services.PcdService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pcd")
@RequiredArgsConstructor
@Slf4j
@Validated
public class PcdController implements IPcdController {

    private final PcdService pcdService;

    @PostMapping("/{idPcd}")
    public ResponseEntity<PcdResponseDTO> create(@PathVariable Long idPcd, @Valid @RequestBody PcdRequestDTO pcdRequestDTO) throws Exception {
        log.info("Criando PCD...");
        PcdResponseDTO pcdCriado = pcdService.create(pcdRequestDTO, idPcd);
        log.info(">>> PCD criado com sucesso <<<");
        return new ResponseEntity<>(pcdCriado, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PcdResponseDTO>> listAll() throws Exception {
        log.info("Buscando PCD´s...");
        List<PcdResponseDTO> listaPcd = pcdService.listAll();
        log.info(">>> PCD´s listados <<<");
        return ResponseEntity.ok().body(listaPcd);
    }

    @GetMapping("/{idPcd}")
    public ResponseEntity<PcdResponseDTO> listById(@PathVariable Long idPcd) throws Exception {
        log.info("Buscando PCD...");
        PcdResponseDTO pcd = pcdService.listById(idPcd);
        log.info(">>> PCD listado <<<");
        return ResponseEntity.ok().body(pcd);
    }

    @PutMapping("/{idPcd}")
    public ResponseEntity<PcdResponseDTO> update(@PathVariable("idPcd") Long idPcd, @Valid @RequestBody PcdRequestDTO pcdRequestDTO) throws Exception {
        log.info("Atualizando PCD...");
        PcdResponseDTO atualizaPcd = pcdService.update(idPcd, pcdRequestDTO);
        log.info(">>> PCD atualizado com sucesso <<<");
        return ResponseEntity.ok().body(atualizaPcd);
    }

    @DeleteMapping("/{idPcd}")
    public ResponseEntity<Void> delete(@PathVariable("idPcd") Long idPcd) throws Exception {
        log.info("Deletando PCD...");
        pcdService.delete(idPcd);
        log.info(">>> PCD deletado com sucesso <<<");
        return ResponseEntity.ok().build();
    }

}
