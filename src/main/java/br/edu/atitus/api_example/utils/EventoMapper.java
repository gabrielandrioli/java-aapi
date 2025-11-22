package br.edu.atitus.api_example.utils;

import org.springframework.stereotype.Component;

import br.edu.atitus.api_example.dtos.Evento.EventoRequestDTO;
import br.edu.atitus.api_example.dtos.Evento.EventoResponseDTO;
import br.edu.atitus.api_example.entities.EventoEntity;

@Component
public class EventoMapper {

    public EventoEntity toEntity(EventoRequestDTO dto) {
        EventoEntity e = new EventoEntity();
        e.setTitulo(dto.getTitulo());
        e.setDescricao(dto.getDescricao());
        e.setData(dto.getData());
        return e;
    }

    public EventoResponseDTO toDTO(EventoEntity e) {
        return new EventoResponseDTO(
                e.getId(),
                e.getTitulo(),
                e.getDescricao(),
                e.getData()
        );
    }

    public void updateEntity(EventoRequestDTO dto, EventoEntity e) {
        e.setTitulo(dto.getTitulo());
        e.setDescricao(dto.getDescricao());
        e.setData(dto.getData());
    }
}
