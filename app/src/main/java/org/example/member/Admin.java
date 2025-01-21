package org.example.member;

import org.example.cinema.Cinema;
import org.example.film.Film;
import org.example.screening.Screening;

public class Admin {
    private Cinema cinema;

    public Admin(Cinema cinema) {
        this.cinema = cinema;
    }

    public void usunFilm(String tytul) {
        Film film = cinema.znajdzFilm(tytul);
        if (film != null) {
            cinema.getFilmy().remove(film);
            System.out.println("Film " + tytul + " został usunięty.");
        } else {
            System.out.println("Film " + tytul + " nie został znaleziony.");
        }
    }

    public void usunUzytkownika(String email) {
        User user = cinema.znajdzUzytkownika(email);
        if (user != null) {
            cinema.getUzytkownicy().remove(user);
            System.out.println("Użytkownik " + email + " został usunięty.");
        } else {
            System.out.println("Użytkownik " + email + " nie został znaleziony.");
        }
    }

    public void usunSeans(Screening screening) {
        cinema.usunSeans(screening);
        System.out.println("Seans został usunięty przez administratora.");
    }
}
