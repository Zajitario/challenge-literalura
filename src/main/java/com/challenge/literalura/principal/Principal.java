package com.challenge.literalura.principal;

import com.challenge.literalura.model.*;
import com.challenge.literalura.repository.AutorRepository;
import com.challenge.literalura.repository.LibroRepository;
import com.challenge.literalura.service.ConsumoAPI;
import com.challenge.literalura.service.ConvierteDatos;
import com.challenge.literalura.service.LibroService;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private final Scanner teclado = new Scanner(System.in);
    private final ConsumoAPI consumoApi = new ConsumoAPI();
    private final ConvierteDatos conversor = new ConvierteDatos();
    private final String URL_BASE = "https://gutendex.com/books/?search=";

    private final LibroRepository repositorio;
    private final AutorRepository autorRepository;

    public Principal(LibroRepository repositorio, AutorRepository autorRepository) {
        this.repositorio = repositorio;
        this.autorRepository = autorRepository;
    }

    public void muestraElMenu() {
        int opcion = -1;

        while (opcion != 0) {
            var menu = """
                    \n📚 LITERAlura - Catálogo de Libros 📚
                    1 - Buscar libro por título
                    2 - Mostrar todos los libros guardados
                    3 - Buscar libros por idioma
                    4 - Mostrar todos los autores registrados
                    5 - Listar autores vivos en un año específico
                    6 - Contar libros por idioma
                    7 - Listar Autores vivos en determinado año
                    0 - Salir
                    Seleccione una opción:
                    """;
            System.out.println(menu);
            opcion = Integer.parseInt(teclado.nextLine());

            switch (opcion) {
                case 1 -> buscarLibroPorTitulo();
                case 2 -> mostrarLibrosGuardados();
                case 3 -> buscarPorIdioma();
                case 4 -> mostrarAutores();
                case 5 -> listarAutoresVivosEnAnio();
                case 6 -> contarLibrosPorIdioma();
                case 0 -> System.out.println("Saliendo del programa...");
                default -> System.out.println("Opción inválida. Intente de nuevo.");
            }
        }
    }

    private void buscarLibroPorTitulo() {
        System.out.println("Ingrese el título del libro:");
        var titulo = teclado.nextLine();

        String url = URL_BASE + URLEncoder.encode(titulo, StandardCharsets.UTF_8);
        var json = consumoApi.obtenerDatos(url);
        RespuestaAPI respuesta = conversor.obtenerDatos(json, RespuestaAPI.class);

//        if (respuesta.getTitulo() == null) {
//            System.out.println("No se encontraron libros con ese título.");
//            return;
//        }

        var libroData = respuesta.getResultados().getFirst();

        var datosAutor = libroData.getAutores().getFirst();

        Autor autor = autorRepository.findByNombreIgnoreCase(datosAutor.getNombre())
                .orElseGet(() -> autorRepository.save(new Autor(
                        datosAutor.getNombre(),
                        datosAutor.getAnioNacimiento(),
                        datosAutor.getAnioFallecimiento()
                )));

        Libro libro = new Libro(libroData);
        libro.setAutor(autor);
        repositorio.save(libro);

        System.out.println("Libro guardado: " + libro);
    }

    private void mostrarLibrosGuardados() {
        List<Libro> libros = repositorio.findAll();
        if (libros.isEmpty()) {
            System.out.println("No hay libros guardados.");
            return;
        }
        System.out.println("📚 Libros en la base de datos:");
        libros.forEach(System.out::println);
    }

    private void buscarPorIdioma() {
        System.out.println("Ingrese el código del idioma (ej: en, es, fr, pt):");
        String idioma = teclado.nextLine().trim();

        List<Libro> libros = repositorio.findByIdioma(idioma);
        if (libros.isEmpty()) {
            System.out.println("No se encontraron libros en ese idioma.");
            return;
        }
        System.out.println("📚 Libros en idioma '" + idioma + "':");
        libros.forEach(System.out::println);
    }

    private void mostrarAutores() {
        List<Autor> autores = autorRepository.findAll();

//        if (autores.isEmpty()) {
//            System.out.println("No hay autores guardados aún.");
//            return;
//        }

        System.out.println("Autores registrados:");
        autores.stream()
                .sorted(Comparator.comparing(Autor::getNombre))
                .forEach(System.out::println);
    }

    private void listarAutoresVivosEnAnio() {
        System.out.println("Ingrese el año a consultar:");
        int anio = Integer.parseInt(teclado.nextLine());

        List<Autor> autoresVivos = autorRepository.buscarAutoresVivosEnAnio(anio);

        if (autoresVivos.isEmpty()) {
            System.out.println("No se encontraron autores vivos en el año " + anio);
            return;
        }

        System.out.println("Autores vivos en " + anio + ":");
        autoresVivos.forEach(System.out::println);
    }

    private void contarLibrosPorIdioma(){
        System.out.println("Ingrese el idioma (ej: es, en): ");
        String idioma = teclado.nextLine();
        LibroService libroService = new LibroService(repositorio, autorRepository);
        long cantidad = libroService.contarLibrosPorIdioma(idioma);
        System.out.println("Cantidad de libros en " + idioma + ": " + cantidad);
    }
}
