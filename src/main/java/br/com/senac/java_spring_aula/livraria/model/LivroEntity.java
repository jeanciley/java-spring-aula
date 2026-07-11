package br.com.senac.java_spring_aula.livraria.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "livraria")
public class LivroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String autor;

    @Column(name = "ano_publicacao")
    private int anoPublicacao;

    @Column
    private Boolean disponivel;

    @PrePersist
    public void prePersist(){
        if (disponivel == null){
            disponivel = true;
        }
    }

}
