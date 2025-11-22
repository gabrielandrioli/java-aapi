package br.edu.atitus.api_example.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.atitus.api_example.dtos.Pedido.PedidoRequestDTO;
import br.edu.atitus.api_example.dtos.Pedido.PedidoResponseDTO;
import br.edu.atitus.api_example.entities.PedidoEntity;
import br.edu.atitus.api_example.entities.UserEntity;
import br.edu.atitus.api_example.repositories.PedidoRepository;
import br.edu.atitus.api_example.repositories.UserRepository;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository repository;
    private final UserRepository userRepository;

    public PedidoServiceImpl(PedidoRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public PedidoResponseDTO criar(PedidoRequestDTO dto) {
        PedidoEntity entity = new PedidoEntity();
        
        UserEntity comprador = userRepository.findById(dto.getCompradorId())
                .orElseThrow(() -> new RuntimeException("Comprador não encontrado"));
        
        entity.setComprador(comprador);
        entity.setDataCompra(dto.getDataCompra());

        PedidoEntity salvo = repository.save(entity);

        return new PedidoResponseDTO(
                salvo.getId(),
                salvo.getComprador().getId(),
                salvo.getComprador().getName(),
                salvo.getDataCompra()
        );
    }

    @Transactional
    @Override
    public PedidoResponseDTO atualizar(Long id, PedidoRequestDTO dto) {
        PedidoEntity existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        UserEntity comprador = userRepository.findById(dto.getCompradorId())
                .orElseThrow(() -> new RuntimeException("Comprador não encontrado"));

        existente.setComprador(comprador);
        existente.setDataCompra(dto.getDataCompra());

        PedidoEntity atualizado = repository.save(existente);

        return new PedidoResponseDTO(
                atualizado.getId(),
                atualizado.getComprador().getId(),
                atualizado.getComprador().getName(),
                atualizado.getDataCompra()
        );
    }

    @Transactional
    @Override
    public void deletar(Long id) {
        repository.deleteById(id);
    }

    @Override
    public PedidoResponseDTO buscarPorId(Long id) {
        PedidoEntity encontrado = repository.findById(id)
                .orElse(null);

        if (encontrado == null)
            return null;

        return new PedidoResponseDTO(
                encontrado.getId(),
                encontrado.getComprador().getId(),
                encontrado.getComprador().getName(),
                encontrado.getDataCompra()
        );
    }

    @Override
    public List<PedidoResponseDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(p -> new PedidoResponseDTO(
                        p.getId(),
                        p.getComprador().getId(),
                        p.getComprador().getName(),
                        p.getDataCompra()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<PedidoResponseDTO> listarPorComprador(UUID compradorId) {
        return repository.findByCompradorId(compradorId)
                .stream()
                .map(p -> new PedidoResponseDTO(
                        p.getId(),
                        p.getComprador().getId(),
                        p.getComprador().getName(),
                        p.getDataCompra()
                ))
                .collect(Collectors.toList());
    }
}
