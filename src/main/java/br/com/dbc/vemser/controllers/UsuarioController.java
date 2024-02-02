package br.com.dbc.vemser.controllers;

import br.com.dbc.vemser.controllers.documentacao.IUsuarioControllerDoc;
import br.com.dbc.vemser.model.dtos.request.UsuarioRequestDTO;
import br.com.dbc.vemser.model.dtos.response.UsuarioResponseCompletoDTO;
import br.com.dbc.vemser.model.dtos.response.UsuarioResponseDTO;
import br.com.dbc.vemser.services.UsuarioService;
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
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Slf4j
@Validated
@Tag(name = "Usuário")
public class UsuarioController implements IUsuarioControllerDoc {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> create(@Valid @RequestBody UsuarioRequestDTO usuarioRequestDTO) throws Exception {
        log.info("Criando usuario...");
        UsuarioResponseDTO usuarioCriado = usuarioService.create(usuarioRequestDTO);
        log.info(">>> Usuário criado com sucesso <<<");
        return new ResponseEntity<>(usuarioCriado, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<UsuarioResponseDTO>> listAll(@PageableDefault(page = 0, size = 10, sort = {"nome"}) Pageable pageable) throws Exception {
        log.info("Buscando usuarios...");
        Page<UsuarioResponseDTO> paginaUsuarios = usuarioService.listAll(pageable);
        log.info(">>> Usuários listados <<<");
        return ResponseEntity.ok().body(paginaUsuarios);
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<UsuarioResponseCompletoDTO> listById(@PathVariable Long idUsuario) throws Exception {
        log.info("Buscando usuário...");
        UsuarioResponseCompletoDTO usuario = usuarioService.listById(idUsuario);
        log.info(">>> Usuário listado <<<");
        return ResponseEntity.ok().body(usuario);
    }

    @PutMapping("/{idUsuario}")
    public ResponseEntity<UsuarioResponseDTO> update(@PathVariable("idUsuario") Long id, @Valid @RequestBody UsuarioRequestDTO usuarioRequestDTO) throws Exception {
        log.info("Atualizando usuário...");
        UsuarioResponseDTO atualizaUsuario = usuarioService.update(id, usuarioRequestDTO);
        log.info(">>> Usuário atualizado com sucesso <<<");
        return ResponseEntity.ok().body(atualizaUsuario);
    }

    @DeleteMapping("{idUsuario}")
    public ResponseEntity<Void> delete(@PathVariable("idUsuario") Long id) throws Exception {
        log.info("Deletando usuário...");
        usuarioService.delete(id);
        log.info(">>> Usuário deletado com sucesso <<<");
        return ResponseEntity.ok().build();
    }
}
