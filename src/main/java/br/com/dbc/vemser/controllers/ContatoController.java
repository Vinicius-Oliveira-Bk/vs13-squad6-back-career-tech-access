package br.com.dbc.vemser.controllers;

import br.com.dbc.vemser.controllers.documentacao.IContatoControllerDoc;
import br.com.dbc.vemser.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.model.dtos.request.ContatoRequestDTO;
import br.com.dbc.vemser.model.dtos.request.EnderecoRequestDTO;
import br.com.dbc.vemser.model.dtos.response.AgendaResponseDTO;
import br.com.dbc.vemser.model.dtos.response.ContatoResponseDTO;
import br.com.dbc.vemser.model.dtos.response.EnderecoResponseDTO;
import br.com.dbc.vemser.model.enums.StatusAgendaEnum;
import br.com.dbc.vemser.model.enums.TipoEnum;
import br.com.dbc.vemser.services.ContatoService;
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
import javax.validation.constraints.NotNull;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/contato")


@Tag(name = "Contato")
@Validated
@Slf4j
@Tag(name = "Contato")
public class ContatoController implements IContatoControllerDoc {

    private final ContatoService contatoService;

    @PostMapping("/criar-contato")
    public ResponseEntity<ContatoResponseDTO> create(@Valid @RequestBody ContatoRequestDTO contatoRequestDTO) throws Exception {
        log.info("Criando contato...");
        return new ResponseEntity<>(contatoService.create(null, contatoRequestDTO), HttpStatus.CREATED);
    }

    @PostMapping("/criar-contato-admin/{idUsuario}")
    public ResponseEntity<ContatoResponseDTO> create(@NotNull @PathVariable("idUsuario") Long idUsuario, @Valid @RequestBody ContatoRequestDTO contatoRequestDTO) throws Exception {
        log.info("Criando contato...");
        return new ResponseEntity<>(contatoService.create(idUsuario, contatoRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<ContatoResponseDTO>> listAll(@PageableDefault(page = 0, size = 10, sort = {"id"}) Pageable pageable,
                                                            @RequestParam(required = false) Long idUsuario,
                                                            @RequestParam(required = false) Long idContato,
                                                            @RequestParam(required = false) TipoEnum tipoEnum) throws BancoDeDadosException {
        log.info("Buscando contatos...");
        return ResponseEntity.ok().body(contatoService.listAll(pageable, idUsuario, idContato, tipoEnum));
    }

    @GetMapping("/usuario")
    public ResponseEntity<Page<ContatoResponseDTO>> listAllUsuario(@PageableDefault(page = 0, size = 10, sort = {"id"}) Pageable pageable,
                                                            @RequestParam(required = false) TipoEnum tipoEnum) throws BancoDeDadosException {
        log.info("Buscando contatos...");
        return ResponseEntity.ok().body(contatoService.listAllUsuario(pageable, tipoEnum));
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
