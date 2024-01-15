package com.dbc.view;

import com.dbc.model.entities.ProfissionalMentor;
import com.dbc.model.entities.Usuario;
import com.dbc.model.enums.NivelExperienciaEnum;
import com.dbc.model.enums.TipoUsuarioEnum;
import com.dbc.services.ProfissionalMentorServico;
import com.dbc.services.UsuarioServico;

import java.time.LocalDate;

import static com.dbc.model.enums.AreaAtuacaoEnum.TI;

/*
UsuarioServico usuarioServico = new UsuarioServico();
Usuario u = new Usuario("vini", LocalDate.of(2000,1,1), "32165498778", "teste@gmail.com", "1234", 'N', TipoUsuarioEnum.MENTOR, "teste", null, null, "aesaeEA");
   usuarioServico.cadastrar(u);
*/

public class Main {
    public static void main(String[] args) {
        // Aplicacao.iniciarAplicacao();
        ProfissionalMentorServico mentorServico = new ProfissionalMentorServico();
        ProfissionalMentor mentor = new ProfissionalMentor(TI, "12345678912345678123", NivelExperienciaEnum.JUNIOR);
        mentorServico.cadastrar(mentor);
    }
}