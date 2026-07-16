package br.com.senac.java_spring_aula.cliente.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ClientePostDTO(
        @NotBlank(message = "Campo nome deve ser preenchido!")
        String nome,

        @Email
        @NotBlank(message = "Campo email deve ser preenchido!")
        String email,

        @Size(min = 11, max = 11)
        @NotBlank(message = "Campo CPF deve ser preenchido!")
        String cpf
) {
}
