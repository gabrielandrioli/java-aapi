package br.edu.atitus.api_example.dtos.Pedido;

import java.time.LocalDateTime;
import java.util.UUID;

public class PedidoResponseDTO {
    private Long id;
    private UUID compradorId;
    private String compradorNome;
    private LocalDateTime dataCompra;

    public PedidoResponseDTO() {
    }

    public PedidoResponseDTO(Long id, UUID compradorId, String compradorNome, LocalDateTime dataCompra) {
        this.id = id;
        this.compradorId = compradorId;
        this.compradorNome = compradorNome;
        this.dataCompra = dataCompra;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getCompradorId() {
        return compradorId;
    }

    public void setCompradorId(UUID compradorId) {
        this.compradorId = compradorId;
    }

    public String getCompradorNome() {
        return compradorNome;
    }

    public void setCompradorNome(String compradorNome) {
        this.compradorNome = compradorNome;
    }

    public LocalDateTime getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDateTime dataCompra) {
        this.dataCompra = dataCompra;
    }
}
