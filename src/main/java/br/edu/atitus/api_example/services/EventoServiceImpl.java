package br.edu.atitus.api_example.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.atitus.api_example.dtos.Evento.EventoRequestDTO;
import br.edu.atitus.api_example.dtos.Evento.EventoResponseDTO;
import br.edu.atitus.api_example.entities.EventoEntity;
import br.edu.atitus.api_example.repositories.EventoRepository;

@Service
public class EventoServiceImpl implements EventoService {

    private final EventoRepository repository;

    public EventoServiceImpl(EventoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public EventoResponseDTO criar(EventoRequestDTO dto) {
        EventoEntity entity = new EventoEntity();
        entity.setTitulo(dto.getTitulo());
        entity.setDescricao(dto.getDescricao());
        entity.setData(dto.getData());

        EventoEntity salvo = repository.save(entity);

        return new EventoResponseDTO(
                salvo.getId(),
                salvo.getTitulo(),
                salvo.getDescricao(),
                salvo.getData()
        );
    }

    @Transactional
    @Override
    public EventoResponseDTO atualizar(Long id, EventoRequestDTO dto) {
        EventoEntity existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));

        existente.setTitulo(dto.getTitulo());
        existente.setDescricao(dto.getDescricao());
        existente.setData(dto.getData());

        EventoEntity atualizado = repository.save(existente);

        return new EventoResponseDTO(
                atualizado.getId(),
                atualizado.getTitulo(),
                atualizado.getDescricao(),
                atualizado.getData()
        );
    }

    @Transactional
    @Override
    public void deletar(Long id) {
        repository.deleteById(id);
    }

    @Override
    public EventoResponseDTO buscarPorId(Long id) {
        EventoEntity encontrado = repository.findById(id)
                .orElse(null);

        if(encontrado ==null)
            return null;

        return new EventoResponseDTO(
                encontrado.getId(),
                encontrado.getTitulo(),
                encontrado.getDescricao(),
                encontrado.getData()
        );
    }

    @Override
    public List<EventoResponseDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(e -> new EventoResponseDTO(
                        e.getId(),
                        e.getTitulo(),
                        e.getDescricao(),
                        e.getData()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public EventoResponseDTO buscarPorTitulo(String titulo) {
        EventoEntity encontrado = repository.findByTitulo(titulo)
                .orElse(null);

        if(encontrado ==null)
            return null;

        return new EventoResponseDTO(
                encontrado.getId(),
                encontrado.getTitulo(),
                encontrado.getDescricao(),
                encontrado.getData()
        );
    }
}
