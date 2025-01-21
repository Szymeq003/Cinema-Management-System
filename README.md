# Cinema-Management-System

System Zarządzania Kinem to kompleksowa aplikacja zaprojektowana do zarządzania filmami, seansami, biletami oraz użytkownikami w kinie. Aplikacja umożliwia administratorom zarządzanie bazą danych filmów, seansów oraz użytkowników, a także umożliwia sprzedaż biletów na seanse.

## Funkcje

- Dodawanie, usuwanie i edytowanie filmów w bazie danych
- Dodawanie, usuwanie i edytowanie seansów w bazie danych
- Dodawanie, usuwanie i edytowanie użytkowników w bazie danych
- Sprzedaż biletów na seanse
- Wyświetlanie danych o sprzedanych biletach i generowanie raportów
- Czyszczenie bazy danych

## Technologie

- Java
- SQLite
- JUnit

## Struktura Projektu

- `org.example.admin` - Zawiera klasy administracyjne, takie jak `AdminPanel` i `DatabaseCleaner`
- `org.example.cinema` - Zawiera główną klasę aplikacji `Cinema`
- `org.example.database` - Zawiera klasy do zarządzania bazą danych, takie jak `DatabaseManager` i `FilmDatabaseManager`
- `org.example.film` - Zawiera klasę filmów, takie jak `Film`
- `org.example.screening` - Zawiera klasę seansów, takie jak `Screening`
- `org.example.ticket` - Zawiera klasy biletów, takie jak `Ticket`
- `org.example.ui` - Zawiera klasy interfejsu użytkownika, takie jak `AppGUI`

## Wymagania

- Java 23 lub nowsza
- SQLite JDBC Driver
- Gradle 8.10.2

## Instalacja

1. Sklonuj repozytorium:
   ```sh
   git clone https://github.com/Szymeq003/Cinema-Management-System.git
