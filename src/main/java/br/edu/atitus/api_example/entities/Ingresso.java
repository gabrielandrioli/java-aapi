package br.edu.atitus.api_example.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "ingressos")
public class Ingresso {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo;

    private Double valor;


    @ManyToOne
    @JoinColumn(name = "evento_id")
    private EventoEntity evento;

    public EventoEntity getEvento() {
        return evento;
    }

    public void setEvento(EventoEntity evento) {
        this.evento = evento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }



}