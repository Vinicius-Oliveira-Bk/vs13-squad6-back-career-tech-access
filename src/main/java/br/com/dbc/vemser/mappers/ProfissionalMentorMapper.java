package br.com.dbc.vemser.mappers;

import br.com.dbc.vemser.model.dtos.request.ProfissionalMentorRequestDTO;
import br.com.dbc.vemser.model.dtos.response.RelatorioAgendaMentorDTO;
import br.com.dbc.vemser.model.entities.AreaAtuacao;
import br.com.dbc.vemser.model.entities.AreaInteresse;
import br.com.dbc.vemser.model.entities.ProfissionalMentor;

import java.util.ArrayList;

public class ProfissionalMentorMapper {

    public static ProfissionalMentor profissionalMentorToEntity(ProfissionalMentorRequestDTO profissionalMentorRequestDTO) {
        ProfissionalMentor profissionalMentor = new ProfissionalMentor();

        profissionalMentor.setAgendas(new ArrayList<>());
        profissionalMentor.setNivelExperienciaEnum(profissionalMentorRequestDTO.getNivelExperienciaEnum());
        profissionalMentor.setCarteiraDeTrabalho(profissionalMentorRequestDTO.getCarteiraDeTrabalho());

        return profissionalMentor;
    }

    public static RelatorioAgendaMentorDTO profissionalMentorToRelatorioAgendaProfissionalMentorDTO(ProfissionalMentor profissionalMentor) {
        RelatorioAgendaMentorDTO relatorioAgendaMentorDTO = new RelatorioAgendaMentorDTO();

        relatorioAgendaMentorDTO.setEmail(profissionalMentor.getUsuario().getEmail());
        relatorioAgendaMentorDTO.setNome(profissionalMentor.getUsuario().getNome());
        relatorioAgendaMentorDTO.setNivelExperiencia(profissionalMentor.getNivelExperienciaEnum());
        relatorioAgendaMentorDTO.setCarteiraDeTrabalho(profissionalMentor.getCarteiraDeTrabalho());
        relatorioAgendaMentorDTO.setEhPCD(profissionalMentor.getUsuario().getEhPcd());
        relatorioAgendaMentorDTO.setTipoDeficiencia(profissionalMentor.getUsuario().getTipoDeficiencia());
        relatorioAgendaMentorDTO.setCertificadoDeficienciaGoverno(profissionalMentor.getUsuario().getCertificadoDeficienciaGov());
        relatorioAgendaMentorDTO.setImagem(profissionalMentor.getUsuario().getImagemDocumento());

        for (AreaAtuacao atuacao : profissionalMentor.getAtuacoes()) {
            relatorioAgendaMentorDTO.getAreaDeAtuacao().add(atuacao.getInteresse().name());
        }

        return relatorioAgendaMentorDTO;
    }
}
