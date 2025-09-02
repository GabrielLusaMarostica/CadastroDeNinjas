package dev.java10x.CadastroDeNinjas.Ninjas;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
   @Operation(summary = "Mensagem de boas vindas", description = "Essa rota da uma mensagem de boas vindas para quem acessa ela") // swagger
    public String boasVindas(){
        return "Essa é minha primeira mensagem nessa rota";
    }

    // Adicionar ninja (CREATE)
    @PostMapping("/criar")
    // swagger
    @Operation(summary = "Cria um novo ninja", description = "Rota cria um novo ninja e insere no banco de dados")
    // swagger
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ninja criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na criação do ninja")
    })
    public ResponseEntity<String> criarNinja(@RequestBody NinjaDTO ninjaDTO){

       NinjaDTO ninja = ninjaService.criarNinja(ninjaDTO); // ao inves de passar diretamente o model para a funcao de criar um ninja, agora passa um ninjaDTO
       return ResponseEntity.status(HttpStatus.CREATED)
            .body("Ninja criado com sucesso: " + ninja.getNome() + " (ID): " + ninja.getId()); // corpo da mensagem que retornará ao usuario
    }

    // Mostrar todos os ninjas (READ)
    @GetMapping("/listar")
    // swagger
    @Operation(summary = "Lista todos os ninjas", description = "Rota lista todos os ninjas cadastrados no banco de dados")
    // swagger
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninjas listados com sucesso"),
    })
    public ResponseEntity<List<NinjaDTO>> listarNinjas(){
        return ResponseEntity.ok(ninjaService.listarNinjas());
    }

    // Mostrar ninja por id (READ)
    @GetMapping("/listar/{id}")
    // swagger
    @Operation(summary = "Lista o ninja por id", description = "Rota lista um ninja pelo id")
    // swagger
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ninja não encontrado")
    })
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
    // swagger
    @Operation(summary = "Altera um ninja por id", description = "Rota altera um ninja pelo seu id")
    // swagger
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja alterado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ninja não encontrado")
    })
    public ResponseEntity<String> alterarNinjaPorId(
            @Parameter(description = "Usuario manda o id no caminho da requisicao") // swagger
            @PathVariable Long id,
            @Parameter(description = "Usuario manda os dados do ninja a ser atualizado no corpo da requisicao") // swagger
            @RequestBody NinjaDTO ninjaModel){
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
    // swagger
    @Operation(summary = "Deleta um ninja", description = "Rota deleta um ninja no banco de dados atraves de seu id")
    // swagger
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja foi deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ninja nao foi encontrado")
    })
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
