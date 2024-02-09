package br.com.dbc.vemser.controllers;

import br.com.dbc.vemser.controllers.documentacao.IAuthControllerDoc;
import br.com.dbc.vemser.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.model.dtos.request.LoginRequestDTO;
import br.com.dbc.vemser.model.dtos.request.UsuarioRequestDTO;
import br.com.dbc.vemser.model.dtos.response.UsuarioResponseCompletoDTO;
import br.com.dbc.vemser.model.dtos.response.UsuarioResponseDTO;
import br.com.dbc.vemser.model.entities.Usuario;
import br.com.dbc.vemser.security.TokenService;
import br.com.dbc.vemser.services.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@Validated
@RequiredArgsConstructor
@Tag(name = "Authorize")
public class AuthController implements IAuthControllerDoc {
    private final TokenService tokenService;
    public final AuthenticationManager authenticationManager;
    public final PasswordEncoder passwordEncoder;
    public final UsuarioService usuarioService;

    @PostMapping
    public String auth(@RequestBody @Valid LoginRequestDTO loginRequestDTO) throws RegraDeNegocioException {
        usuarioService.userIsAtivo(loginRequestDTO);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(
                        loginRequestDTO.getEmail(),
                        loginRequestDTO.getSenha()
                );

        Authentication authentication =
                authenticationManager.authenticate(
                        usernamePasswordAuthenticationToken);

        Usuario usuarioValidado = (Usuario) authentication.getPrincipal();

        return tokenService.generateToken(usuarioValidado);
    }

    @GetMapping("/usuario-logado")
    public ResponseEntity<UsuarioResponseCompletoDTO> usuarioLogado() {
        return new ResponseEntity<>(usuarioService.getLoggedUser(), HttpStatus.OK);
    }

}
