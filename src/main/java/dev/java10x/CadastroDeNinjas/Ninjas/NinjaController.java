package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    private NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    //cria um endereco valido para abrir a pagina na web
   @GetMapping("/boasvindas")
    public String boasVindas(){
        return "Essa é minha primeira mensagem nessa rota";
    }

    // Adicionar ninja (CREATE)
    @PostMapping("/criar")
    public ResponseEntity<String> criarNinja(@RequestBody NinjaDTO ninjaDTO){

       NinjaDTO ninja = ninjaService.criarNinja(ninjaDTO); // ao inves de passar diretamente o model para a funcao de criar um ninja, agora passa um ninjaDTO
       return ResponseEntity.status(HttpStatus.CREATED)
            .body("Ninja criado com sucesso: " + ninja.getNome() + " (ID): " + ninja.getId()); // corpo da mensagem que retornará ao usuario
    }

    // Mostrar todos os ninjas (READ)
    @GetMapping("/listar")
    public ResponseEntity<List<NinjaDTO>> listarNinjas(){
        return ResponseEntity.ok(ninjaService.listarNinjas());
    }

    // Mostrar ninja por id (READ)
    @GetMapping("/listar/{id}")
    public ResponseEntity<String> listarNinjasPorId(@PathVariable Long id){
        if(ninjaService.listarNinjasPorId(id) != null){
            return ResponseEntity.status(HttpStatus.FOUND)
                    .body("Ninja de id: " + id + " foi com sucesso: \n" + ninjaService.listarNinjasPorId(id));
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Não foi encontrado nenhum ninja com o id " + id);
        }
    }

    // Alterar dados dos ninjas (UPDATE)
    @PutMapping("/alterar/{id}")
    public ResponseEntity<String> alterarNinjaPorId(@PathVariable Long id, @RequestBody NinjaDTO ninjaModel){
        if(ninjaService.listarNinjasPorId(id) != null){
            NinjaDTO ninja =  ninjaService.atualizarNinja(id, ninjaModel);
            return ResponseEntity.status(HttpStatus.FOUND)
                    .body("O ninja com o nome: " + ninjaModel.getNome() + " e de id: " + id + " foi alterado com sucesso");
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Não foi encontrado um ninja com o id: " + id);
        }
    }

    // Deletar ninja (DELETE)
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarNinjaPorId(@PathVariable Long id){
        if(ninjaService.listarNinjasPorId(id) != null){
            ninjaService.deletarNinjaPorId(id);
            return ResponseEntity.ok()
                    .body("O ninja de id: " + id + " deletado com sucesso");
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("O ninja de id: " + id + " não foi encontrado");
        }

    }
}
