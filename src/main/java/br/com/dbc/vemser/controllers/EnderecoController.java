package br.com.dbc.vemser.controllers;

import br.com.dbc.vemser.controllers.documentacao.IEnderecoControllerDoc;
import br.com.dbc.vemser.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.model.dtos.request.EnderecoRequestDTO;
import br.com.dbc.vemser.model.dtos.response.EnderecoResponseDTO;
import br.com.dbc.vemser.services.EnderecoService;
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
@RequestMapping("/endereco")
@Tag(name = "Endereco")
@Validated
@Slf4j
public class EnderecoController implements IEnderecoControllerDoc {

    private final EnderecoService enderecoService;

    @PostMapping("/{idUsuario}")
    public ResponseEntity<EnderecoResponseDTO> create(@NotNull @PathVariable("idUsuario") Long idUsuario, @Valid @RequestBody EnderecoRequestDTO enderecoRequestDTO) throws Exception {
        log.info("Criando endereço...");
        return new ResponseEntity<>(enderecoService.create(idUsuario, enderecoRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<EnderecoResponseDTO>> listAll(@PageableDefault(page = 0, size = 10, sort = {"id"}) Pageable pageable) throws BancoDeDadosException {
        log.info("Buscando endereços...");
        return ResponseEntity.ok().body(enderecoService.listAll(pageable));
    }

    @GetMapping("/{idEndereco}")
    public ResponseEntity<EnderecoResponseDTO> listById(@PathVariable Long idEndereco) throws Exception {
        log.info("Buscando endereço...");
        return ResponseEntity.ok().body(enderecoService.listById(idEndereco));
    }

    @PutMapping("/{idEndereco}")
    public ResponseEntity<EnderecoResponseDTO> update(@PathVariable("idEndereco") Long id, @Valid @RequestBody EnderecoRequestDTO enderecoRequestDTO) throws Exception {
        log.info("Atualizando endereço...");
        return ResponseEntity.ok().body(enderecoService.update(id, enderecoRequestDTO));
    }

    @DeleteMapping("/{idEndereco}")
    public ResponseEntity<Void> delete(@PathVariable("idEndereco") Long id) throws Exception {
        log.info("Deletando endereço...");
        enderecoService.delete(id);
        return ResponseEntity.ok().build();
    }
}
