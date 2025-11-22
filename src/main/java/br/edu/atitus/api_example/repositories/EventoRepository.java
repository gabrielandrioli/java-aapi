package br.edu.atitus.api_example.repositories;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import br.edu.atitus.api_example.entities.EventoEntity;

public interface EventoRepository extends JpaRepository<EventoEntity, UUID>{

	boolean existsByTitulo(String titulo);
		
	Optional<EventoEntity> findByTitulo(String titulo);

	Optional<EventoEntity> findById(long id);

	void deleteById(long id);


}
