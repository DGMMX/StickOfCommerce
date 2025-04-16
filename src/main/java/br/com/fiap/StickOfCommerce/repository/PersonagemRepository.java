package br.com.fiap.StickOfCommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.StickOfCommerce.model.Personagem;

import java.util.List;
import java.util.Optional;

public interface PersonagemRepository extends JpaRepository<Personagem, Long> {
    Optional<Personagem> findByNome(String nome);
    
    List<Personagem> findByClasse(String classe);
}
