package org.example.admin;

import org.example.cinema.Cinema;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class AdminReportPanel {
    private Cinema cinema;

    public AdminReportPanel(Cinema cinema) {
        this.cinema = cinema;
        createReportPanelFrame();
    }

    private void createReportPanelFrame() {
        JFrame frame = new JFrame("Wyświetl dane z bazy");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JTextArea dataTextArea = new JTextArea(30, 70);
        dataTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(dataTextArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton btnWyswietlDane = new JButton("Wyświetl dane");
        btnWyswietlDane.setFont(new Font("Arial", Font.BOLD, 20));
        btnWyswietlDane.addActionListener(e -> wyswietlDane(dataTextArea));
        panel.add(btnWyswietlDane, BorderLayout.NORTH);

        frame.add(panel);
        frame.setVisible(true);
    }

    private void wyswietlDane(JTextArea dataTextArea) {
        StringBuilder data = new StringBuilder();
        List<String> tables = List.of("filmy", "seanse", "bilety", "użytkownicy");

        for (String table : tables) {
            data.append("Tabela: ").append(table).append("\n");
            data.append("--------------------------\n");

            try (Connection conn = cinema.getDatabaseManager().getConnection();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT * FROM " + table)) {

                int columnCount = rs.getMetaData().getColumnCount();

                while (rs.next()) {
                    for (int i = 1; i <= columnCount; i++) {
                        data.append(rs.getMetaData().getColumnName(i)).append(": ").append(rs.getString(i)).append(" | ");
                    }
                    data.append("\n");
                }
            } catch (Exception e) {
                data.append("Błąd: ").append(e.getMessage()).append("\n");
            }
            data.append("\n");
        }

        dataTextArea.setText(data.toString());
    }
}
