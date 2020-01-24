package com.example.cmuproject.model;

public class PendentToma {

    private String altura;

    private String medicamento;

    public PendentToma(String altura, String medicamento) {
        this.altura = altura;
        this.medicamento = medicamento;
    }

    public String getAltura() {
        return altura;
    }

    public String getMedicamento() {
        return medicamento;
    }

    @Override
    public String toString() {
        return "PendentToma{" +
                "altura='" + altura + '\'' +
                ", medicamento='" + medicamento + '\'' +
                '}';
    }
}
