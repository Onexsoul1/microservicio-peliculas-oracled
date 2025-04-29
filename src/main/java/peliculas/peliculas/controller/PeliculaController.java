package peliculas.peliculas.controller;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import peliculas.peliculas.model.Pelicula;
import peliculas.peliculas.service.PeliculaService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/peliculas")
public class PeliculaController {

    private final PeliculaService peliculaService;

    public PeliculaController(PeliculaService peliculaService) {
        this.peliculaService = peliculaService;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Pelicula>>> obtenerTodasLasPeliculas() {
        List<EntityModel<Pelicula>> peliculas = peliculaService.obtenerTodas().stream()
            .map(pelicula -> EntityModel.of(pelicula,
                    linkTo(methodOn(PeliculaController.class).obtenerPorId(pelicula.getId())).withSelfRel(),
                    linkTo(methodOn(PeliculaController.class).obtenerTodasLasPeliculas()).withRel("peliculas")))
            .collect(Collectors.toList());

        return ResponseEntity.ok(
                CollectionModel.of(peliculas, linkTo(methodOn(PeliculaController.class).obtenerTodasLasPeliculas()).withSelfRel())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Pelicula>> obtenerPorId(@PathVariable int id) {
        Optional<Pelicula> pelicula = peliculaService.obtenerPorId(id);
        return pelicula.map(p ->
                ResponseEntity.ok(
                        EntityModel.of(p,
                                linkTo(methodOn(PeliculaController.class).obtenerPorId(p.getId())).withSelfRel(),
                                linkTo(methodOn(PeliculaController.class).obtenerTodasLasPeliculas()).withRel("peliculas"))
                )
        ).orElseGet(() -> ResponseEntity.notFound().build());
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
