package peliculas.peliculas.service;

import org.springframework.stereotype.Service;
import peliculas.peliculas.model.Pelicula;
import peliculas.peliculas.repository.PeliculaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PeliculaService {

    private final PeliculaRepository peliculaRepository;

    public PeliculaService(PeliculaRepository peliculaRepository) {
        this.peliculaRepository = peliculaRepository;
    }

    public List<Pelicula> obtenerTodas() {
        return peliculaRepository.findAll();
    }

    public Pelicula guardar(Pelicula pelicula) {
        return peliculaRepository.save(pelicula);
    }
    public Optional<Pelicula> obtenerPorId(int id) {
        return peliculaRepository.findById((int) id);
    }
    public Optional<Pelicula> actualizar(int id, Pelicula detalles) {
        Optional<Pelicula> optional = peliculaRepository.findById((int) id);
        if (optional.isPresent()) {
            Pelicula pelicula = optional.get();
            pelicula.setTitulo(detalles.getTitulo());
            pelicula.setAño(detalles.getAño());
            pelicula.setDirector(detalles.getDirector());
            pelicula.setGenero(detalles.getGenero());
            pelicula.setSinopsis(detalles.getSinopsis());
            return Optional.of(peliculaRepository.save(pelicula));
        } else {
            return Optional.empty();
        }
    }
}
