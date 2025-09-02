package dev.java10x.CadastroDeNinjas.Missoes;
import dev.java10x.CadastroDeNinjas.Ninjas.NinjaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    // swagger
    @Operation(summary = "Lista todas as missoes", description = "Rota lista todas as missoes cadastrados no banco de dados")
    // swagger
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missoes listadas com sucesso"),
    })
    public ResponseEntity<List<MissoesDTO>> listarMissoes(){
        return ResponseEntity.ok(missoesService.listarMissoes());
    }

    //mostra algo pro usuario
    @GetMapping("/listar/{id}")
    // swagger
    @Operation(summary = "Lista a missao por id", description = "Rota lista uma missao pelo id")
    // swagger
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missao encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Missao não encontrada")
    })
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
    // swagger
    @Operation(summary = "Cria uma nova missao", description = "Rota cria uma nova missao e insere no banco de dados")
    // swagger
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Missao criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na criação da missao")
    })
    public ResponseEntity<String> criarMissao(@RequestBody MissoesDTO missoesDTO) {
        MissoesDTO missao = missoesService.criarMissao(missoesDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Ninja com o nome " + missao.getNome() + " e de id " + missao.getId() + " foi criado com sucesso");
    }

    //mandar uma requisicao para alterar as missoes
    @PutMapping("/alterar/{id}")
    // swagger
    @Operation(summary = "Altera uma missao por id", description = "Rota altera uma missao pelo seu id")
    // swagger
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missao alterada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Missao não encontrada")
    })
    public ResponseEntity<String> alterarMissao(
            @Parameter(description = "Usuario manda o id no caminho da requisicao") // swagger
            @PathVariable Long id,
            @Parameter(description = "Usuario manda os dados da missao a ser atualizada no corpo da requisicao") // swagger
            @RequestBody MissoesDTO missoesDTO){
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
    // swagger
    @Operation(summary = "Deleta uma missao", description = "Rota deleta uma missao no banco de dados atraves de seu id")
    // swagger
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missao foi deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Missao nao foi encontrada")
    })
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
