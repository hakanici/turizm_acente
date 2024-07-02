package com.hotelmanagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;
//SeasonPanel'in oluşturulduğu kısım.
public class SeasonPanel extends JPanel {
    private JTable seasonTable;
    private JButton addSeasonButton, editSeasonButton;
    private SeasonDAO seasonDAO;

    public SeasonPanel() {
        seasonDAO = new SeasonDAO();
        setLayout(new BorderLayout());

        seasonTable = new JTable();
        loadSeasons();

        addSeasonButton = new JButton("Add Season");
        editSeasonButton = new JButton("Edit Season");


        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addSeasonButton);
        buttonPanel.add(editSeasonButton);


        add(new JScrollPane(seasonTable), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        addSeasonButton.addActionListener(e -> showSeasonForm(null));
        editSeasonButton.addActionListener(e -> showSeasonForm(getSelectedSeason()));

    }
    //Bütün Seasonları alıp listeleyen metot.
    private void loadSeasons() {
        try {
            List<Season> seasons = seasonDAO.getAllSeasons();
            String[] columnNames = {"ID", "Hotel ID", "Start Date", "End Date"};
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);

            for (Season season : seasons) {
                Object[] row = {
                        season.getId(),
                        season.getHotelId(),
                        season.getStartDate(),
                        season.getEndDate()
                };
                model.addRow(row);
            }

            seasonTable.setModel(model);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showSeasonForm(Season season) {
        SeasonForm seasonForm = new SeasonForm(seasonDAO, season);
        seasonForm.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                loadSeasons();
            }
        });
        seasonForm.pack();
        WindowUtils.centerWindow(seasonForm);
        seasonForm.setVisible(true);
    }
    //Season seçmemizi sağlayan metot.
    private Season getSelectedSeason() {
        int selectedRow = seasonTable.getSelectedRow();
        if (selectedRow != -1) {
            int seasonId = (int) seasonTable.getValueAt(selectedRow, 0);
            try {
                List<Season> seasons = seasonDAO.getAllSeasons();
                for (Season season : seasons) {
                    if (season.getId() == seasonId) {
                        return season;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


}
