package org.example.ui;

import org.example.cinema.Cinema;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ReportManagerPanel {
    private Cinema cinema;

    public ReportManagerPanel(Cinema cinema) {
        this.cinema = cinema;
        createReportManagerFrame();
    }

    private void createReportManagerFrame() {
        JFrame frame = new JFrame("Wyświetl Raporty");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 500);
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
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Dodawanie przycisku wyświetlania raportu
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        JButton btnWyswietlRaport = new JButton("Wyświetl raport");
        btnWyswietlRaport.setFont(new Font("Arial", Font.BOLD, 20));
        btnWyswietlRaport.setBackground(new Color(240, 230, 140));
        panel.add(btnWyswietlRaport, gbc);

        // Dodawanie etykiety do wyświetlania wyniku raportu
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        JTextPane reportTextPane = new JTextPane();
        reportTextPane.setEditable(false);
        reportTextPane.setFont(new Font("Monospaced", Font.PLAIN, 16));
        reportTextPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        reportTextPane.setContentType("text/html");
        JScrollPane scrollPane = new JScrollPane(reportTextPane);
        scrollPane.setPreferredSize(new Dimension(550, 350));
        panel.add(scrollPane, gbc);

        // Dodawanie akcji do przycisku
        btnWyswietlRaport.addActionListener(e -> wyswietlRaport(reportTextPane));

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void wyswietlRaport(JTextPane reportTextPane) {
        int liczbaSprzedanychBiletow = cinema.liczbaSprzedanychBiletów();
        double dochodZeSprzedazy = cinema.dochodZeSprzedazy();

        String report = String.format(
                "<html>" +
                        "<h2 style='text-align: center;'>Raport</h2>" +
                        "<table style='width: 100%%; border-collapse: collapse;'>" +
                        "<tr><th style='border: 1px solid black; padding: 8px;'>Element</th><th style='border: 1px solid black; padding: 8px;'>Wartość</th></tr>" +
                        "<tr><td style='border: 1px solid black; padding: 8px;'>Liczba sprzedanych biletów</td><td style='border: 1px solid black; padding: 8px;'>%d</td></tr>" +
                        "<tr><td style='border: 1px solid black; padding: 8px;'>Dochód ze sprzedaży</td><td style='border: 1px solid black; padding: 8px;'>%.2f zł</td></tr>" +
                        "</table>" +
                        "</html>",
                liczbaSprzedanychBiletow, dochodZeSprzedazy
        );

        reportTextPane.setText(report);
    }
}
