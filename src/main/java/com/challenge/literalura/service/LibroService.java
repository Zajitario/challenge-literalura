package com.challenge.literalura.service;

import com.challenge.literalura.model.Autor;
import com.challenge.literalura.model.DatosLibro;
import com.challenge.literalura.model.Libro;
import com.challenge.literalura.repository.AutorRepository;
import com.challenge.literalura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroService {
    @Autowired
    private AutorRepository autorRepository;
    @Autowired
    private LibroRepository libroRepository;

    public LibroService(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    private Autor obtenerOcrearAutor(DatosLibro datos) {
        return datos.getAutores().stream()
                .findFirst()
                .map(a -> autorRepository.findByNombreIgnoreCase(a.getNombre())
                        .orElseGet(() -> autorRepository.save(
                                new Autor(a.getNombre(), a.getAnioNacimiento(), a.getAnioFallecimiento())
                        ))
                )
                .orElseThrow(() -> new IllegalArgumentException("El libro no tiene autores conocidos"));
    }

    public void guardarLibroDesdeDatos(DatosLibro datos) {
        Autor autor = obtenerOcrearAutor(datos);
        Libro libro = new Libro(datos);
        libro.setAutor(autor);
        libroRepository.save(libro);
    }
    public long contarLibrosPorIdioma(String idioma) {
        return libroRepository.countByIdiomaIgnoreCase(idioma);
    }
}