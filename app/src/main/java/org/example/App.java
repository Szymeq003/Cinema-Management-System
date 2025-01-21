package org.example;

import org.example.database.DatabaseManager;
import org.example.cinema.Cinema;
import org.example.ui.AppGUI;

public class App {
    public static void main(String[] args) {
        DatabaseManager dbManager = new DatabaseManager();
        dbManager.inicjalizujBazeDanych();

        Cinema cinema = new Cinema(dbManager);

        javax.swing.SwingUtilities.invokeLater(() -> new AppGUI(cinema));
    }
}
