package org.example.Ticketui;

import org.example.Ticketui.TicketDetailsPanel;
import org.example.cinema.Cinema;
import org.example.database.TicketDataBaseManager;
import org.example.film.Film;
import org.example.screening.Screening;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class TicketManagerPanel {
    private Cinema cinema;
    private TicketDataBaseManager dbManager;

    public TicketManagerPanel(Cinema cinema) {
        this.cinema = cinema;
        this.dbManager = new TicketDataBaseManager();
        createTicketManagerFrame();
    }

    private void createTicketManagerFrame() {
        JFrame frame = new JFrame("Zarządzaj Biletami");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600); // Powiększenie okna, aby lepiej dopasować treść
        frame.setLocationRelativeTo(null);

        try {
            ImageIcon icon = new ImageIcon(getClass().getResource("/icon.jpg"));
            frame.setIconImage(icon.getImage());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Nie udało się załadować ikony.");
        }

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font customFont = new Font("Arial", Font.BOLD, 20); // Ustalanie czcionki

        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel tytulFilmuLabel = new JLabel("Tytuł filmu:");
        tytulFilmuLabel.setFont(customFont);
        panel.add(tytulFilmuLabel, gbc);

        gbc.gridx = 1;
        List<Film> filmy = dbManager.pobierzFilmyZBazy();
        JComboBox<String> filmComboBox = new JComboBox<>();
        filmComboBox.addItem("---");
        for (Film film : filmy) {
            filmComboBox.addItem(film.getTytul());
        }
        filmComboBox.setSelectedIndex(0);
        filmComboBox.setPreferredSize(new Dimension(375, 40));
        filmComboBox.setFont(customFont);
        panel.add(filmComboBox, gbc);

        // Dodawanie rozwijanego menu z dostępnymi godzinami filmów
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel godzinaSeansuLabel = new JLabel("Godzina seansu:");
        godzinaSeansuLabel.setFont(customFont); // Ustawienie czcionki dla etykiety
        panel.add(godzinaSeansuLabel, gbc);

        gbc.gridx = 1;
        JComboBox<String> godzinaComboBox = new JComboBox<>();
        godzinaComboBox.setPreferredSize(new Dimension(375, 40)); // Ustawienie preferowanej wielkości rozwijanego menu
        godzinaComboBox.setFont(customFont); // Ustawienie czcionki dla JComboBox
        panel.add(godzinaComboBox, gbc);

        // Dodawanie rozwijanego menu dla sali
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel salaLabel = new JLabel("Sala:");
        salaLabel.setFont(customFont); // Ustawienie czcionki dla etykiety
        panel.add(salaLabel, gbc);

        gbc.gridx = 1;
        JComboBox<String> salaComboBox = new JComboBox<>();
        salaComboBox.setPreferredSize(new Dimension(375, 40)); // Ustawienie preferowanej wielkości rozwijanego menu
        salaComboBox.setFont(customFont); // Ustawienie czcionki dla JComboBox
        panel.add(salaComboBox, gbc);

        // Aktualizacja dostępnych godzin seansów po wybraniu filmu
        filmComboBox.addActionListener(e -> {
            String wybranyFilm = (String) filmComboBox.getSelectedItem();
            List<Screening> seanse = dbManager.pobierzSeanseDlaFilmu(wybranyFilm);
            godzinaComboBox.removeAllItems();
            salaComboBox.removeAllItems();
            seanse.forEach(seans -> {
                godzinaComboBox.addItem(seans.getGodzina());
                salaComboBox.addItem(seans.getSala().getNumer());
            });
        });

        // Dodawanie panelu szczegółów biletu
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        TicketDetailsPanel ticketDetailsPanel = new TicketDetailsPanel(dbManager, frame, filmComboBox, godzinaComboBox, salaComboBox);
        panel.add(ticketDetailsPanel, gbc);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
