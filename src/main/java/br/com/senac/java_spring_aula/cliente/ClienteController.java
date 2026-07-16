package br.com.senac.java_spring_aula.cliente;

import br.com.senac.java_spring_aula.cliente.model.ClienteEntity;
import br.com.senac.java_spring_aula.cliente.model.ClientePostDTO;
import br.com.senac.java_spring_aula.cliente.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteRepository repository;

    public ClienteController(ClienteRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@Valid @RequestBody ClientePostDTO dto) {

        Optional<ClienteEntity> clienteExistente = repository.findByEmail(dto.email());

        if (clienteExistente.isPresent()) {

            Map<String, String> erro = new HashMap<>();
            erro.put("erro", "E-mail já cadastrado");

            return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
        }

        ClienteEntity cliente = new ClienteEntity();

        cliente.setNome(dto.nome());
        cliente.setEmail(dto.email());
        cliente.setCpf(dto.cpf());
        cliente.setDataCadastro(LocalDateTime.now());

        repository.save(cliente);

        return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
    }

    @GetMapping
    public ResponseEntity<List<ClienteEntity>> listar() {

        return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable int id) {

        Optional<ClienteEntity> cliente = repository.findById(id);

        if (cliente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(cliente.get());

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable int id) {

        Optional<ClienteEntity> cliente = repository.findById(id);

        if (cliente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        repository.delete(cliente.get());

        return ResponseEntity.noContent().build();

    }

}
