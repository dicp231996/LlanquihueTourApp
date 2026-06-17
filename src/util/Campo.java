package util;

public class Campo {
    private String pregunta;
    private TipoDato tipoRegla;

    public Campo(String pregunta, TipoDato tipoRegla) {
        this.pregunta = pregunta;
        this.tipoRegla = tipoRegla;
    }

    public String getPregunta() {
        return pregunta;
    }

    public TipoDato getTipoRegla() {
        return tipoRegla;
    }
}
