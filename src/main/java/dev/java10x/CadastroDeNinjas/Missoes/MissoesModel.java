package dev.java10x.CadastroDeNinjas.Missoes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.java10x.CadastroDeNinjas.Ninjas.NinjaModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//Transforma nossa classe em uma entidade no banco de dados
@Entity
//cria o nome da tabela para o banco de dados
@Table(name = "tb_missoes")
//criacao dos construtores com Lombok
@NoArgsConstructor
@AllArgsConstructor
//Criacao dos getters e setters com Lombok
@Data
public class MissoesModel {
    // Notacoes para criacao automatica do Id para o banco de dados
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String dificuldade;
    private String rank;

    // Uma missao pode ter varios ninjas
    @OneToMany(mappedBy = "missoes") // quando utilziado o onetomany, usamos o mappedby com o nome do atributo da outra classe
    @JsonIgnore
    private List<NinjaModel> ninjas;

}
