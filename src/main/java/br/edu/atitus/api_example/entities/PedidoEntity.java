package br.edu.atitus.api_example.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class PedidoEntity {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "comprador_id", nullable = false)
    private UserEntity comprador;

    private LocalDateTime dataCompra;
    private Double total;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id") 
    private List<Ingresso> ingressos = new ArrayList<>();

    // Método auxiliar para adicionar ingressos
    public void adicionarIngresso(Ingresso ingresso) {
        this.ingressos.add(ingresso);
    }

    public void adicionarIngressos(List<Ingresso> novosIngressos) {
        this.ingressos.addAll(novosIngressos);
    }

    //#region Getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getComprador() {
        return comprador;
    }

    public void setComprador(UserEntity comprador) {
        this.comprador = comprador;
    }

    public LocalDateTime getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDateTime dataCompra) {
        this.dataCompra = dataCompra;
    }

    public Double getTotal() {
        if (ingressos== null || ingressos.isEmpty()) {
            return 0.0;
        }
        
        return ingressos.stream()
                .mapToDouble(Ingresso::getValor)
                .sum();
    }
    public void setTotal(Double total) {
        this.total = total;
    }

    public List<Ingresso> getIngressos() {
        return ingressos;
    }

    public void setIngressos(List<Ingresso> ingressos) {
        this.ingressos = ingressos;
    }
    //#endregion
}