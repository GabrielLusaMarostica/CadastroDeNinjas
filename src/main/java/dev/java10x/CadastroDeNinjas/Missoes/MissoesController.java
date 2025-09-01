package dev.java10x.CadastroDeNinjas.Missoes;
import dev.java10x.CadastroDeNinjas.Ninjas.NinjaDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<MissoesDTO>> listarMissoes(){
        return ResponseEntity.ok(missoesService.listarMissoes());
    }

    //mostra algo pro usuario
    @GetMapping("/listar/{id}")
    public ResponseEntity<String> listarMissoes(@PathVariable Long id){
        if(missoesService.listarMissoesPorId(id) != null){
            return ResponseEntity.status(HttpStatus.FOUND)
                    .body("Ninja de id " + id + " foi encontrado com sucesso: \n" + missoesService.listarMissoesPorId(id));
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Não foi encontrado nenhum ninja com o id " + id);
        }
    }

    // usada quando o usuario deve mandar algo para nós
    @PostMapping("/criar")
    public ResponseEntity<String> criarMissao(@RequestBody MissoesDTO missoesDTO) {
        MissoesDTO missao = missoesService.criarMissao(missoesDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Ninja com o nome " + missao.getNome() + " e de id " + missao.getId() + " foi criado com sucesso");
    }

    //mandar uma requisicao para alterar as missoes
    @PutMapping("/alterar/{id}")
    public ResponseEntity<String> alterarMissao(@PathVariable Long id, @RequestBody MissoesDTO missoesDTO){
        if(missoesService.listarMissoesPorId(id) != null){
            missoesService.atualizarMissao(id, missoesDTO);
            return ResponseEntity.status(HttpStatus.FOUND)
                    .body("A missao de id " + id + " foi alterado com sucesso \n" + missoesDTO);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Não foi encontrada nenhuma missão com o id: " + id);
        }
    }

    //usada para deletar as missoes
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarMissao(@PathVariable Long id){
        if(missoesService.listarMissoesPorId(id) != null){
            missoesService.deletarMissaoPorId(id);
            return ResponseEntity.status(HttpStatus.FOUND)
                    .body("A missao de id " + id + " foi deletada com sucesso");
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Não foi encontrada nenhuma missão com o id: " + id);
        }

    }


}
