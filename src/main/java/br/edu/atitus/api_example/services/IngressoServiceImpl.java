package br.edu.atitus.api_example.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.atitus.api_example.dtos.Ingresso.IngressoRequestDTO;
import br.edu.atitus.api_example.dtos.Ingresso.IngressoResponseDTO;
import br.edu.atitus.api_example.entities.EventoEntity;
import br.edu.atitus.api_example.entities.Ingresso;
import br.edu.atitus.api_example.repositories.EventoRepository;
import br.edu.atitus.api_example.repositories.IngressoRepository;

@Service
public class IngressoServiceImpl implements IngressoService {

    private final IngressoRepository repository;
    private final EventoRepository EventoRepository;
    

    public IngressoServiceImpl(IngressoRepository repository, EventoRepository eventoRepository) {
        this.repository = repository;
        this.EventoRepository= eventoRepository;
    }

    @Transactional
    @Override
    public IngressoResponseDTO criar(IngressoRequestDTO dto) {
        Ingresso entity = new Ingresso();
        
            EventoEntity evento = EventoRepository.findById(dto.getEventoId())
        .orElseThrow(() -> new RuntimeException("Evento não encontrado"));

        entity.setTipo(dto.getTipoIngresso());
        entity.setValor(dto.getValor());
        entity.setEvento(evento);

        Ingresso salvo = repository.save(entity);

        return new IngressoResponseDTO(
                salvo.getId(),
                salvo.getTipo(),
                salvo.getValor(),
                salvo.getEvento().getId()
        );
    }

    @Transactional
    @Override
    public IngressoResponseDTO atualizar(Long id, IngressoRequestDTO dto) {
        Ingresso existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ingresso não encontrado"));


        existente.setTipo(dto.getTipoIngresso().toString());

        Ingresso atualizado = repository.save(existente);

        return new IngressoResponseDTO(
                atualizado.getId(),
                atualizado.getTipo(),
                atualizado.getValor(),
                atualizado.getEvento().getId()
        );
    }

    @Transactional
    @Override
    public void deletar(Long id) {
        repository.deleteById(id);
    }

    @Override
    public IngressoResponseDTO buscarPorId(Long id) {
        Ingresso encontrado = repository.findById(id)
                .orElse(null);

        if (encontrado == null)
            return null;

        return new IngressoResponseDTO(
                encontrado.getId(),
                encontrado.getTipo(),
                encontrado.getValor(),
                encontrado.getEvento().getId()
        );
    }

  
    @Override
    public List<IngressoResponseDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(i -> new IngressoResponseDTO(
                        i.getId(),
                        i.getTipo(),
                        i.getValor(),
                        i.getEvento().getId()
                ))
                .collect(Collectors.toList());
    }

}
