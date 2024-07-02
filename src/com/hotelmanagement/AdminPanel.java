package com.hotelmanagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AdminPanel extends JPanel {
    private JTable userTable;
    private JButton addUserButton, editUserButton, deleteUserButton;
    private JComboBox<String> roleComboBox;
    private UserDAO userDAO;

    //Admin Panelin oluşturulduğu kısım
    public AdminPanel() {
        userDAO = new UserDAO();
        setLayout(new BorderLayout());

        userTable = new JTable();
        loadUsers(null);

        addUserButton = new JButton("Add User");
        editUserButton = new JButton("Edit User");
        deleteUserButton = new JButton("Delete User");

        roleComboBox = new JComboBox<>(new String[]{"All", "admin", "personel"});
        roleComboBox.addActionListener(e -> filterUsersByRole());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(roleComboBox);
        buttonPanel.add(addUserButton);
        buttonPanel.add(editUserButton);
        buttonPanel.add(deleteUserButton);

        add(new JScrollPane(userTable), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        addUserButton.addActionListener(e -> showUserForm(null));
        editUserButton.addActionListener(e -> showUserForm(getSelectedUser()));
        deleteUserButton.addActionListener(e -> deleteUser());
    }
    //Userleri rollüne göre listelediğimiz metot.
    private void loadUsers(String role) {
        try {
            List<User> users;
            if (role == null || role.equals("All")) {
                users = userDAO.getAllUsers();
            } else {
                users = userDAO.getUsersByRole(role);
            }

            String[] columnNames = {"ID", "Username", "Role"};
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);

            for (User user : users) {
                Object[] row = {
                        user.getId(),
                        user.getUsername(),
                        user.getRole()
                };
                model.addRow(row);
            }

            userTable.setModel(model);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //ComboBoxtan Rol çeken metot
    private void filterUsersByRole() {
        String selectedRole = (String) roleComboBox.getSelectedItem();
        loadUsers(selectedRole);
    }


    private void showUserForm(User user) {
        UserForm userForm = new UserForm(userDAO, user);
        userForm.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                filterUsersByRole();
            }
        });

        userForm.pack();
        WindowUtils.centerWindow(userForm);
        userForm.setVisible(true);
    }

    //Useri id den çeken metot
    private User getSelectedUser() {
        int selectedRow = userTable.getSelectedRow();
        if (selectedRow != -1) {
            int userId = (int) userTable.getValueAt(selectedRow, 0);
            try {
                List<User> users = userDAO.getAllUsers();
                for (User user : users) {
                    if (user.getId() == userId) {
                        return user;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    //Seçini Useri silen metot
    private void deleteUser() {
        int selectedRow = userTable.getSelectedRow();
        if (selectedRow != -1) {
            int userId = (int) userTable.getValueAt(selectedRow, 0);
            try {
                userDAO.deleteUser(userId);
                filterUsersByRole();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a user to delete.");
        }
    }
}
