package br.edu.atitus.api_example.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.atitus.api_example.dtos.Ingresso.IngressoRequestDTO;
import br.edu.atitus.api_example.dtos.Ingresso.IngressoResponseDTO;
import br.edu.atitus.api_example.services.IngressoService;

@RestController
@RequestMapping("/ingressos")
public class IngressoController {

    private final IngressoService service;

    public IngressoController(IngressoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<IngressoResponseDTO> criar(@RequestBody IngressoRequestDTO dto) {
        return ResponseEntity.ok(service.criar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<IngressoResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody IngressoRequestDTO dto) {

        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngressoResponseDTO> buscarPorId(@PathVariable Long id) {

        var result = service.buscarPorId(id);
        if (result == null) 
            return ResponseEntity.notFound().build();
    
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<List<IngressoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
