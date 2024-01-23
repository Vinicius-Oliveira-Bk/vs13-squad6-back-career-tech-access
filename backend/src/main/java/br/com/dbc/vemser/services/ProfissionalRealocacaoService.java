package br.com.dbc.vemser.services;

import br.com.dbc.vemser.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.model.dtos.request.ProfissionalRealocacaoRequestDTO;
import br.com.dbc.vemser.model.dtos.response.ProfissionalRealocacaoResponseDTO;
import br.com.dbc.vemser.model.entities.ProfissionalRealocacao;
import br.com.dbc.vemser.repository.ProfissionalRealocacaoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfissionalRealocacaoService {

    private final ProfissionalRealocacaoRepository profissionalRealocacaoRepository;
    private final ObjectMapper objectMapper;

    public ProfissionalRealocacaoResponseDTO create(ProfissionalRealocacaoRequestDTO profissionalRealocacaoRequestDTO) throws Exception {
        ProfissionalRealocacao profissionalRealocacaoEntity = objectMapper.convertValue(profissionalRealocacaoRequestDTO, ProfissionalRealocacao.class);
        profissionalRealocacaoRepository.create(profissionalRealocacaoEntity);
        ProfissionalRealocacaoResponseDTO profissionalRealocacaoResponseDTO = objectMapper.convertValue(profissionalRealocacaoEntity, ProfissionalRealocacaoResponseDTO.class);
        return profissionalRealocacaoResponseDTO;
    }

    public List<ProfissionalRealocacaoResponseDTO> listAll() throws BancoDeDadosException {
        List<ProfissionalRealocacao> profissionalRealocacaoEntity= profissionalRealocacaoRepository.getAll();
        List<ProfissionalRealocacaoResponseDTO> profissionalRealocacaoResponseDTO = profissionalRealocacaoEntity.stream()
                .map(profissionalRealocacao -> objectMapper.convertValue(profissionalRealocacao, ProfissionalRealocacaoResponseDTO.class))
                .collect(Collectors.toList());
        return profissionalRealocacaoResponseDTO;
    }

    public ProfissionalRealocacaoResponseDTO update(Long idProfissionalRealocacao, ProfissionalRealocacaoRequestDTO profissionalRealocacaoRequestDTO) throws Exception {
        ProfissionalRealocacao buscaProfissionalRealocacao = getProfissionalRealocacao(idProfissionalRealocacao);

        ProfissionalRealocacao profissionalRealocacaoEntity = objectMapper.convertValue(profissionalRealocacaoRequestDTO, ProfissionalRealocacao.class);
        profissionalRealocacaoRepository.update(idProfissionalRealocacao, profissionalRealocacaoEntity);
        profissionalRealocacaoEntity.setId(idProfissionalRealocacao);

        ProfissionalRealocacaoResponseDTO profissionalRealocacaoResponseDTO = objectMapper.convertValue(profissionalRealocacaoEntity, ProfissionalRealocacaoResponseDTO.class);
        return profissionalRealocacaoResponseDTO;
    }

    public void delete(Long idProfissionalRealocacao) throws Exception {
        ProfissionalRealocacao profissionalRealocacao = getProfissionalRealocacao(idProfissionalRealocacao);
        profissionalRealocacaoRepository.delete(idProfissionalRealocacao);
    }

    public ProfissionalRealocacaoResponseDTO listById(Long idProfissionalRealocacao) throws Exception {
        ProfissionalRealocacao profissionalRealocacaoEntity = getProfissionalRealocacao(idProfissionalRealocacao);
        ProfissionalRealocacaoResponseDTO profissionalRealocacaoResponseDTO = objectMapper.convertValue(profissionalRealocacaoEntity, ProfissionalRealocacaoResponseDTO.class);
        return profissionalRealocacaoResponseDTO;
    }

    private ProfissionalRealocacao getProfissionalRealocacao(Long idProfissionalRealocacao) throws Exception {
        ProfissionalRealocacao profissionalRealocacaoRecuperado = profissionalRealocacaoRepository.getAll().stream()
                .filter(profissionalRealocacao -> profissionalRealocacao.getId().equals(idProfissionalRealocacao))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("O profissional realocacao de ID " + idProfissionalRealocacao + " não foi encontrado!"));
        return profissionalRealocacaoRecuperado;
    }

//    private ArrayList<ProfissionalRealocacao> lista = new ArrayList<>();
//    private ProfissionalRealocacaoRepository profRealocRepository;
//
//    public void cadastrar(ProfissionalRealocacao profissionalRealocacao) {
//        if (profissionalRealocacao == null) {
//            System.err.println("❌ O usuário não pode ser nulo!");
//        }
//
//        try {
//            lista.add(profissionalRealocacao);
//            profRealocRepository.create(profissionalRealocacao);
//        } catch (BancoDeDadosException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("\n✅ Profissional Realocacao cadastrado!");
//    }
//
//    public void listarUm(Long id) {
//        try {
//            profRealocRepository.getById(id);
//        } catch (BancoDeDadosException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void listarTodos() {
//        if (lista.isEmpty()) {
//            System.err.println("❌ Nenhum Profissional Realocacao cadastrado!");
//            return;
//        }
//
//        for (ProfissionalRealocacao profissionalRealocacao : lista) {
//            System.out.println(profissionalRealocacao);
//        }
//    }
//
//    public void atualizar(Long id, ProfissionalRealocacao profissionalRealocacaoAtualiza) {
//        for (int i = 0; i < lista.size(); i++) {
//            ProfissionalRealocacao profissionalRealocacao = lista.get(i);
//
//            if (profissionalRealocacao.getId() == id) {
//                profissionalRealocacao.setNome(profissionalRealocacaoAtualiza.getNome());
//                profissionalRealocacao.setCpf(profissionalRealocacaoAtualiza.getCpf());
//                profissionalRealocacao.setDataNascimento(profissionalRealocacaoAtualiza.getDataNascimento());
//                profissionalRealocacao.setEmail(profissionalRealocacaoAtualiza.getEmail());
//                profissionalRealocacao.setPlano(profissionalRealocacaoAtualiza.getPlano());
//                profissionalRealocacao.setInteresses(profissionalRealocacaoAtualiza.getInteresses());
//                profissionalRealocacao.setImagemDocumento(profissionalRealocacaoAtualiza.getImagemDocumento());
//                profissionalRealocacao.setControleParental(profissionalRealocacaoAtualiza.getControleParental());
//                profissionalRealocacao.setAcessoPcd(profissionalRealocacaoAtualiza.getAcessoPcd());
//                profissionalRealocacao.setProfissao(profissionalRealocacaoAtualiza.getProfissao());
//                profissionalRealocacao.setObjetivoProfissional(profissionalRealocacaoAtualiza.getObjetivoProfissional());
//                System.out.println("✅ Profissional Realocação atualizado!");
//                return;
//            }
//        }
//    }
//
//    public void remover(Long id) {
//        try {
//            ProfissionalRealocacao profissionalRealocacaoDeletar = profRealocRepository.getById(id);
//
//            if (profissionalRealocacaoDeletar == null)
//            System.err.println("❌ Usuário não encontrado!");
//
//            lista.remove(profissionalRealocacaoDeletar);
//            profRealocRepository.delete(profissionalRealocacaoDeletar.getId());
//            System.out.println("✅ Usuário removido!");
//        } catch (BancoDeDadosException e) {
//            System.err.println("❌ Erro ao remover usuário: " + e.getMessage());
//        }
//    }
}