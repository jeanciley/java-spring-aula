package br.com.senac.java_spring_aula.livraria.repository;

import br.com.senac.java_spring_aula.livraria.model.LivroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<LivroEntity, Integer> {

}
