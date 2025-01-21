package org.example.Ticketui;

import org.example.database.TicketDataBaseManager;
import org.example.screening.Screening;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ScreeningSelectionPanel extends JPanel {
    private JComboBox<String> godzinaComboBox;
    private JComboBox<String> salaComboBox;

    public ScreeningSelectionPanel(TicketDataBaseManager dbManager) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Godzina seansu:"), gbc);

        gbc.gridx = 1;
        godzinaComboBox = new JComboBox<>();
        godzinaComboBox.setPreferredSize(new Dimension(200, 25));
        add(godzinaComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Sala:"), gbc);

        gbc.gridx = 1;
        salaComboBox = new JComboBox<>();
        salaComboBox.setPreferredSize(new Dimension(200, 25));
        add(salaComboBox, gbc);
    }

    public void updateScreenings(TicketDataBaseManager dbManager, String selectedFilm) {
        List<Screening> seanse = dbManager.pobierzSeanseDlaFilmu(selectedFilm);
        godzinaComboBox.removeAllItems();
        salaComboBox.removeAllItems();
        seanse.forEach(seans -> {
            godzinaComboBox.addItem(seans.getGodzina());
            salaComboBox.addItem(seans.getSala().getNumer());
        });
    }

    public String getSelectedScreeningTime() {
        return (String) godzinaComboBox.getSelectedItem();
    }

    public String getSelectedScreeningHall() {
        return (String) salaComboBox.getSelectedItem();
    }
}
