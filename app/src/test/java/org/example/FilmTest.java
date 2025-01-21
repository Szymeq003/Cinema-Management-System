package org.example;

import org.example.film.Film;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FilmTest {

    @Test
    public void testFilmConstructor() {
        Film film = new Film("Incepcja", "Sci-Fi", 148, "Christopher Nolan", 8.8);
        assertNotNull(film);
        assertEquals("Incepcja", film.getTytul());
        assertEquals("Sci-Fi", film.getGatunek());
        assertEquals(148, film.getCzasTrwania());
        assertEquals("Christopher Nolan", film.getRezyser());
        assertEquals(8.8, film.getOcena());
        System.out.println("testFilmConstructor passed: " + film);
    }

    @Test
    public void testSetAndGetOcena() {
        Film film = new Film("Incepcja", "Sci-Fi", 148, "Christopher Nolan", 8.8);
        film.setOcena(9.0);
        assertEquals(9.0, film.getOcena());
        System.out.println("testSetAndGetOcena passed: " + film);
    }

    @Test
    public void testToString() {
        Film film = new Film("Incepcja", "Sci-Fi", 148, "Christopher Nolan", 8.8);
        String expected = "Film{tytul='Incepcja', gatunek='Sci-Fi', czasTrwania=148, rezyser='Christopher Nolan', ocena=8.8}";
        assertEquals(expected, film.toString());
        System.out.println("testToString passed: " + film);
    }
}
