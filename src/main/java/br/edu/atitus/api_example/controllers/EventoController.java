package br.edu.atitus.api_example.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.atitus.api_example.dtos.Evento.EventoRequestDTO;
import br.edu.atitus.api_example.dtos.Evento.EventoResponseDTO;
import br.edu.atitus.api_example.services.EventoService;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    private final EventoService service;

    public EventoController(EventoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<EventoResponseDTO> criar(@RequestBody EventoRequestDTO dto) {
        return ResponseEntity.ok(service.criar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventoResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody EventoRequestDTO dto) {

        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventoResponseDTO> buscarPorId(@PathVariable Long id) {

        var result = service.buscarPorId(id);
        if (result == null) 
            return ResponseEntity.notFound().build();
    
        return ResponseEntity.ok(result);
    }


    @GetMapping("/getByTitulo/{titulo}")
    public ResponseEntity<EventoResponseDTO> buscarPorTitulo(@PathVariable String titulo) {

        var result = service.buscarPorTitulo(titulo);
        if (result == null) 
            return ResponseEntity.notFound().build();
    
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<List<EventoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
