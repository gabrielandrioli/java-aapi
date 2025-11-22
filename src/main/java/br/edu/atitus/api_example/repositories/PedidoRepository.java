package br.edu.atitus.api_example.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.atitus.api_example.entities.PedidoEntity;

public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {
    
    Optional<PedidoEntity> findById(Long id);
    
    List<PedidoEntity> findByCompradorId(UUID compradorId);
    
    void deleteById(Long id);
}
