package com.challenge.literalura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;

    private String idioma;
    private int numeroDescargas;

    public Libro() {}

    public Libro(DatosLibro datos) {
        this.titulo = datos.getTitulo();
        this.idioma = datos.getIdiomas().isEmpty() ? "Desconocido" : datos.getIdiomas().get(0);
        this.numeroDescargas = datos.getNumeroDescargas();
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public int getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public void setNumeroDescargas(int numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }

    @Override
    public String toString() {
        return titulo + " - " + (autor != null ? autor.getNombre() : "Desconocido")
                + " (" + idioma + "), Descargas: " + numeroDescargas;
    }
}