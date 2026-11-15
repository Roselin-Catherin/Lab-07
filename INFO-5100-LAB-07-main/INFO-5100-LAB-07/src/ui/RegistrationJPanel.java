/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui;

import db.DatabaseConnector;
import java.awt.Image;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import model.User;

/**
 *
 * @author Roselin
 */
public class RegistrationJPanel extends javax.swing.JPanel {

    private ViewJPanel viewPanel;
    private JPanel cardContainer;

    private List<User> users = new ArrayList<>(); // the users shown in the table
    private User editingUser = null;              // null = UPDATE adds a new user

    public RegistrationJPanel() {
        initComponents();

        // Table columns
        String[] columns = {"ID", "First Name", "Last Name", "Gender", "Age", "Email", "Phone", "Continent", "Hobbies"};
        userJTable.setModel(new DefaultTableModel(columns, 0));

        populateTable();

        // Connect the buttons
        editButton.addActionListener(e -> editUser());
        deleteButton.addActionListener(e -> deleteUser());
        updateButton.addActionListener(e -> saveUser());
    }
    
    
    public void setViewPanel(ViewJPanel viewPanel) {
        this.viewPanel = viewPanel;
    }

    public void setCardContainer(JPanel cardContainer) {
        this.cardContainer = cardContainer;
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

    // EDIT: put the selected user's data into the fields
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
            clearForm();
            refreshTables();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Unable to delete: " + e.getMessage());
        }
    }

    // CREATE or UPDATE: add a new user, or save changes if EDIT was clicked first
    private void saveUser() {
        String firstName = firstNameTextField.getText().trim();
        String lastName = lastNameTextField.getText().trim();
        String email = emailFormattedTextField.getText().trim();
        String phone = phoneNumberFormattedTextField.getText().trim();
        int age = (int) ageSpinner.getValue();
        String photo = photoPathTextField.getText();
        if (photo.startsWith("JPG or PNG")) {
            photo = null; // no photo was chosen
        }

        // Simple validation
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in name, email and phone.");
            return;
        }
        if (age < 1) {
            JOptionPane.showMessageDialog(this, "Please pick a date of birth / enter a valid age.");
            return;
        }

        User user = new User(firstName, lastName,
                (String) genderComboBox.getSelectedItem(), age, phone, email,
                (String) continentComboBox.getSelectedItem(), hobbiesTextArea.getText(),
                photo, dateOfBirthCalenderComboBox.getDate());

        try {
            if (editingUser == null) {
                DatabaseConnector.addUser(user);
                JOptionPane.showMessageDialog(this, "User registered!");
            } else {
                user.setId(editingUser.getId());
                DatabaseConnector.editUser(user);
                JOptionPane.showMessageDialog(this, "User updated!");
            }
            clearForm();
            refreshTables();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Could not save: " + e.getMessage());
        }
    }

    private void refreshTables() {
        populateTable();
        if (viewPanel != null) {
            viewPanel.populateTable();
        }
    }

    private void clearForm() {
        editingUser = null;
        firstNameTextField.setText("");
        lastNameTextField.setText("");
        emailFormattedTextField.setText("");
        phoneNumberFormattedTextField.setText("");
        hobbiesTextArea.setText("");
        ageSpinner.setValue(0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        firstNameLabel = new javax.swing.JLabel();
        phoneLabel = new javax.swing.JLabel();
        dateOfBirthCalenderComboBox = new de.wannawork.jcalendar.JCalendarComboBox();
        firstNameTextField = new javax.swing.JTextField();
        continentLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lastNameLabel = new javax.swing.JLabel();
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
        phoneNumberFormattedTextField = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
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

        firstNameLabel.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        firstNameLabel.setForeground(new java.awt.Color(255, 255, 255));
        firstNameLabel.setText("FIRST NAME");

        phoneLabel.setForeground(new java.awt.Color(255, 255, 255));
        phoneLabel.setText("PHONE:");

        dateOfBirthCalenderComboBox.setPreferredSize(new java.awt.Dimension(64, 18));
        dateOfBirthCalenderComboBox.addChangeListener(this::dateOfBirthCalenderComboBoxStateChanged);

        continentLabel.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        continentLabel.setForeground(new java.awt.Color(255, 255, 255));
        continentLabel.setText("CONTINENT");

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("DATE OF BIRTH");

        lastNameLabel.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        lastNameLabel.setForeground(new java.awt.Color(255, 255, 255));
        lastNameLabel.setText("LAST NAME");

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
        hobbiesLabel.setText("HOBBIES");

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
        photoUploadButton.addActionListener(this::photoUploadButtonActionPerformed);

        phoneNumberFormattedTextField.setToolTipText("123-456-7890");

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Create Profile (Register)");

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("PERSONAL INFROMATION");

        jLabel4.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("CONTACT INFROMATION");

        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("ABOUT YOU");

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
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(ageSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(photoPreviewLabel)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(photoUploadButton, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(photoPathTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(emailFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel4)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(genderLabel)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dateOfBirthCalenderComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ageSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(genderLabel)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(genderComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(phoneLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(emailLabel)
                                .addComponent(jLabel11)
                                .addComponent(jLabel12)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(emailFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(phoneNumberFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(continentLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(continentComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hobbiesLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(110, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void dateOfBirthCalenderComboBoxStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_dateOfBirthCalenderComboBoxStateChanged
        Date selectedDateOfBirth = dateOfBirthCalenderComboBox.getDate();

        // Stop if no date was picked
        if (selectedDateOfBirth == null) {
            JOptionPane.showMessageDialog(this, "Please select a date of birth");
            return;
        }

        Calendar birth = Calendar.getInstance();
        birth.setTime(selectedDateOfBirth);

        Calendar today = Calendar.getInstance();

        int age = today.get(Calendar.YEAR) - birth.get(Calendar.YEAR);

        // Subtract 1 if the birthday hasn't happened yet this year
        if (today.get(Calendar.DAY_OF_YEAR) < birth.get(Calendar.DAY_OF_YEAR)) {
            age = age - 1;
        }

        ageSpinner.setValue(age);
    }//GEN-LAST:event_dateOfBirthCalenderComboBoxStateChanged

    private void photoUploadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_photoUploadButtonActionPerformed
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            photoPathTextField.setText(selectedFile.getAbsolutePath());

            // Load and scale image to fit label
            ImageIcon icon = new ImageIcon(selectedFile.getAbsolutePath());
            Image scaledImage = icon.getImage().getScaledInstance(photoPreviewLabel.getWidth(), photoPreviewLabel.getHeight(), Image.SCALE_SMOOTH);
            icon = new ImageIcon(scaledImage);

            photoPreviewLabel.setIcon(icon);
            photoPreviewLabel.setText(""); // remove placeholder text
        }
    }//GEN-LAST:event_photoUploadButtonActionPerformed


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
