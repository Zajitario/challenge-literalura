package com.challenge.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DatosLibro {

    @JsonAlias("title")
    private String titulo;

    @JsonAlias("authors")
    private List<DatosAutor> autores;

    @JsonAlias("languages")
    private List<String> idiomas;

    @JsonAlias("download_count")
    private int numeroDescargas;

    public String getTitulo() {
        return titulo;
    }

    public List<DatosAutor> getAutores() {
        return autores;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public int getNumeroDescargas() {
        return numeroDescargas;
    }

    @Override
    public String toString() {
        return "Título: " + titulo +
                "\nAutor(es): " + autores +
                "\nIdiomas: " + idiomas +
                "\nDescargas: " + numeroDescargas;
    }
}