package com.challenge.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RespuestaAPI {

    @JsonAlias("results")
    private List<DatosLibro> resultados;

    public List<DatosLibro> getResultados() {
        return resultados;
    }
    public void setResults(List<DatosLibro> results) { this.resultados = results; }
}