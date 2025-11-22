package br.edu.atitus.api_example.services;

import java.util.List;

import br.edu.atitus.api_example.dtos.Evento.EventoRequestDTO;
import br.edu.atitus.api_example.dtos.Evento.EventoResponseDTO;

public interface EventoService {

    EventoResponseDTO criar(EventoRequestDTO novo);

    EventoResponseDTO atualizar(Long id, EventoRequestDTO evento);

    void deletar(Long id);

    EventoResponseDTO buscarPorId(Long id);

    EventoResponseDTO buscarPorTitulo(String titulo);

    List<EventoResponseDTO> listarTodos();
}
