package dev.java10x.CadastroDeNinjas.Missoes;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("missoes")
public class MissoesController {

    private MissoesService missoesService;

    public MissoesController(MissoesService missoesService) {
        this.missoesService = missoesService;
    }

    //mostra algo pro usuario
    @GetMapping("/listar")
    public List<MissoesModel> listarMissoes(){
        return missoesService.listarMissoes();
    }

    //mostra algo pro usuario
    @GetMapping("/listar/{id}")
    public MissoesModel listarMissoes(@PathVariable Long id){
        return missoesService.listarMissoesPorId(id);
    }

    // usada quando o usuario deve mandar algo para nós
    @PostMapping("/criar")
    public MissoesDTO criarMissao(@RequestBody MissoesDTO missoesDTO) {
        return missoesService.criarMissao(missoesDTO);
    }

    //mandar uma requisicao para alterar as missoes
    @PutMapping("/alterar/{id}")
    public MissoesModel alterarMissao(@PathVariable Long id, @RequestBody MissoesModel missoesModel){
        return missoesService.atualizarMissao(id, missoesModel);
    }

    //usada para deletar as missoes
    @DeleteMapping("/deletar/{id}")
    public void deletarMissao(@PathVariable Long id){
        missoesService.deletarMissaoPorId(id);
    }


}
