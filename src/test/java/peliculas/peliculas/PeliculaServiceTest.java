package peliculas.peliculas;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import peliculas.peliculas.model.Pelicula;
import peliculas.peliculas.repository.PeliculaRepository;
import peliculas.peliculas.service.PeliculaService;

import java.util.Optional;

public class PeliculaServiceTest {

    private PeliculaService peliculaService;
    private PeliculaRepository peliculaRepository;

    @BeforeEach
    public void setUp() {
        peliculaRepository = mock(PeliculaRepository.class);
        peliculaService = new PeliculaService(peliculaRepository);
    }

    @Test
    public void testObtenerPorIdExistente() {
        Pelicula pelicula = new Pelicula();
        pelicula.setId(1);
        pelicula.setTitulo("Inception");

        when(peliculaRepository.findById(1)).thenReturn(Optional.of(pelicula));

        Optional<Pelicula> resultado = peliculaService.obtenerPorId(1);

        assertTrue(resultado.isPresent());
        assertEquals("Inception", resultado.get().getTitulo());
    }
}
