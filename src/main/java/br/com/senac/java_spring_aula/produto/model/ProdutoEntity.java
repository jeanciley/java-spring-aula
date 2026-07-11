package br.com.senac.java_spring_aula.produto.model;

import br.com.senac.java_spring_aula.livraria.model.ProdutoStatus;
import br.com.senac.java_spring_aula.todolist.model.ListaStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Setter
@Getter
@Table(name = "produto")
public class ProdutoEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String nome;

    @Column
    private BigDecimal preco;

    @Column(name = "quantidade_estoque")
    private Integer quantidadeEstoque;

    @Enumerated(EnumType.STRING)
    private ProdutoStatus status;

    @PrePersist
    public void prePersist() {
        if (status == null){
            status = ProdutoStatus.DISPONIVEL;
        }
    }
}
