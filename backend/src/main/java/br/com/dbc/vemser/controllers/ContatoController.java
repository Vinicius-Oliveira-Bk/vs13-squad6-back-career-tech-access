package br.com.dbc.vemser.controllers;

import br.com.dbc.vemser.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.model.dtos.request.ContatoRequestDTO;
import br.com.dbc.vemser.model.dtos.response.ContatoResponseDTO;
import br.com.dbc.vemser.services.ContatoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/contato")
@Validated
@Slf4j
public class ContatoController {

    private final ContatoService contatoService;

    @PostMapping
    public ResponseEntity<ContatoResponseDTO> create(@Valid @RequestBody ContatoRequestDTO contatoRequestDTO) throws Exception {
        log.info("Criando contato...");
        return new ResponseEntity<>(contatoService.create(contatoRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ContatoResponseDTO>> listAll() throws BancoDeDadosException {
        log.info("Buscando contatos...");
        return ResponseEntity.ok().body(contatoService.listAll());
    }

    @GetMapping("/{idContato}")
    public ResponseEntity<ContatoResponseDTO> listById(@PathVariable Long idContato) throws Exception {
        log.info("Buscando contato...");
        return ResponseEntity.ok().body(contatoService.listById(idContato));
    }

    @PutMapping("/{idContato}")
    public ResponseEntity<ContatoResponseDTO> update(@PathVariable("idContato") Long id, @Valid @RequestBody ContatoRequestDTO contatoRequestDTO) throws Exception {
        log.info("Atualizando contato...");
        return ResponseEntity.ok().body(contatoService.update(id, contatoRequestDTO));
    }

    @DeleteMapping("/{idContato}")
    public ResponseEntity<Void> delete(@PathVariable("idContato") Long id) throws Exception {
        log.info("Deletando contato...");
        contatoService.delete(id);
        return ResponseEntity.ok().build();
    }
}
