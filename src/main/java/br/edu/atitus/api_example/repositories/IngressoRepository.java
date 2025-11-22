package br.edu.atitus.api_example.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.atitus.api_example.entities.Ingresso;

public interface IngressoRepository extends JpaRepository<Ingresso, Long> {
    
    Optional<Ingresso> findById(Long id);
    
    void deleteById(Long id);
}
