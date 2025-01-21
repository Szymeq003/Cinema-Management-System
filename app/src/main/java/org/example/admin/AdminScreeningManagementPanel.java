package org.example.admin;

import org.example.cinema.Cinema;
import org.example.screening.Screening;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AdminScreeningManagementPanel {
    private Cinema cinema;

    public AdminScreeningManagementPanel(Cinema cinema) {
        this.cinema = cinema;
        createScreeningManagementFrame();
    }

    private void createScreeningManagementFrame() {
        JFrame frame = new JFrame("Wyświetl Seanse");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextArea screeningTextArea = new JTextArea(20, 50);
        screeningTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(screeningTextArea);

        StringBuilder message = new StringBuilder("Lista seansów:\n");
        for (Screening screening : cinema.getSeanse()) {
            message.append(" - ").append(screening.getFilm().getTytul())
                    .append(" o godz. ").append(screening.getGodzina())
                    .append(" w ").append(screening.getSala().getNumer()).append("\n");
        }
        screeningTextArea.setText(message.toString());

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(scrollPane, gbc);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
