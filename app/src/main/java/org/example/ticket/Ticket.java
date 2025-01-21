package org.example.ticket;

import org.example.screening.Screening;

public class Ticket {
    private Screening screening;
    private String miejsce;
    private double cena;
    private TicketType ticketType;
    private boolean sprzedany;

    public Ticket(Screening screening, String miejsce, double cena, TicketType ticketType, boolean sprzedany) {
        this.screening = screening;
        this.miejsce = miejsce;
        this.cena = cena;
        this.ticketType = ticketType;
        this.sprzedany = sprzedany;
    }

    public Screening getSeans() {
        return screening;
    }

    public String getMiejsce() {
        return miejsce;
    }

    public double getCena() {
        return cena;
    }

    public TicketType getRodzajBiletu() {
        return ticketType;
    }

    public boolean isSprzedany() {
        return sprzedany;
    }

    public void setSprzedany(boolean sprzedany) {
        this.sprzedany = sprzedany;
    }
}
