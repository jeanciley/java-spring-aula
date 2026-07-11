package br.com.senac.java_spring_aula.produto.model;

import br.com.senac.java_spring_aula.livraria.model.ProdutoStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record ProdutoPostDTO(
        @NotBlank(message = "Campo nome deve ser preenchido!")
        String nome,

        @NotNull(message = "Preço deve ser preenchido!")
        @Positive(message = "Preço deve ser positivo!")
        BigDecimal preco,

        @NotNull(message = "Quantidade deve ser prenchida!")
        @PositiveOrZero(message = "Quantidade deve ser positiva!")
        int quantidadeEstoque

) {
}
