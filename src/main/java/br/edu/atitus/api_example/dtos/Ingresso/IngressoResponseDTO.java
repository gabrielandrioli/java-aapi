package br.edu.atitus.api_example.dtos.Ingresso;

public class IngressoResponseDTO {
    private Long id;
    private String tipoIngressoNome;
    private Double valor;
    private Long eventoId;

    public void setTipoIngressoNome(String tipoIngressoNome) {
        this.tipoIngressoNome = tipoIngressoNome;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Long getEventoId() {
        return eventoId;
    }

    public void setEventoId(Long eventoId) {
        this.eventoId = eventoId;
    }

    public IngressoResponseDTO() {
    }

    public IngressoResponseDTO(Long id,String tipoIngressoNome, double valor,Long eventoId) {
        this.id = id;
        this.tipoIngressoNome = tipoIngressoNome;
        this.valor = valor;
        this.eventoId = eventoId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoIngressoNome() {
        return tipoIngressoNome.toString();
    }

    public double getValor() {
        return valor;
    }

    public void setCodigoQr(double valor) {
        this.valor = valor;
    }
}
