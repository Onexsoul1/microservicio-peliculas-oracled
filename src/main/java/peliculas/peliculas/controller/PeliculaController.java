package peliculas.peliculas.controller;

import org.springframework.web.bind.annotation.*;
import peliculas.peliculas.model.Pelicula;
import peliculas.peliculas.service.PeliculaService;

import java.util.List;

@RestController
@RequestMapping("/peliculas")
public class PeliculaController {

    private final PeliculaService peliculaService;

    public PeliculaController(PeliculaService peliculaService) {
        this.peliculaService = peliculaService;
    }

    @GetMapping
    public List<Pelicula> obtenerTodasLasPeliculas() {
        return peliculaService.obtenerTodas();
    }

    @GetMapping("/{id}")
    public Pelicula obtenerPorId(@PathVariable int id) {
        return peliculaService.obtenerPorId(id);
    }
}
