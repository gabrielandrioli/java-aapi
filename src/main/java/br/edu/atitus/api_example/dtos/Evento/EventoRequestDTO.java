package br.edu.atitus.api_example.dtos.Evento;

import java.time.LocalDate;

public class EventoRequestDTO {
    private String titulo;
    private String descricao;
    private LocalDate data;

    public EventoRequestDTO() {
    }

    public EventoRequestDTO(String titulo, String descricao, LocalDate data) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.data = data;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
