package br.com.dbc.vemser.controllers.documentacao;

import br.com.dbc.vemser.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.model.dtos.request.LoginRequestDTO;
import br.com.dbc.vemser.model.dtos.request.UsuarioRequestDTO;
import br.com.dbc.vemser.model.dtos.response.UsuarioResponseDTO;
import br.com.dbc.vemser.model.entities.Usuario;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Optional;

public interface IAuthControllerDoc {

    @Operation(summary = "Autenticação do usuário", description = "Verifica se o usuário está autenticado no sistema e retorna o token se estiver")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Requisição bem-sucedida"),
                    @ApiResponse(responseCode = "403", description = "Acesso negado"),
                    @ApiResponse(responseCode = "500", description = "Falha inesperada no servidor")
            }
    )
    String auth(@RequestBody @Valid LoginRequestDTO loginRequestDTO);

    @Operation(summary = "Exibir usuário logado no sistema", description = "Exibe o usuário que está logado no sistema")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Requisição bem-sucedida"),
                    @ApiResponse(responseCode = "403", description = "Acesso negado"),
                    @ApiResponse(responseCode = "500", description = "Falha inesperada no servidor")
            }
    )
    ResponseEntity<UsuarioResponseDTO> usuarioLogado() throws RegraDeNegocioException;

}
