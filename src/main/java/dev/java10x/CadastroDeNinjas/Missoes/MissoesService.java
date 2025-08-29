package dev.java10x.CadastroDeNinjas.Missoes;

import dev.java10x.CadastroDeNinjas.Ninjas.NinjaDTO;
import dev.java10x.CadastroDeNinjas.Ninjas.NinjaModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MissoesService {

    private MissoesRepository missoesRepository;
    private MissoesMapper missoesMapper;

    public MissoesService(MissoesRepository missoesRepository) {
        this.missoesRepository = missoesRepository;
    }

    public List<MissoesDTO> listarMissoes(){
        List<MissoesModel> missoes = missoesRepository.findAll();// procura tudo que temos no ninjaRepository no meu banco de dados
        return missoes.stream()
                .map(missoesMapper::map)
                .collect(Collectors.toList());
    }

    public MissoesDTO listarMissoesPorId(Long id){
        Optional<MissoesModel> missaoPorId = missoesRepository.findById(id);
        return missaoPorId.map(missoesMapper::map).orElse(null);
    }

    public MissoesDTO criarMissao(MissoesDTO missoesDTO){
        MissoesModel missoes = missoesMapper.map(missoesDTO);
        missoes = missoesRepository.save(missoes);
        return missoesMapper.map(missoes);

    }

    public void deletarMissaoPorId(Long id){
        missoesRepository.deleteById(id);
    }

    public MissoesDTO atualizarMissao(Long id, MissoesDTO missoesDTO){
            Optional<MissoesModel> missaoExistente = missoesRepository.findById(id); // procura pelo id no banco de dados
            if(missaoExistente.isPresent()){ // se o id existir
                MissoesModel ninjaAtualizado = missoesMapper.map(missoesDTO); // pega o mapper do dto
                ninjaAtualizado.setId(id); //sobescreve o id
                MissoesModel ninjaSalvo = missoesRepository.save(ninjaAtualizado); // da um save no banco de dados com o ninja atualizado
                return missoesMapper.map(ninjaSalvo); // retorna o ninja que foi salvo no banco de dados atraves do mapper
            }
            return null;
        }
}
