package br.com.senac.java_spring_aula.cliente.repository;

import br.com.senac.java_spring_aula.cliente.model.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Integer> {

    Optional<ClienteEntity> findByEmail(String email);

    Optional<ClienteEntity> findByCpf(String cpf);

    @Query("SELECT c FROM ClienteEntity c WHERE c.email = :email")
    Optional<ClienteEntity> buscarPorEmail(String email);

}