package org.example.admin;

import org.example.cinema.Cinema;
import org.example.database.FilmDatabaseManager;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AdminPanel {
    private static final String ADMIN_PASSWORD = "";
    private Cinema cinema;
    private FilmDatabaseManager filmDatabaseManager;
    private DatabaseCleaner dbCleaner;

    public AdminPanel(Cinema cinema) {
        this.cinema = cinema;
        this.filmDatabaseManager = new FilmDatabaseManager();
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:kinobaza.db");
            this.dbCleaner = new DatabaseCleaner(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        createAdminPanelFrame();
    }

    private void createAdminPanelFrame() {
        JPasswordField hasloField = new JPasswordField();
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Podaj hasło:"));
        panel.add(hasloField);

        UIManager.put("OptionPane.cancelButtonText", "Anuluj");
        int result = JOptionPane.showConfirmDialog(null, panel, "Panel Admin", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String haslo = new String(hasloField.getPassword());
            if (ADMIN_PASSWORD.equals(haslo)) {
                adminMenu();
            } else {
                JOptionPane.showMessageDialog(null, "Nieprawidłowe hasło.");
            }
        }
    }

    private void adminMenu() {
        JFrame adminFrame = new JFrame("Panel Admina");
        adminFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenHeight = screenSize.height;

        adminFrame.setSize(600, screenHeight);
        adminFrame.setLocationRelativeTo(null);
        adminFrame.setLayout(new GridLayout(0, 1, 10, 10));

        Font customFont = new Font("Arial", Font.BOLD, 20);

        JButton btnWyswietlFilmy = new JButton("Wyświetl filmy");
        btnWyswietlFilmy.setFont(customFont);
        JButton btnWyswietlSeanse = new JButton("Wyświetl seanse");
        btnWyswietlSeanse.setFont(customFont);
        JButton btnWyswietlUzytkownikow = new JButton("Wyświetl użytkowników");
        btnWyswietlUzytkownikow.setFont(customFont);
        JButton btnUsunFilm = new JButton("Usuń film");
        btnUsunFilm.setFont(customFont);
        JButton btnUsunUzytkownika = new JButton("Usuń użytkownika");
        btnUsunUzytkownika.setFont(customFont);
        JButton btnWyswietlDane = new JButton("Wyświetl dane z bazy");
        btnWyswietlDane.setFont(customFont);
        JButton btnUsunSeans = new JButton("Usuń seans");
        btnUsunSeans.setFont(customFont);
        JButton btnUsunDane = new JButton("Usuń dane z bazy");
        btnUsunDane.setFont(customFont);
        JButton btnWyjscie = new JButton("Wyjście");
        btnWyjscie.setFont(customFont);

        btnWyswietlFilmy.addActionListener(e -> new AdminFilmManagementPanel(filmDatabaseManager));
        btnWyswietlSeanse.addActionListener(e -> new AdminScreeningManagementPanel(cinema));
        btnWyswietlUzytkownikow.addActionListener(e -> new AdminUserManagementPanel(cinema));
        btnUsunFilm.addActionListener(e -> new AdminDeleteFilmPanel(cinema));
        btnUsunUzytkownika.addActionListener(e -> new AdminUserManagementPanel(cinema, true));
        btnWyswietlDane.addActionListener(e -> new AdminReportPanel(cinema));
        btnUsunSeans.addActionListener(e -> new AdminDeleteScreeningPanel(cinema));
        btnUsunDane.addActionListener(e -> dbCleaner.clearDatabase());
        btnWyjscie.addActionListener(e -> adminFrame.dispose());

        adminFrame.add(btnWyswietlFilmy);
        adminFrame.add(btnWyswietlSeanse);
        adminFrame.add(btnWyswietlUzytkownikow);
        adminFrame.add(btnUsunFilm);
        adminFrame.add(btnUsunSeans);
        adminFrame.add(btnUsunUzytkownika);
        adminFrame.add(btnWyswietlDane);
        adminFrame.add(btnUsunDane);
        adminFrame.add(btnWyjscie);

        adminFrame.setVisible(true);
    }
}
