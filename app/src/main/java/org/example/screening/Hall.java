package org.example.screening;

public class Hall {
    private String numer;
    private int pojemnosc;

    public Hall(String numer, int pojemnosc) {
        this.numer = numer;
        this.pojemnosc = pojemnosc;
    }

    public String getNumer() {
        return numer;
    }

    public int getPojemnosc() {
        return pojemnosc;
    }
}
