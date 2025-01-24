package org.example.screening;

import org.example.film.Film;

public class Screening {
    private Film film;
    private String godzina;
    private Hall sala;

    public Screening(Film film, String godzina, Hall sala) {
        this.film = film;
        this.godzina = godzina;
        this.sala = sala;
    }

    public Film getFilm() {
        return film;
    }

    public String getGodzina() {
        return godzina;
    }

    public Hall getSala() {
        return sala;
    }

    @Override
    public String toString() {
        return film.getTytul() + " o godz. " + godzina + " w sali " + sala.getNumer();
    }
}
