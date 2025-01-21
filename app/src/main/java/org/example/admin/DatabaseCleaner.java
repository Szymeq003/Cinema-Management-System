package org.example.admin;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseCleaner {
    private Connection connection;

    public DatabaseCleaner(Connection connection) {
        this.connection = connection;
    }

    public void clearDatabase() {
        Object[] options = {"Tak", "Nie"};
        int response = JOptionPane.showOptionDialog(null, "Czy na pewno chcesz usunąć wszystkie dane z bazy?", "Potwierdzenie",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (response == JOptionPane.YES_OPTION) {
            try {
                String[] tables = {"bilety", "seanse", "filmy", "użytkownicy"};

                for (String table : tables) {
                    String sql = "DELETE FROM " + table;
                    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                        pstmt.executeUpdate();
                    }
                }

                JOptionPane.showMessageDialog(null, "Baza danych została wyczyszczona.");
                System.out.println("Baza danych została wyczyszczona.");
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Wystąpił błąd podczas czyszczenia bazy danych.");
            }
        }
    }
}
