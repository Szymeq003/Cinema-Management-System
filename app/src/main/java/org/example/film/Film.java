package org.example.film;

public class Film {
    private String tytul;
    private String gatunek;
    private int czasTrwania;
    private String rezyser;
    private double ocena;

    public Film(String tytul, String gatunek, int czasTrwania, String rezyser, double ocena) {
        this.tytul = tytul;
        this.gatunek = gatunek;
        this.czasTrwania = czasTrwania;
        this.rezyser = rezyser;
        this.ocena = ocena;
    }

    public String getTytul() {
        return tytul;
    }

    public String getGatunek() {
        return gatunek;
    }

    public int getCzasTrwania() {
        return czasTrwania;
    }

    public String getRezyser() {
        return rezyser;
    }

    public double getOcena() {
        return ocena;
    }

    public void setOcena(double ocena) {
        this.ocena = ocena;
    }

    @Override
    public String toString() {
        return "Film{" +
                "tytul='" + tytul + '\'' +
                ", gatunek='" + gatunek + '\'' +
                ", czasTrwania=" + czasTrwania +
                ", rezyser='" + rezyser + '\'' +
                ", ocena=" + ocena +
                '}';
    }
}
