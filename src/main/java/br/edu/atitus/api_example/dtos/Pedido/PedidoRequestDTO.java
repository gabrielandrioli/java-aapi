package br.edu.atitus.api_example.dtos.Pedido;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import br.edu.atitus.api_example.entities.Ingresso;

public class PedidoRequestDTO {
    private UUID compradorId;
    private LocalDateTime dataCompra;
    private List<Long> ingressosIds;

    public PedidoRequestDTO() {
    }

    public PedidoRequestDTO(UUID compradorId, LocalDateTime dataCompra,List<Long> ingressosIds) {
        this.compradorId = compradorId;
        this.dataCompra = dataCompra;
        this.ingressosIds = ingressosIds;
    }

    public UUID getCompradorId() {
        return compradorId;
    }

    public void setCompradorId(UUID compradorId) {
        this.compradorId = compradorId;
    }

    public LocalDateTime getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDateTime dataCompra) {
        this.dataCompra = dataCompra;
    }

    public List<Long> getIngressosIds() {
        return ingressosIds;
    }

    public void setIngressosIds(List<Long> ingressosIds) {
        this.ingressosIds = ingressosIds;
    }
}
