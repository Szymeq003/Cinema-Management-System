package org.example.admin;

import org.example.cinema.Cinema;
import org.example.screening.Screening;
import org.example.member.Admin;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AdminDeleteScreeningPanel {
    private Cinema cinema;
    private Admin admin;

    public AdminDeleteScreeningPanel(Cinema cinema) {
        this.cinema = cinema;
        this.admin = new Admin(cinema);
        createDeleteScreeningFrame();
    }

    private void createDeleteScreeningFrame() {
        JFrame frame = new JFrame("Usuń Seans");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Dodawanie pola do wpisania tytułu filmu
        JTextField tytulField = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Tytuł filmu:"), gbc);
        gbc.gridx = 1;
        panel.add(tytulField, gbc);

        // Dodawanie pola do wpisania godziny seansu
        JTextField godzinaField = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Godzina seansu:"), gbc);
        gbc.gridx = 1;
        panel.add(godzinaField, gbc);

        // Dodawanie przycisku "Usuń seans"
        JButton btnUsunSeans = new JButton("Usuń seans");
        btnUsunSeans.addActionListener(e -> {
            String tytul = tytulField.getText();
            String godzina = godzinaField.getText();
            Screening screening = cinema.znajdzSeans(tytul, godzina);
            if (screening != null) {
                cinema.usunSeans(screening);
                admin.usunSeans(screening);
                JOptionPane.showMessageDialog(frame, "Seans został usunięty.");
            } else {
                JOptionPane.showMessageDialog(frame, "Seans nie znaleziony.");
            }
        });

        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(btnUsunSeans, gbc);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
