package com.challenge.literalura.repository;

import com.challenge.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    List<Libro> findByIdioma(String idioma);
    long countByIdiomaIgnoreCase(String idioma);

    @Query("SELECT DISTINCT l.autor FROM Libro l")
    List<String> findAllAutores();
}