package br.com.dbc.vemser.services;

import br.com.dbc.vemser.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.mappers.ProfissionalMentorMapper;
import br.com.dbc.vemser.model.dtos.request.ProfissionalMentorRequestDTO;
import br.com.dbc.vemser.model.dtos.response.ClienteResponseDTO;
import br.com.dbc.vemser.model.dtos.response.ProfissionalMentorResponseCompletoDTO;
import br.com.dbc.vemser.model.dtos.response.ProfissionalMentorResponseDTO;
import br.com.dbc.vemser.model.entities.*;
import br.com.dbc.vemser.model.enums.AreasDeInteresse;
import br.com.dbc.vemser.model.enums.EmailTemplate;
import br.com.dbc.vemser.repository.ProfissionalMentorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProfissionalMentorService {

    private final ProfissionalMentorRepository profissionalMentorRepository;
    private final ObjectMapper objectMapper;
    private final UsuarioService usuarioService;
    private final EmailService emailService;

    public ProfissionalMentorResponseDTO create(Long idUsuario, ProfissionalMentorRequestDTO profissionalMentorRequestDTO) throws Exception {
        List<ProfissionalMentor> mentores = profissionalMentorRepository.findAll();
        Usuario usuario = usuarioService.getUsuario(idUsuario);
        boolean mentorExiste = mentores.stream()
                .anyMatch(mentor -> mentor.getUsuario().getId().equals(idUsuario));

        if (mentorExiste) {
            throw new RegraDeNegocioException("Já existe um mentor com o mesmo id_usuario.");
        }
        ProfissionalMentor profissionalMentor = ProfissionalMentorMapper.profissionalMentorToEntity(profissionalMentorRequestDTO);

        profissionalMentor.setUsuario(usuario);
        ProfissionalMentor criado = profissionalMentorRepository.save(profissionalMentor);

        criado.setAtuacoes(listAreaInteresseToAreaAtuacao(profissionalMentorRequestDTO.getAtuacoes(), criado));

        profissionalMentorRepository.save(criado);

        ProfissionalMentorResponseDTO profissionalMentorResponseDTO = objectMapper.convertValue(profissionalMentor, ProfissionalMentorResponseDTO.class);
        emailService.sendEmail(profissionalMentorResponseDTO.getUsuario(), profissionalMentorResponseDTO.getUsuario().getEmail(), EmailTemplate.CRIAR_USUARIO);

        return profissionalMentorResponseDTO;
    }

    public List<ProfissionalMentorResponseDTO> listAll() throws BancoDeDadosException {
        List<ProfissionalMentor> profissionalMentorEntity= profissionalMentorRepository.findAll();

        return profissionalMentorEntity.stream()
                .map(profissionalMentor -> objectMapper.convertValue(profissionalMentor, ProfissionalMentorResponseDTO.class))
                .toList();
    }

    public ProfissionalMentorResponseDTO update(Long idProfissionalMentor, ProfissionalMentorRequestDTO profissionalMentorRequestDTO) throws Exception {
        ProfissionalMentor prof = getProfissionalMentor(idProfissionalMentor);

        prof.setAtuacoes(listAreaInteresseToAreaAtuacao(profissionalMentorRequestDTO.getAtuacoes(), prof));
        prof.setCarteiraDeTrabalho(profissionalMentorRequestDTO.getCarteiraDeTrabalho());
        prof.setNivelExperienciaEnum(profissionalMentorRequestDTO.getNivelExperienciaEnum());
        profissionalMentorRepository.save(prof);

        return objectMapper.convertValue(prof, ProfissionalMentorResponseDTO.class);
    }

    public void delete(Long idProfissionalMentor) throws Exception {
        ProfissionalMentor prof = getProfissionalMentor(idProfissionalMentor);
        profissionalMentorRepository.delete(prof);
    }

    public ProfissionalMentorResponseCompletoDTO getById(Long idProfissionalMentor) throws Exception {
        return objectMapper.convertValue(getProfissionalMentor(idProfissionalMentor), ProfissionalMentorResponseCompletoDTO.class);
    }

    public ProfissionalMentor getProfissionalMentor(Long idProfissionalMentor) throws Exception {
        return profissionalMentorRepository.findById(idProfissionalMentor)
                .orElseThrow(() -> new RegraDeNegocioException("O Profissional Mentor de ID " + idProfissionalMentor + " não foi encontrado!"));
    }

    private List<AreaAtuacao> listAreaInteresseToAreaAtuacao(List<AreasDeInteresse> areaInteresses, ProfissionalMentor profissionalMentor) {
        return areaInteresses.stream()
                .map(areaInteresse -> {
                    AreaAtuacao areaAtuacao = new AreaAtuacao();

                    areaAtuacao.setProfissionalMentor(profissionalMentor);
                    areaAtuacao.setInteresse(areaInteresse);

                    return areaAtuacao;
                })
                .collect(Collectors.toList());
    }
}
