package peliculas.peliculas.service;

import org.springframework.stereotype.Service;
import peliculas.peliculas.model.Pelicula;
import peliculas.peliculas.repository.PeliculaRepository;

import java.util.List;

@Service
public class PeliculaService {

    private final PeliculaRepository peliculaRepository;

    public PeliculaService(PeliculaRepository peliculaRepository) {
        this.peliculaRepository = peliculaRepository;
    }

    public List<Pelicula> obtenerTodas() {
        return peliculaRepository.findAll();
    }

    public Pelicula obtenerPorId(int id) {
        return peliculaRepository.findById(id).orElse(null);
    }
}
