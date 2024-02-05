package br.com.dbc.vemser.mappers;

import br.com.dbc.vemser.model.dtos.response.RelatorioAgendaDTO;
import br.com.dbc.vemser.model.entities.Agenda;

import java.util.Objects;

public class AgendaMapper {

    public static RelatorioAgendaDTO agendaToRelatorioAgendaDTO(Agenda agenda) {
        RelatorioAgendaDTO relatorioAgendaDTO = new RelatorioAgendaDTO();

        relatorioAgendaDTO.setCodigoAgenda(agenda.getId());
        relatorioAgendaDTO.setStatusAgenda(agenda.getStatusAgendaEnum());
        relatorioAgendaDTO.setDataInicioAgendamento(agenda.getDataHoraInicio());
        relatorioAgendaDTO.setDataFimAgendamento(agenda.getDataHoraFim());

        if (Objects.nonNull(agenda.getCliente())) {
            relatorioAgendaDTO.setCliente(ClienteMapper.clienteToRelatorioAgendaClienteDTO(agenda.getCliente()));
        }

        if (Objects.nonNull(agenda.getProfissionalMentor())) {
            relatorioAgendaDTO.setMentor(ProfissionalMentorMapper.profissionalMentorToRelatorioAgendaProfissionalMentorDTO(agenda.getProfissionalMentor()));
        }

        return relatorioAgendaDTO;
    }

}
