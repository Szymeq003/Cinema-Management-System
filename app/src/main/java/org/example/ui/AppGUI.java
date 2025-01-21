package org.example.ui;

import org.example.Ticketui.TicketManagerPanel;
import org.example.admin.AdminPanel;
import org.example.cinema.Cinema;

import javax.swing.*;
import java.awt.*;

public class AppGUI {
    private Cinema cinema;

    public AppGUI(Cinema cinema) {
        this.cinema = cinema;
        createMainFrame();
    }

    private void createMainFrame() {
        JFrame frame = new JFrame("System Zarządzania Kinem");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        try {
            ImageIcon icon = new ImageIcon(getClass().getResource("/icon.jpg"));
            frame.setIconImage(icon.getImage());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Nie udało się załadować ikony.");
        }
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));
        panel.setBackground(new Color(173, 216, 230));

        Font customFont = new Font("Arial", Font.BOLD, 50);

        JButton btnDodajFilm = new JButton("Dodaj film");
        btnDodajFilm.setFont(customFont);
        btnDodajFilm.setBackground(new Color(255, 182, 193));
        btnDodajFilm.setHorizontalTextPosition(SwingConstants.CENTER);
        btnDodajFilm.addActionListener(e -> new FilmManagerPanel(cinema));

        JButton btnDodajSeans = new JButton("Dodaj seans");
        btnDodajSeans.setFont(customFont);
        btnDodajSeans.setBackground(new Color(144, 238, 144));
        btnDodajSeans.setHorizontalTextPosition(SwingConstants.CENTER);
        btnDodajSeans.addActionListener(e -> new ScreeningManagerPanel(cinema));

        JButton btnDodajUzytkownika = new JButton("Dodaj klienta");
        btnDodajUzytkownika.setFont(customFont);
        btnDodajUzytkownika.setBackground(new Color(173, 216, 230));
        btnDodajUzytkownika.setHorizontalTextPosition(SwingConstants.CENTER);
        btnDodajUzytkownika.addActionListener(e -> new UserManagerPanel(cinema));

        JButton btnSprzedajBilet = new JButton("Sprzedaj bilet");
        btnSprzedajBilet.setFont(customFont);
        btnSprzedajBilet.setBackground(new Color(255, 228, 181));
        btnSprzedajBilet.setHorizontalTextPosition(SwingConstants.CENTER);
        btnSprzedajBilet.addActionListener(e -> new TicketManagerPanel(cinema));

        JButton btnWyswietlRaport = new JButton("Wyświetl raport");
        btnWyswietlRaport.setFont(customFont);
        btnWyswietlRaport.setBackground(new Color(240, 230, 140));
        btnWyswietlRaport.setHorizontalTextPosition(SwingConstants.CENTER);
        btnWyswietlRaport.addActionListener(e -> new ReportManagerPanel(cinema));

        JButton btnPanelAdmin = new JButton("Panel admin");
        btnPanelAdmin.setFont(customFont);
        btnPanelAdmin.setBackground(new Color(175, 238, 238));
        btnPanelAdmin.setHorizontalTextPosition(SwingConstants.CENTER);
        btnPanelAdmin.addActionListener(e -> new AdminPanel(cinema));

        panel.add(btnDodajFilm);
        panel.add(btnDodajSeans);
        panel.add(btnDodajUzytkownika);
        panel.add(btnSprzedajBilet);
        panel.add(btnWyswietlRaport);
        panel.add(btnPanelAdmin);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
