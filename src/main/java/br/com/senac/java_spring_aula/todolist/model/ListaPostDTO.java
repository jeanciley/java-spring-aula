package br.com.senac.java_spring_aula.todolist.model;

import jakarta.validation.constraints.NotBlank;

public record ListaPostDTO(
        @NotBlank(message = "O campo descrição é obrigatório!")
        String descricao
) {
}
