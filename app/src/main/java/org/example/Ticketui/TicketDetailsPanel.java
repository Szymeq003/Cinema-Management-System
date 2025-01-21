package org.example.Ticketui;

import org.example.database.TicketDataBaseManager;
import org.example.screening.Screening;
import org.example.ticket.Ticket;
import org.example.ticket.TicketType;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TicketDetailsPanel extends JPanel {
    private JComboBox<String> rzadComboBox;
    private JTextField miejsceField;
    private JComboBox<String> rodzajBiletuComboBox;
    private JTextField cenaField;
    private JButton btnSprzedajBilet;

    public TicketDetailsPanel(TicketDataBaseManager dbManager, JFrame frame, JComboBox<String> filmComboBox, JComboBox<String> godzinaComboBox, JComboBox<String> salaComboBox) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font customFont = new Font("Arial", Font.BOLD, 20); // Ustalanie czcionki

        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel rzadLabel = new JLabel("Rząd:");
        rzadLabel.setFont(customFont); // Ustawienie czcionki dla etykiety
        add(rzadLabel, gbc);

        gbc.gridx = 1;
        rzadComboBox = new JComboBox<>(new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"});
        rzadComboBox.setPreferredSize(new Dimension(200, 25));
        rzadComboBox.setFont(customFont); // Ustawienie czcionki dla JComboBox
        add(rzadComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel miejsceLabel = new JLabel("Miejsce:");
        miejsceLabel.setFont(customFont); // Ustawienie czcionki dla etykiety
        add(miejsceLabel, gbc);

        gbc.gridx = 1;
        miejsceField = new JTextField(20);
        miejsceField.setPreferredSize(new Dimension(200, 25));
        miejsceField.setFont(customFont); // Ustawienie czcionki dla JTextField
        add(miejsceField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel rodzajBiletuLabel = new JLabel("Rodzaj biletu:");
        rodzajBiletuLabel.setFont(customFont); // Ustawienie czcionki dla etykiety
        add(rodzajBiletuLabel, gbc);

        gbc.gridx = 1;
        rodzajBiletuComboBox = new JComboBox<>(new String[]{"---", "NORMALNY", "ULGOWY", "STUDENCKI", "SENIOR"});
        rodzajBiletuComboBox.setPreferredSize(new Dimension(200, 25));
        rodzajBiletuComboBox.setFont(customFont); // Ustawienie czcionki dla JComboBox
        add(rodzajBiletuComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel cenaLabel = new JLabel("Cena:");
        cenaLabel.setFont(customFont); // Ustawienie czcionki dla etykiety
        add(cenaLabel, gbc);

        gbc.gridx = 1;
        cenaField = new JTextField(20);
        cenaField.setEditable(false);
        cenaField.setPreferredSize(new Dimension(200, 25));
        cenaField.setFont(customFont); // Ustawienie czcionki dla JTextField
        add(cenaField, gbc);

        // Aktualizacja ceny po wyborze rodzaju biletu
        rodzajBiletuComboBox.addActionListener(e -> updatePrice());

        // Dodawanie przycisku "Sprzedaj bilet"
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        btnSprzedajBilet = new JButton("Sprzedaj bilet");
        btnSprzedajBilet.setFont(customFont); // Ustawienie czcionki dla przycisku
        btnSprzedajBilet.setBackground(new Color(255, 228, 181));
        btnSprzedajBilet.addActionListener(e -> sellTicket(dbManager, frame, filmComboBox, godzinaComboBox, salaComboBox));
        add(btnSprzedajBilet, gbc);
    }

    private void updatePrice() {
        String rodzaj = (String) rodzajBiletuComboBox.getSelectedItem();
        double cena = 0.00; // Cena domyślna
        switch (rodzaj) {
            case "NORMALNY":
                cena = 30.00; // Cena bazowa
                break;
            case "ULGOWY":
                cena = 30.00 * 0.7; // 30% zniżki
                break;
            case "STUDENCKI":
                cena = 30.00 * 0.5; // 50% zniżki
                break;
            case "SENIOR":
                cena = 30.00 * 0.2; // 80% zniżki
                break;
        }
        cenaField.setText(String.valueOf(cena));
    }

    private void sellTicket(TicketDataBaseManager dbManager, JFrame frame, JComboBox<String> filmComboBox, JComboBox<String> godzinaComboBox, JComboBox<String> salaComboBox) {
        String tytul = (String) filmComboBox.getSelectedItem();
        List<Screening> seanse = dbManager.pobierzSeanseDlaFilmu(tytul);
        String godzina = (String) godzinaComboBox.getSelectedItem();
        Screening screening = seanse.stream().filter(s -> s.getGodzina().equals(godzina)).findFirst().orElse(null);
        if (screening == null) {
            JOptionPane.showMessageDialog(frame, "Seans nie znaleziony.");
            return;
        }
        String rzad = (String) rzadComboBox.getSelectedItem();
        String miejsce = miejsceField.getText();
        double cena = Double.parseDouble(cenaField.getText());
        TicketType ticketType = TicketType.valueOf((String) rodzajBiletuComboBox.getSelectedItem());

        Ticket ticket = new Ticket(screening, rzad + " " + miejsce, cena, ticketType, true);
        dbManager.sprzedajBiletDoBazy(ticket);

        JOptionPane.showMessageDialog(frame, "Bilet sprzedany!");

        // Czyszczenie pól po sprzedaży biletu
        filmComboBox.setSelectedIndex(0);
        godzinaComboBox.removeAllItems();
        salaComboBox.removeAllItems();
        rzadComboBox.setSelectedIndex(0);
        miejsceField.setText("");
        rodzajBiletuComboBox.setSelectedIndex(0);
        cenaField.setText("");
    }
}
