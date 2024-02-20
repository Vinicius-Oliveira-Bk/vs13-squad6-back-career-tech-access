package br.com.dbc.vemser.utils;

import br.com.dbc.vemser.model.dtos.request.EnderecoRequestDTO;
import br.com.dbc.vemser.model.dtos.response.EnderecoResponseDTO;
import br.com.dbc.vemser.model.entities.Endereco;
import br.com.dbc.vemser.model.enums.TipoEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public class EnderecoServiceTestUtils {

    public static Endereco createEnderecoDefault() {
        Endereco endereco = new Endereco();

        endereco.setId(1L);
        endereco.setCep("89000000");
        endereco.setLogradouro("Rua das Flores");
        endereco.setCidade("Blumenau");
        endereco.setEstado("SC");
        endereco.setTipo(TipoEnum.COMERCIAL);
        endereco.setNumero("123");
        endereco.setPais("Brasil");
        endereco.setUsuarios(new ArrayList<>());
        endereco.setComplemento("Complemento");

        return endereco;
    }

    public static Endereco createEnderecoComPessoa() {
        Endereco endereco = new Endereco();

        endereco.setId(1L);
        endereco.setCep("89000000");
        endereco.setLogradouro("Rua das Flores");
        endereco.setCidade("Blumenau");
        endereco.setEstado("SC");
        endereco.setTipo(TipoEnum.COMERCIAL);
        endereco.setNumero("123");
        endereco.setPais("Brasil");
        endereco.setUsuarios(List.of(UsuarioServiceTestUtils.createUsuarioDefault()));
        endereco.setComplemento("Complemento");

        return endereco;
    }

    public static EnderecoRequestDTO createEnderecoRequestDTO() {
        EnderecoRequestDTO enderecoRequestDTO = new EnderecoRequestDTO();

        enderecoRequestDTO.setCep("89000000");
        enderecoRequestDTO.setLogradouro("Rua das Flores");
        enderecoRequestDTO.setCidade("Blumenau");
        enderecoRequestDTO.setEstado("SC");
        enderecoRequestDTO.setTipo(TipoEnum.COMERCIAL);
        enderecoRequestDTO.setNumero("123");
        enderecoRequestDTO.setPais("Brasil");
        enderecoRequestDTO.setComplemento("Complemento");

        return enderecoRequestDTO;
    }

    public static EnderecoResponseDTO createEnderecoResponseDTO() {
        EnderecoResponseDTO endereco = new EnderecoResponseDTO();

        endereco.setId(1L);
        endereco.setCep("89000000");
        endereco.setLogradouro("Rua das Flores");
        endereco.setCidade("Blumenau");
        endereco.setEstado("SC");
        endereco.setTipo(TipoEnum.COMERCIAL);
        endereco.setNumero("123");
        endereco.setPais("Brasil");
        endereco.setComplemento("Complemento");

        return endereco;
    }

    public static Pageable createPageable() {
        return PageRequest.of(0, 10);
    }

    public static Page<Endereco> createPageEndereco() {
        List<Endereco> enderecos = new ArrayList<>();

        Endereco endereco2 = createEnderecoDefault();
        Endereco endereco3 = createEnderecoDefault();

        endereco2.setId(2L);
        endereco2.setNumero("456");
        endereco3.setId(3L);
        endereco3.setNumero("789");

        enderecos.add(createEnderecoDefault());
        enderecos.add(endereco2);
        enderecos.add(endereco3);

        return new PageImpl<>(enderecos, createPageable(), 0);
    }
}
