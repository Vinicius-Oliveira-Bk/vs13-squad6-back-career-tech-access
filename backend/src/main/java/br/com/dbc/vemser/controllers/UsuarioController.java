package br.com.dbc.vemser.controllers;

import br.com.dbc.vemser.model.dtos.request.UsuarioRequestDTO;
import br.com.dbc.vemser.model.dtos.response.UsuarioResponseDTO;
import br.com.dbc.vemser.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Slf4j
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> create(@Valid @RequestBody UsuarioRequestDTO usuarioRequestDTO) throws Exception {
        log.info("Criando usuario...");
        return new ResponseEntity<>(usuarioService.create(usuarioRequestDTO), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listAll() throws Exception {
        log.info("Buscando usuarios...");
        return ResponseEntity.ok().body(usuarioService.listAll());
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<UsuarioResponseDTO> listById(@PathVariable Long idUsuario) throws Exception {
        log.info("Buscando usuário...");
        return ResponseEntity.ok().body(usuarioService.listById(idUsuario));
    }

    @PutMapping("/{idUsuario}")
    public ResponseEntity<UsuarioResponseDTO> update(@PathVariable("idUsuario") Long id, @Valid @RequestBody UsuarioRequestDTO contatoRequestDTO) throws Exception {
        log.info("Atualizando usuário...");
        return ResponseEntity.ok().body(usuarioService.update(id, contatoRequestDTO));
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Void> delete(@PathVariable("idUsuario") Long id) throws Exception {
        log.info("Deletando usuário...");
        usuarioService.delete(id);
        return ResponseEntity.ok().build();
    }


}
