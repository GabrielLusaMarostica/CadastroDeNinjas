package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NinjaService {

    private NinjaRepository ninjaRepository;
    private NinjaMapper ninjaMapper;

    public NinjaService(NinjaRepository ninjaRepository, NinjaMapper ninjaMapper) {
        this.ninjaRepository = ninjaRepository;
        this.ninjaMapper = ninjaMapper;
    }

    // listar todos os meus ninjas
    public List<NinjaDTO> listarNinjas(){
        List<NinjaModel> ninjas = ninjaRepository.findAll();// procura tudo que temos no ninjaRepository no meu banco de dados
        return ninjas.stream()
                .map(ninjaMapper::map)
                .collect(Collectors.toList());
    }

    //Listar os ninjas por id
    public NinjaDTO listarNinjasPorId(Long id){
        Optional<NinjaModel> ninjaPorId = ninjaRepository.findById(id);
        return ninjaPorId.map(ninjaMapper::map).orElse(null);
    }

    // criar um novo ninja
    public NinjaDTO criarNinja(NinjaDTO ninjaDTO){
        NinjaModel ninja = ninjaMapper.map(ninjaDTO); // cria uma instancia do ninjamodel, e atribui os atributos do ninjaDTO, atraves do mapper
        ninja = ninjaRepository.save(ninja); // usa o metodo save para salvar o ninja no banco de dados
        return ninjaMapper.map(ninja); // retorna o nosso ninja para o usuario
    }

    // Deletar o ninja - tem que ser um metodo void, pois nao retornará nada, apenas deletará
    public void deletarNinjaPorId(Long id){
        ninjaRepository.deleteById(id);
    }

    // Atualizar ninja
    public NinjaDTO atualizarNinja(Long id, NinjaDTO ninjaDTO){
        Optional<NinjaModel> ninjaExistente = ninjaRepository.findById(id); // procura pelo id no banco de dados
        if(ninjaExistente.isPresent()){ // se o id existir
            NinjaModel ninjaAtualizado = ninjaMapper.map(ninjaDTO); // pega o mapper do dto
            ninjaAtualizado.setId(id); //sobescreve o id
            NinjaModel ninjaSalvo = ninjaRepository.save(ninjaAtualizado); // da um save no banco de dados com o ninja atualizado
            return ninjaMapper.map(ninjaSalvo); // retorna o ninja que foi salvo no banco de dados atraves do mapper
        }
        return null;
    }

}
