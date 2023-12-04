public class preguntas {

    private String enunciado;
    private String respuestaCorrecta;

    public preguntas(String enunciado, String respuestaCorrecta) {
        this.enunciado = enunciado;
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public String getRespuestaCorrecta() {
        return respuestaCorrecta;
    }
}