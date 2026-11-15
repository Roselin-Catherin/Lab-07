/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui;

import db.DatabaseConnector;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.User;

/**
 *
 * @author Roselin
 */
public class ViewJPanel extends javax.swing.JPanel {

    private List<User> users = new ArrayList<>(); // the users shown in the table
    private User editingUser = null;              // set by EDIT, saved by UPDATE

    public ViewJPanel() {
        initComponents();

        // Table columns
        String[] columns = {"ID", "First Name", "Last Name", "Gender", "Age", "Email", "Phone", "Continent", "Hobbies"};
        userJTable.setModel(new DefaultTableModel(columns, 0));

        setFieldsEnabled(false);
        populateTable();

        // Connect the buttons
        editButton.addActionListener(e -> editUser());
        deleteButton.addActionListener(e -> deleteUser());
        updateButton.addActionListener(e -> updateUser());
    }
// READ: load all users from the database into the table
    public void populateTable() {
        DefaultTableModel model = (DefaultTableModel) userJTable.getModel();
        model.setRowCount(0);
        try {
            users = DatabaseConnector.getAllUsers();
            for (User u : users) {
                model.addRow(new Object[]{u.getId(), u.getFirstName(), u.getLastName(), u.getGender(),
                                          u.getAge(), u.getEmail(), u.getPhone(), u.getContinent(), u.getHobbies()});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Could not load users: " + e.getMessage());
        }
    }

    // EDIT: show the selected user in the fields and unlock them
    private void editUser() {
        int row = userJTable.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Please select a row first.");
            return;
        }
        editingUser = users.get(row);

        firstNameTextField.setText(editingUser.getFirstName());
        lastNameTextField.setText(editingUser.getLastName());
        if (editingUser.getDate() != null) {
            dateOfBirthCalenderComboBox.setDate(editingUser.getDate());
        }
        ageSpinner.setValue(editingUser.getAge());
        genderComboBox.setSelectedItem(editingUser.getGender());
        emailFormattedTextField.setText(editingUser.getEmail());
        phoneNumberFormattedTextField.setText(editingUser.getPhone());
        continentComboBox.setSelectedItem(editingUser.getContinent());
        hobbiesTextArea.setText(editingUser.getHobbies());

        setFieldsEnabled(true);
    }

