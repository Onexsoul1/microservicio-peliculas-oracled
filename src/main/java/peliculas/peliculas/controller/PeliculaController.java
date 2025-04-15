package peliculas.peliculas.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import peliculas.peliculas.model.Pelicula;
import peliculas.peliculas.service.PeliculaService;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<Pelicula> obtenerPorId(@PathVariable int id) {
        Optional<Pelicula> pelicula = peliculaService.obtenerPorId(id);
        return pelicula.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Pelicula> crearPelicula(@RequestBody Pelicula pelicula) {
        Pelicula nueva = peliculaService.guardar(pelicula);
        return ResponseEntity.ok(nueva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pelicula> actualizarPelicula(@PathVariable int id, @RequestBody Pelicula peliculaDetalles) {
        Optional<Pelicula> actualizada = peliculaService.actualizar(id, peliculaDetalles);
        return actualizada.map(ResponseEntity::ok)
                          .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
