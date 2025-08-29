package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NinjaService {

    private NinjaRepository ninjaRepository;
    private NinjaMapper ninjaMapper;

    public NinjaService(NinjaRepository ninjaRepository, NinjaMapper ninjaMapper) {
        this.ninjaRepository = ninjaRepository;
        this.ninjaMapper = ninjaMapper;
    }

    // listar todos os meus ninjas
    public List<NinjaModel> listarNinjas(){
        return ninjaRepository.findAll(); // procura tudo que temos no ninjaRepository no meu banco de dados
    }

    //Listar os ninjas por id
    public NinjaModel listarNinjasPorId(Long id){
        Optional<NinjaModel> ninjaPorId = ninjaRepository.findById(id);
        return ninjaPorId.orElse(null);
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
    public NinjaModel atualizarNinja(Long id, NinjaModel ninjaModel){
        if(ninjaRepository.existsById(id)){
            ninjaModel.setId(id);
            return ninjaRepository.save(ninjaModel);
        }
        return null;
    }

}
