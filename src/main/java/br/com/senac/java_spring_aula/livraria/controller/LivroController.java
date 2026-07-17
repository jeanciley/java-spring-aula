package br.com.senac.java_spring_aula.livraria.controller;

import br.com.senac.java_spring_aula.livraria.model.LivroEntity;
import br.com.senac.java_spring_aula.livraria.model.LivroPostDTO;
import br.com.senac.java_spring_aula.livraria.repository.LivroRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livraria")
@RequiredArgsConstructor
public class LivroController {

    private final LivroRepository livroRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<LivroEntity> cadastrarLivro(@Valid @RequestBody LivroPostDTO dto){

        LivroEntity livro = new LivroEntity();

        livro.setTitulo(dto.titulo());
        livro.setAutor(dto.autor());
        livro.setAnoPublicacao(dto.anoPublicacao());

        LivroEntity livroSalvo = livroRepository.save(livro);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(livroSalvo);
    }

    @GetMapping
    public ResponseEntity<List<LivroEntity>> listarLivro(){
        List<LivroEntity> livros = livroRepository.findAll();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(livros);
    }
}
