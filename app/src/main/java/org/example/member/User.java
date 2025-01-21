package org.example.member;

public class User {
    private String imie;
    private String nazwisko;
    private String email;
    private String numerTelefonu;

    public User(String imie, String nazwisko, String email, String numerTelefonu) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.email = email;
        this.numerTelefonu = numerTelefonu;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public String getEmail() {
        return email;
    }

    public String getNumerTelefonu() {
        return numerTelefonu;
    }

}
