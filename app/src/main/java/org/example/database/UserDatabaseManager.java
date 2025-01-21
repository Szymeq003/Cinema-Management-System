package org.example.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.example.member.User;

public class UserDatabaseManager extends DatabaseManager {

    public void dodajUzytkownikaDoBazy(User user) {
        String sql = "INSERT INTO użytkownicy (imie, nazwisko, email, numer_telefonu) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getImie());
            pstmt.setString(2, user.getNazwisko());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getNumerTelefonu());

            pstmt.executeUpdate();
            System.out.println("Użytkownik został zapisany do bazy danych.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
