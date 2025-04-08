package com.peliculas.peliculas.controller;

import org.springframework.web.bind.annotation.*;
import com.peliculas.peliculas.model.Pelicula;
import com.peliculas.peliculas.repository.PeliculaRepository;

import java.util.List;

@RestController
@RequestMapping("/peliculas")
public class PeliculaController {

    private final PeliculaRepository repo;

    public PeliculaController(PeliculaRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Pelicula> getPeliculas() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Pelicula getPeliculaPorId(@PathVariable Long id) {
        return repo.findById(id)
                   .orElseThrow(() -> new RuntimeException("Película no encontrada con ID: " + id));
    }
}
