package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.stereotype.Component;

@Component
public class NinjaMapper {

    //cria um mapeamento de um ninjaModel para o ninjaDTO, para que o ninjaModel seja igual ao DTO
    public NinjaModel map(NinjaDTO ninjaDTO){
        NinjaModel ninjaModel = new NinjaModel();
        ninjaModel.setId(ninjaDTO.getId());
        ninjaModel.setNome(ninjaDTO.getNome());
        ninjaModel.setIdade(ninjaDTO.getIdade());
        ninjaModel.setEmail(ninjaDTO.getEmail());
        ninjaModel.setLocal(ninjaDTO.getLocal());
        ninjaModel.setMissoes(ninjaDTO.getMissoes());
        ninjaModel.setCorDoOlho(ninjaDTO.getCorDoOlho());
        ninjaModel.setImgUrl(ninjaDTO.getImgUrl());

        return ninjaModel;
    }

    //cria um mapeamento do ninjDTO para o ninjaMOdel , para que o DTO seja igual o model
    public NinjaDTO map(NinjaModel ninjaModel){
        NinjaDTO ninjaDTO = new NinjaDTO();
        ninjaDTO.setId(ninjaModel.getId());
        ninjaDTO.setNome(ninjaModel.getNome());
        ninjaDTO.setIdade(ninjaModel.getIdade());
        ninjaDTO.setEmail(ninjaModel.getEmail());
        ninjaDTO.setLocal(ninjaModel.getLocal());
        ninjaDTO.setMissoes(ninjaModel.getMissoes());
        ninjaDTO.setCorDoOlho(ninjaModel.getCorDoOlho());
        ninjaDTO.setImgUrl(ninjaModel.getImgUrl());

        return ninjaDTO;
    }

}
