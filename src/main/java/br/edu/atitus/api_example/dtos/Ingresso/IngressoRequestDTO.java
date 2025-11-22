package br.edu.atitus.api_example.dtos.Ingresso;

import br.edu.atitus.api_example.entities.TipoIngressoEnum;

public class IngressoRequestDTO {
    private TipoIngressoEnum tipoIngresso;
    private Double valor;
    private Long eventoId;


    public IngressoRequestDTO(TipoIngressoEnum tipoIngresso, Double valor,Long eventoId) {
        this.tipoIngresso = tipoIngresso;
        this.valor = valor;
        this.eventoId = eventoId;
    }

    public String getTipoIngresso() {
        return tipoIngresso.toString();
    }

    public void setTipoIngresso(TipoIngressoEnum tipoIngresso) {
        this.tipoIngresso = tipoIngresso;
    }

    public Double getValor() {
        return this.valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Long getEventoId() {
        return eventoId;
    }

    public void setEventoId(Long eventoId) {
        this.eventoId = eventoId;
    }
}
