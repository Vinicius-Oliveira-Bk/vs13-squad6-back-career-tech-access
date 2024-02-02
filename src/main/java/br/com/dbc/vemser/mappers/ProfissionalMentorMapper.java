package br.com.dbc.vemser.mappers;

import br.com.dbc.vemser.model.dtos.request.ProfissionalMentorRequestDTO;
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

}