    // DELETE: remove the selected user from the database
    private void deleteUser() {
        int row = userJTable.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Please select a row first.");
            return;
        }
        try {
            DatabaseConnector.deleteUser(users.get(row).getId());
            JOptionPane.showMessageDialog(this, "User deleted.");
            editingUser = null;
            setFieldsEnabled(false);
            populateTable();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Unable to delete: " + e.getMessage());
        }
    }

    // UPDATE: save the edited fields to the database
    private void updateUser() {
        if (editingUser == null) {
            JOptionPane.showMessageDialog(this, "Select a row and click EDIT first.");
            return;
        }

        editingUser.setFirstName(firstNameTextField.getText().trim());
        editingUser.setLastName(lastNameTextField.getText().trim());
        editingUser.setDate(dateOfBirthCalenderComboBox.getDate());
        editingUser.setAge((int) ageSpinner.getValue());
        editingUser.setGender((String) genderComboBox.getSelectedItem());
        editingUser.setEmail(emailFormattedTextField.getText().trim());
        editingUser.setPhone(phoneNumberFormattedTextField.getText().trim());
        editingUser.setContinent((String) continentComboBox.getSelectedItem());
        editingUser.setHobbies(hobbiesTextArea.getText());

        try {
            DatabaseConnector.editUser(editingUser);
            JOptionPane.showMessageDialog(this, "User updated!");
            editingUser = null;
            setFieldsEnabled(false);
            populateTable();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Unable to update: " + e.getMessage());
        }
    }

    // Turns the input fields on (true) or off (false)
    private void setFieldsEnabled(boolean enabled) {
        firstNameTextField.setEnabled(enabled);
        lastNameTextField.setEnabled(enabled);
        dateOfBirthCalenderComboBox.setEnabled(enabled);
        ageSpinner.setEnabled(enabled);
        genderComboBox.setEnabled(enabled);
        emailFormattedTextField.setEnabled(enabled);
        phoneNumberFormattedTextField.setEnabled(enabled);
        continentComboBox.setEnabled(enabled);
        hobbiesTextArea.setEnabled(enabled);
        photoPathTextField.setEnabled(false);
        photoUploadButton.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        firstNameTextField = new javax.swing.JTextField();
        continentLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lastNameLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        continentComboBox = new javax.swing.JComboBox<>();
        lastNameTextField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        hobbiesTextArea = new javax.swing.JTextArea();
        ageSpinner = new javax.swing.JSpinner();
        ageLabel = new javax.swing.JLabel();
        hobbiesLabel = new javax.swing.JLabel();
        genderLabel = new javax.swing.JLabel();
        photoPreviewLabel = new javax.swing.JLabel();
        genderComboBox = new javax.swing.JComboBox<>();
        emailLabel = new javax.swing.JLabel();
        photoPathTextField = new javax.swing.JTextField();
        emailFormattedTextField = new javax.swing.JFormattedTextField();
        photoUploadButton = new javax.swing.JButton();
        firstNameLabel = new javax.swing.JLabel();
        phoneNumberFormattedTextField = new javax.swing.JFormattedTextField();
        phoneLabel = new javax.swing.JLabel();
        dateOfBirthCalenderComboBox = new de.wannawork.jcalendar.JCalendarComboBox();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        userJTable = new javax.swing.JTable();
        editButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(26, 26, 25));

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Create Profile (View)");

        firstNameTextField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        continentLabel.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        continentLabel.setForeground(new java.awt.Color(255, 255, 255));
        continentLabel.setText("CONTINENT");

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("PERSONAL INFROMATION");

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("DATE OF BIRTH");

        jLabel4.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("CONTACT INFROMATION");

        lastNameLabel.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        lastNameLabel.setForeground(new java.awt.Color(255, 255, 255));
        lastNameLabel.setText("LAST NAME");

        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("ABOUT YOU");

        continentComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Africa", "Antarctica", "Asia", "Europe", "North America", "South America", "Australia" }));

        lastNameTextField.setPreferredSize(new java.awt.Dimension(64, 18));

        hobbiesTextArea.setColumns(20);
        hobbiesTextArea.setRows(5);
        hobbiesTextArea.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jScrollPane1.setViewportView(hobbiesTextArea);

        ageLabel.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        ageLabel.setForeground(new java.awt.Color(255, 255, 255));
        ageLabel.setText("AGE");

        hobbiesLabel.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        hobbiesLabel.setForeground(new java.awt.Color(255, 255, 255));
        hobbiesLabel.setText("HOBBIES:");

        genderLabel.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        genderLabel.setForeground(new java.awt.Color(255, 255, 255));
        genderLabel.setText("GENDER");

        photoPreviewLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/user.png"))); // NOI18N

        genderComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));

        emailLabel.setForeground(new java.awt.Color(255, 255, 255));
        emailLabel.setText("EMAIL");

        photoPathTextField.setBackground(new java.awt.Color(26, 26, 25));
        photoPathTextField.setForeground(new java.awt.Color(204, 204, 204));
        photoPathTextField.setText("JPG or PNG, up to 5 MB");
        photoPathTextField.setBorder(null);

        photoUploadButton.setBackground(new java.awt.Color(26, 26, 25));
        photoUploadButton.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        photoUploadButton.setForeground(new java.awt.Color(255, 255, 255));
        photoUploadButton.setText("Choose Photo");
        photoUploadButton.setBorder(null);

        firstNameLabel.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        firstNameLabel.setForeground(new java.awt.Color(255, 255, 255));
        firstNameLabel.setText("FIRST NAME");

        phoneNumberFormattedTextField.setToolTipText("123-456-7890");

        phoneLabel.setForeground(new java.awt.Color(255, 255, 255));
        phoneLabel.setText("PHONE:");

        dateOfBirthCalenderComboBox.setPreferredSize(new java.awt.Dimension(64, 18));

        jLabel6.setForeground(new java.awt.Color(255, 0, 0));
        jLabel6.setText("*");

        jLabel7.setForeground(new java.awt.Color(255, 0, 0));
        jLabel7.setText("*");

        jLabel8.setForeground(new java.awt.Color(255, 0, 0));
        jLabel8.setText("*");

        jLabel9.setForeground(new java.awt.Color(255, 0, 0));
        jLabel9.setText("*");

        jLabel10.setForeground(new java.awt.Color(255, 0, 0));
        jLabel10.setText("*");

        jLabel11.setForeground(new java.awt.Color(255, 0, 0));
        jLabel11.setText("*");

        jLabel12.setForeground(new java.awt.Color(255, 0, 0));
        jLabel12.setText("*");

        userJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(userJTable);

        editButton.setBackground(new java.awt.Color(42, 120, 214));
        editButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        editButton.setForeground(new java.awt.Color(255, 255, 255));
        editButton.setText("EDIT");
        editButton.setBorderPainted(false);

        deleteButton.setBackground(new java.awt.Color(42, 120, 214));
        deleteButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        deleteButton.setForeground(new java.awt.Color(255, 255, 255));
        deleteButton.setText("DELETE");
        deleteButton.setBorderPainted(false);

        updateButton.setBackground(new java.awt.Color(42, 120, 214));
        updateButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        updateButton.setForeground(new java.awt.Color(255, 255, 255));
        updateButton.setText("UPDATE");
        updateButton.setBorderPainted(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hobbiesLabel)
                    .addComponent(continentComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(continentLabel)
                    .addComponent(jLabel5)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(firstNameLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(firstNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateOfBirthCalenderComboBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lastNameLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lastNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(ageLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(ageSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(photoPreviewLabel)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(photoUploadButton, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(photoPathTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(emailFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel4)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(genderLabel)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(genderComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(emailLabel)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(phoneLabel)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(phoneNumberFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2))
                .addGap(0, 28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(photoPreviewLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(photoUploadButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(photoPathTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(firstNameLabel)
                            .addComponent(lastNameLabel)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(firstNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lastNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(ageLabel)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dateOfBirthCalenderComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ageSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(genderLabel)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(genderComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(phoneLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(emailLabel)
                        .addComponent(jLabel11)
                        .addComponent(jLabel12)))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(phoneNumberFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(continentLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(continentComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hobbiesLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(98, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ageLabel;
    private javax.swing.JSpinner ageSpinner;
    private javax.swing.JComboBox<String> continentComboBox;
    private javax.swing.JLabel continentLabel;
    private de.wannawork.jcalendar.JCalendarComboBox dateOfBirthCalenderComboBox;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton editButton;
    private javax.swing.JFormattedTextField emailFormattedTextField;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JLabel firstNameLabel;
    private javax.swing.JTextField firstNameTextField;
    private javax.swing.JComboBox<String> genderComboBox;
    private javax.swing.JLabel genderLabel;
    private javax.swing.JLabel hobbiesLabel;
    private javax.swing.JTextArea hobbiesTextArea;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lastNameLabel;
    private javax.swing.JTextField lastNameTextField;
    private javax.swing.JLabel phoneLabel;
    private javax.swing.JFormattedTextField phoneNumberFormattedTextField;
    private javax.swing.JTextField photoPathTextField;
    private javax.swing.JLabel photoPreviewLabel;
    private javax.swing.JButton photoUploadButton;
    private javax.swing.JButton updateButton;
    private javax.swing.JTable userJTable;
    // End of variables declaration//GEN-END:variables
}
