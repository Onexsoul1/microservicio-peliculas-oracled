package peliculas.peliculas.model;

import jakarta.persistence.*;

@Entity
@Table(name = "PELICULA") // Asegúrate de que el nombre coincida con la tabla real en Oracle
public class Pelicula {

    @Id
    private int id;

    private String titulo;
    private int año;
    private String director;
    private String genero;
    private String sinopsis;

    public Pelicula() {
        // Constructor por defecto requerido por JPA
    }

    public Pelicula(int id, String titulo, int año, String director, String genero, String sinopsis) {
        this.id = id;
        this.titulo = titulo;
        this.año = año;
        this.director = director;
        this.genero = genero;
        this.sinopsis = sinopsis;
    }

    // Getters y setters...
    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getAño() {
        return año;
    }

    public String getDirector() {
        return director;
    }

    public String getGenero() {
        return genero;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }
}
