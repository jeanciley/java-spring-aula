package br.com.senac.java_spring_aula.produto;

import br.com.senac.java_spring_aula.produto.model.ProdutoStatus;
import br.com.senac.java_spring_aula.produto.model.ProdutoEntity;
import br.com.senac.java_spring_aula.produto.model.ProdutoPostDTO;
import br.com.senac.java_spring_aula.produto.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produto")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<ProdutoEntity> cadastrarProduto(@Valid @RequestBody ProdutoPostDTO dto) {

        ProdutoEntity produto = new  ProdutoEntity();

        produto.setNome(dto.nome());
        produto.setPreco(dto.preco());
        produto.setQuantidadeEstoque(dto.quantidadeEstoque());

        if (dto.quantidadeEstoque() == 0){
            produto.setStatus(ProdutoStatus.ESGOTADO);
        }

        ProdutoEntity salve = repository.save(produto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(repository.save(salve));
    }

    @GetMapping
    @Transactional
    public ResponseEntity<List<ProdutoEntity>> listarProduto() {
        List<ProdutoEntity> lista = repository.findAll();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(lista);
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<ProdutoEntity> getById(@PathVariable Integer id) {

        Optional<ProdutoEntity> optional = repository.findById(id);

        if (optional.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(null);
        } else {
            return  ResponseEntity
                    .status(HttpStatus.OK)
                    .body(optional.get());
        }
    }

    @PatchMapping("/{id}/alterar")
    @Transactional
    public ResponseEntity<ProdutoEntity> alterarProduto(@PathVariable int id) {

        Optional<ProdutoEntity> optionalProduto = repository.findById(id);

        if (optionalProduto.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(null);
        }

        ProdutoEntity produtoEntity = optionalProduto.get();

        if (produtoEntity.getStatus() == ProdutoStatus.DISPONIVEL){
            produtoEntity.setStatus(ProdutoStatus.ESGOTADO);
            produtoEntity.setQuantidadeEstoque(0);
        }

        ProdutoEntity salve = repository.save(produtoEntity);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(salve);
    }

    @PatchMapping("/{id}/reabastecer")
    @Transactional
    public ResponseEntity<?> reabastecerEstoque(@PathVariable int id,@RequestParam int quantidade) {
        Optional<ProdutoEntity> optionalProduto = repository.findById(id);

        if (optionalProduto.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(null);
        }

        ProdutoEntity produtoEntity = optionalProduto.get();

        produtoEntity.setQuantidadeEstoque(quantidade +  produtoEntity.getQuantidadeEstoque());

        if (produtoEntity.getQuantidadeEstoque() > 0){
            produtoEntity.setStatus(ProdutoStatus.DISPONIVEL);
        } else {
            produtoEntity.setStatus(ProdutoStatus.ESGOTADO);
        }

        ProdutoEntity  salve = repository.save(produtoEntity);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(salve);
    }
}
