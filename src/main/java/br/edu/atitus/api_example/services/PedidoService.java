package br.edu.atitus.api_example.services;

import java.util.List;
import java.util.UUID;

import br.edu.atitus.api_example.dtos.Pedido.PedidoRequestDTO;
import br.edu.atitus.api_example.dtos.Pedido.PedidoResponseDTO;

public interface PedidoService {

    PedidoResponseDTO criar(PedidoRequestDTO novo);

    PedidoResponseDTO atualizar(Long id, PedidoRequestDTO pedido);

    void deletar(Long id);

    PedidoResponseDTO buscarPorId(Long id);

    List<PedidoResponseDTO> listarTodos();
    
    List<PedidoResponseDTO> listarPorComprador(UUID compradorId);
}
