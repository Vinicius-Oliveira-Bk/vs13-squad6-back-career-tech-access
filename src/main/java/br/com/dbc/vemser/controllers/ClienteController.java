package br.com.dbc.vemser.controllers;

import br.com.dbc.vemser.controllers.documentacao.IClienteControllerDoc;
import br.com.dbc.vemser.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.model.dtos.request.ClienteRequestDTO;
import br.com.dbc.vemser.model.dtos.response.ClienteResponseCompletoDTO;
import br.com.dbc.vemser.model.dtos.response.ClienteResponseDTO;
import br.com.dbc.vemser.services.ClienteService;
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
@RequestMapping("/cliente")
@Tag(name = "Cliente")
@Validated
@Slf4j
public class ClienteController implements IClienteControllerDoc {

    private final ClienteService clienteService;

    @PostMapping("/{idUsuario}")
    public ResponseEntity<ClienteResponseDTO> create(@Valid @RequestBody ClienteRequestDTO clienteRequestDTO, @PathVariable("idUsuario") @NotNull Long idUsuario) throws Exception {
        log.info("Criando cliente...");
        return new ResponseEntity<>(clienteService.create(clienteRequestDTO, idUsuario), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<ClienteResponseDTO>> listAll(@PageableDefault(page = 0, size = 10, sort = {"usuario_nome"}) Pageable pageable) throws BancoDeDadosException {
        log.info("Buscando clientes...");
        return ResponseEntity.ok().body(clienteService.listAll(pageable));
    }

    @GetMapping("/{idCliente}")
    public ResponseEntity<ClienteResponseCompletoDTO> listById(@PathVariable Long idCliente) throws Exception {
        log.info("Buscando cliente...");
        return ResponseEntity.ok().body(clienteService.listById(idCliente));
    }

    @PutMapping("/{idCliente}")
    public ResponseEntity<ClienteResponseDTO> update(@PathVariable("idCliente") Long id, @Valid @RequestBody ClienteRequestDTO clienteRequestDTO) throws Exception {
        log.info("Atualizando cliente...");
        return ResponseEntity.ok().body(clienteService.update(id, clienteRequestDTO));
    }

    @DeleteMapping("/{idCliente}")
    public ResponseEntity<Void> delete(@PathVariable("idCliente") Long id) throws Exception {
        log.info("Deletando cliente...");
        clienteService.delete(id);
        return ResponseEntity.ok().build();
    }

}

