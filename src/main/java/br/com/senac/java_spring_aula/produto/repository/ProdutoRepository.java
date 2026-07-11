package br.com.senac.java_spring_aula.produto.repository;

import br.com.senac.java_spring_aula.produto.model.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Integer> {
}
