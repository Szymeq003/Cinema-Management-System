package org.example.database;

import org.example.member.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<User> pobierzUzytkownikowZBazy() {
        List<User> uzytkownicy = new ArrayList<>();
        String sql = "SELECT * FROM użytkownicy";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                User user = new User(
                        rs.getString("imię"),
                        rs.getString("nazwisko"),
                        rs.getString("email"),
                        rs.getString("numer_telefonu")
                );
                uzytkownicy.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return uzytkownicy;
    }
}
