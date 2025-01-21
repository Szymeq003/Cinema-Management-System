package org.example.screening;

import org.example.film.Film;
import java.util.List;

public class Screening {
    private Film film;
    private String godzina;
    private Hall hall;

    public Screening(Film film, String godzina, Hall hall) {
        this.film = film;
        this.godzina = godzina;
        this.hall = hall;
    }

    public Film getFilm() {
        return film;
    }

    public String getGodzina() {
        return godzina;
    }

    public Hall getSala() {
        return hall;
    }
}
