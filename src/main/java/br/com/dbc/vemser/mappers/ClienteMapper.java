package br.com.dbc.vemser.mappers;

import br.com.dbc.vemser.model.dtos.request.ClienteRequestDTO;
import br.com.dbc.vemser.model.dtos.response.RelatorioAgendaClienteDTO;
import br.com.dbc.vemser.model.entities.*;

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

    public static RelatorioAgendaClienteDTO clienteToRelatorioAgendaClienteDTO(Cliente cliente) {
        RelatorioAgendaClienteDTO relatorioAgendaClienteDTO = new RelatorioAgendaClienteDTO();

        relatorioAgendaClienteDTO.setNome(cliente.getUsuario().getNome());
        relatorioAgendaClienteDTO.setEmail(cliente.getUsuario().getEmail());
        relatorioAgendaClienteDTO.setEhPCD(cliente.getUsuario().getEhPcd());
        relatorioAgendaClienteDTO.setEhEstudante(cliente.getEhEstudante());
        relatorioAgendaClienteDTO.setEhProfissionalRealocacao(cliente.getEhProfissionalRealocacao());
        relatorioAgendaClienteDTO.setTipoDeficiencia(cliente.getUsuario().getTipoDeficiencia());
        relatorioAgendaClienteDTO.setCertificadoDeficienciaGoverno(cliente.getUsuario().getCertificadoDeficienciaGov());

        for (Contato contato : cliente.getUsuario().getContatos()) {
            relatorioAgendaClienteDTO.getContatos().add(ContatoMapper.contatoToRelatorioContatoDTO(contato));
        }

        for (Endereco endereco : cliente.getUsuario().getEnderecos()) {
            relatorioAgendaClienteDTO.getEnderecos().add(EnderecoMapper.enderecoToRelatorioEnderecoDTO(endereco));
        }

        for (AreaInteresse interesse : cliente.getInteresses()) {
            relatorioAgendaClienteDTO.getInteresses().add(interesse.getInteresse().name());
        }

        return relatorioAgendaClienteDTO;
    }
}
