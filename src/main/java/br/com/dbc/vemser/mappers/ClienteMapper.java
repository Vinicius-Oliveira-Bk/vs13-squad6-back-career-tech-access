package br.com.dbc.vemser.mappers;

import br.com.dbc.vemser.model.dtos.request.ClienteRequestDTO;
import br.com.dbc.vemser.model.entities.Cliente;
import br.com.dbc.vemser.model.entities.Usuario;

public class ClienteMapper {

    public static Cliente clienteRequestDTOtoEntity(ClienteRequestDTO clienteRequestDTO) {
        Cliente cliente = new Cliente();

        cliente.setTipoPlano(clienteRequestDTO.getTipoPlano());
        cliente.setControleParental(clienteRequestDTO.getControleParental());
        cliente.setEhEstudante(clienteRequestDTO.getEhEstudante());
        cliente.setEhProfissionalRealocacao(clienteRequestDTO.getEhProfissionalRealocacao());
        cliente.setProfissao(clienteRequestDTO.getProfissao());
        cliente.setObjetivoProfissional(clienteRequestDTO.getObjetivoProfissional());
        cliente.setMatricula(clienteRequestDTO.getMatricula());
        cliente.setComprovanteMatricula(clienteRequestDTO.getComprovanteMatricula());
        cliente.setInstituicao(clienteRequestDTO.getInstituicao());
        cliente.setCurso(clienteRequestDTO.getCurso());
        cliente.setDataInicio(clienteRequestDTO.getDataInicio());
        cliente.setDataTermino(clienteRequestDTO.getDataTermino());

        return cliente;
    }

}
