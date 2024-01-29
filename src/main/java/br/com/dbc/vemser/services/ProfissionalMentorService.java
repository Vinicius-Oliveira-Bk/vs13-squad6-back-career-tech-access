package br.com.dbc.vemser.services;

import br.com.dbc.vemser.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.model.dtos.request.ProfissionalMentorRequestDTO;
import br.com.dbc.vemser.model.dtos.response.ProfissionalMentorResponseCompletoDTO;
import br.com.dbc.vemser.model.dtos.response.ProfissionalMentorResponseDTO;
import br.com.dbc.vemser.model.entities.ProfissionalMentor;
import br.com.dbc.vemser.model.entities.Usuario;
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
        Usuario usuario = usuarioService.getUsuario(idUsuario);
        ProfissionalMentor profissionalMentorEntity = objectMapper.convertValue(profissionalMentorRequestDTO, ProfissionalMentor.class);
        profissionalMentorEntity.setUsuario(usuario);
        profissionalMentorRepository.create(profissionalMentorEntity);
        ProfissionalMentorResponseDTO profissionalMentorResponseDTO = objectMapper.convertValue(profissionalMentorEntity, ProfissionalMentorResponseDTO.class);
        emailService.sendEmail(profissionalMentorResponseDTO.getUsuario(), profissionalMentorResponseDTO.getUsuario().getEmail(), EmailTemplate.CRIAR_USUARIO);
        return profissionalMentorResponseDTO;
    }

    public List<ProfissionalMentorResponseDTO> listAll() throws BancoDeDadosException {
        List<ProfissionalMentor> profissionalMentorEntity= profissionalMentorRepository.getAll();
        List<ProfissionalMentorResponseDTO> profissionalMentorResponseDTO = profissionalMentorEntity.stream()
                .map(profissionalMentor -> objectMapper.convertValue(profissionalMentor, ProfissionalMentorResponseDTO.class))
                .collect(Collectors.toList());
        return profissionalMentorResponseDTO;
    }

    public ProfissionalMentorResponseDTO update(Long idProfissionalMentor, ProfissionalMentorRequestDTO profissionalMentorRequestDTO) throws Exception {
        ProfissionalMentor prof = getProfissionalMentor(idProfissionalMentor);
        ProfissionalMentor profissionalMentorEntity = objectMapper.convertValue(profissionalMentorRequestDTO, ProfissionalMentor.class);
        profissionalMentorRepository.update(idProfissionalMentor, profissionalMentorEntity);
        profissionalMentorEntity.setIdProfissionalMentor(idProfissionalMentor);
        profissionalMentorEntity.setUsuario(prof.getUsuario());

        ProfissionalMentorResponseDTO profissionalMentorResponseDTO = objectMapper.convertValue(profissionalMentorEntity, ProfissionalMentorResponseDTO.class);
        return profissionalMentorResponseDTO;
    }

    public void delete(Long idProfissionalMentor) throws Exception {
        getProfissionalMentor(idProfissionalMentor);
        profissionalMentorRepository.delete(idProfissionalMentor);
    }

    public ProfissionalMentorResponseCompletoDTO getById(Long idProfissionalMentor) throws Exception {
        ProfissionalMentor profissionalMentorRecuperado = getProfissionalMentor(idProfissionalMentor);
        return objectMapper.convertValue(profissionalMentorRecuperado, ProfissionalMentorResponseCompletoDTO.class);
    }

    public ProfissionalMentor getProfissionalMentor(Long idProfissionalMentor) throws Exception {
        ProfissionalMentor profissionalMentorRecuperado = profissionalMentorRepository.getAll().stream()
                .filter(profissionalMentor -> profissionalMentor.getIdProfissionalMentor().equals(idProfissionalMentor))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("O Profissional Mentor de ID " + idProfissionalMentor + " não foi encontrado!"));
        return profissionalMentorRecuperado;
    }
}
