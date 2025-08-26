package dev.java10x.CadastroDeNinjas.Missoes;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("missoes")
public class MissoesController {

    //mostra algo pro usuario
    @GetMapping("/listar")
    public String listarMissoes(){
        return "Missoes listadas com sucesso";
    }

    // usada quando o usuario deve mandar algo para nós
    @PostMapping("/criar")
    public String criarMissao(){
        return "Missao criada com sucesso";
    }

    //mandar uma requisicao para alterar as missoes
    @PutMapping("/alterar")
    public String alterarMissao(){
        return "Missao criada com sucesso";
    }

    //usada para deletar as missoes
    @DeleteMapping("/deletar")
    public String deletarMissao(){
        return "Missao deletada com sucesso";
    }


}
