package dev.java10x.CadastroDeNinjas.Ninjas;

import dev.java10x.CadastroDeNinjas.Missoes.MissoesModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// Entity transforma uma classe em uma entidade do banco de dados
// JPA = Java Persistence API
@Entity
//cria o nome da tabela para o banco de dados
@Table(name = "tb_cadastro")
// Criacao dos contrutores com Lombok
@NoArgsConstructor
@AllArgsConstructor
//Criacao dos getters e setters com Lombok
@Data
public class NinjaModel {
    // Notacoes para criacao automatica do Id para o banco de dados
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(unique = true) // diz que essa coluna vai ser unica, pois só um ninja pode ter um email, o email nao pode ser repitido
    private String email;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "idade")
    private int idade;

    // @ManyToOne, um ninja tem uma unica missao
    @ManyToOne
    @JoinColumn(name = "missoes_id") // foreing key ou chave estrangeira
    private MissoesModel missoes;

    private String corDoOlho;

}
