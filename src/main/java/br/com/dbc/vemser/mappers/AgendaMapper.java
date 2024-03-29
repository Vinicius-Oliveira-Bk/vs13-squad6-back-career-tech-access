package br.com.dbc.vemser.mappers;

import br.com.dbc.vemser.model.dtos.response.RelatorioAgendaDTO;
import br.com.dbc.vemser.model.entities.Agenda;

import java.util.Objects;

public class AgendaMapper {

    public static RelatorioAgendaDTO agendaToRelatorioAgendaDTO(Agenda agenda) {
        RelatorioAgendaDTO relatorioAgendaDTO = new RelatorioAgendaDTO();

        relatorioAgendaDTO.setId(agenda.getId());
        relatorioAgendaDTO.setStatusAgendaEnum(agenda.getStatusAgendaEnum());
        relatorioAgendaDTO.setDataHoraInicio(agenda.getDataHoraInicio());
        relatorioAgendaDTO.setDataHoraFim(agenda.getDataHoraFim());

        return relatorioAgendaDTO;
    }

}
