package com.hotelmanagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;
//PensionTypePanel'in oluşturulduğu kısım.
public class PensionTypePanel extends JPanel {
    private JTable pensionTypeTable;
    private JButton addPensionTypeButton, editPensionTypeButton, deletePensionTypeButton;
    private PensionTypeDAO pensionTypeDAO;

    public PensionTypePanel() {
        pensionTypeDAO = new PensionTypeDAO();
        setLayout(new BorderLayout());

        pensionTypeTable = new JTable();
        loadPensionTypes();

        addPensionTypeButton = new JButton("Add Pension Type");
        editPensionTypeButton = new JButton("Edit Pension Type");
        deletePensionTypeButton = new JButton("Delete Pension Type");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addPensionTypeButton);
        buttonPanel.add(editPensionTypeButton);
        buttonPanel.add(deletePensionTypeButton);

        add(new JScrollPane(pensionTypeTable), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        addPensionTypeButton.addActionListener(e -> showPensionTypeForm(null));
        editPensionTypeButton.addActionListener(e -> showPensionTypeForm(getSelectedPensionType()));
        deletePensionTypeButton.addActionListener(e -> deletePensionType());
    }
    //PensionType'lerin alınıp listelendiği metot.
    private void loadPensionTypes() {
        try {
            List<PensionType> pensionTypes = pensionTypeDAO.getAllPensionTypes();
            String[] columnNames = {"ID", "Hotel ID", "Type"};
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);

            for (PensionType pensionType : pensionTypes) {
                Object[] row = {
                        pensionType.getId(),
                        pensionType.getHotelId(),
                        pensionType.getType()
                };
                model.addRow(row);
            }

            pensionTypeTable.setModel(model);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showPensionTypeForm(PensionType pensionType) {
        PensionTypeForm pensionTypeForm = new PensionTypeForm(pensionTypeDAO, pensionType);
        pensionTypeForm.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                loadPensionTypes();
            }
        });

        pensionTypeForm.pack();
        WindowUtils.centerWindow(pensionTypeForm);
        setVisible(true);
    }
    //PensionType seçmemizi sağlayan metot.
    private PensionType getSelectedPensionType() {
        int selectedRow = pensionTypeTable.getSelectedRow();
        if (selectedRow != -1) {
            int pensionTypeId = (int) pensionTypeTable.getValueAt(selectedRow, 0);
            try {
                List<PensionType> pensionTypes = pensionTypeDAO.getAllPensionTypes();
                for (PensionType pensionType : pensionTypes) {
                    if (pensionType.getId() == pensionTypeId) {
                        return pensionType;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    //PensionType silmemizi sağlayan metot.
    private void deletePensionType() {
        int selectedRow = pensionTypeTable.getSelectedRow();
        if (selectedRow != -1) {
            int pensionTypeId = (int) pensionTypeTable.getValueAt(selectedRow, 0);
            try {
                pensionTypeDAO.deletePensionType(pensionTypeId);
                loadPensionTypes();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a pension type to delete.");
        }
    }
}
