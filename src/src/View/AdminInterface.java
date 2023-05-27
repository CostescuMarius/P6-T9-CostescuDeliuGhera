package View;

import Model.DataBaseConnection;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.*;
import java.util.Vector;

public class AdminInterface extends JPanel {
    int width, height;

    private JButton btnCreate, bLogout, binteriorConfirm, btnDelete, btnUpdate;
    private JLabel lconfirmlogout, l_create_label, l_delete_label, l_update_label;
    private ApplicationWindow fa;
    private JTextField txtEmailCreate, txtFirstNameCreate, txtLastNameCreate, txtMobileNoCreate, txtSalaryCreate
            ,txtFirstNameEdit, txtLastNameEdit, txtMobileNoEdit, txtSalaryEdit;
    private JPasswordField txtPasswordCreate, txtPasswordEdit;

    private boolean confirm_logout_open = false;
    private JComboBox<String> cbEmailDelete, cbEmailEdit;

    private Vector<String> emails;

    public AdminInterface(int width, int height, ApplicationWindow fa) {
        this.width = width;
        this.height = height;
        this.fa = fa;

        this.setBounds(0, 0, this.width, this.height);
        this.setLayout(null);

        set_logout_interface();

        set_create_interface();

        set_delete_interface();

        set_edit_interface();

        add_buttons_actions();
    }

    private void set_create_interface() {
        // panelCreate
        JPanel panelCreate = new JPanel();
        panelCreate.setLayout(null);
        panelCreate.setBounds(0, 0, this.width / 3, this.height);
        panelCreate.setBackground(new Color(191, 208, 233));
        Border border_right = BorderFactory.createMatteBorder(0, 0, 0, 3, Color.BLACK);

        panelCreate.setBorder(border_right);

        JLabel lblTitleCreate = new JLabel("Create Account");
        lblTitleCreate.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblTitleCreate.setBounds(160, 150, 180, 40);
        panelCreate.add(lblTitleCreate);

        JLabel lblEmailCreate = new JLabel("Email:");
        lblEmailCreate.setBounds(80, 230, 46, 14);
        panelCreate.add(lblEmailCreate);

        txtEmailCreate = new JTextField();
        txtEmailCreate.setBounds(170, 230, 220, 20);
        panelCreate.add(txtEmailCreate);

        JLabel lblFirstNameCreate = new JLabel("First Name:");
        lblFirstNameCreate.setBounds(80, 270, 80, 14);
        panelCreate.add(lblFirstNameCreate);

        txtFirstNameCreate = new JTextField();
        txtFirstNameCreate.setBounds(170, 270, 220, 20);
        panelCreate.add(txtFirstNameCreate);

        JLabel lblLastNameCreate = new JLabel("Last Name:");
        lblLastNameCreate.setBounds(80, 310, 80, 14);
        panelCreate.add(lblLastNameCreate);

        txtLastNameCreate = new JTextField();
        txtLastNameCreate.setBounds(170, 310, 220, 20);
        panelCreate.add(txtLastNameCreate);

        JLabel lblPasswordCreate = new JLabel("Password:");
        lblPasswordCreate.setBounds(80, 350, 80, 14);
        panelCreate.add(lblPasswordCreate);

        txtPasswordCreate = new JPasswordField();
        txtPasswordCreate.setBounds(170, 350, 220, 20);
        panelCreate.add(txtPasswordCreate);

        JLabel lblMobileNoCreate = new JLabel("Mobile No:");
        lblMobileNoCreate.setBounds(80, 390, 80, 14);
        panelCreate.add(lblMobileNoCreate);

        txtMobileNoCreate = new JTextField();
        txtMobileNoCreate.setBounds(170, 390, 220, 20);
        panelCreate.add(txtMobileNoCreate);


        JLabel lblSalaryCreate = new JLabel("Salary:");
        lblSalaryCreate.setBounds(80, 430, 80, 14);
        panelCreate.add(lblSalaryCreate);

        txtSalaryCreate = new JTextField();
        txtSalaryCreate.setBounds(170, 430, 220, 20);
        panelCreate.add(txtSalaryCreate);

        btnCreate = new JButton("Create");
        btnCreate.setBorder(null);
        btnCreate.setBackground(new Color(144, 228, 144));
        btnCreate.setBounds(190, 520, 89, 23);
        panelCreate.add(btnCreate);

        l_create_label = new JLabel("");
        l_create_label.setBounds(30, 470, this.width, 30);
        panelCreate.add(l_create_label);

        this.add(panelCreate);
    }

    private void set_delete_interface() {
        // panelDelete
        JPanel panelDelete = new JPanel();
        Border border_right = BorderFactory.createMatteBorder(0, 0, 0, 3, Color.BLACK);
        panelDelete.setBorder(border_right);
        panelDelete.setBackground(new Color(191, 208, 233));
        panelDelete.setLayout(null);
        panelDelete.setBounds(this.width/3, 0, this.width / 3, this.height);

        JLabel lblTitleDelete = new JLabel("Delete Account");
        lblTitleDelete.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblTitleDelete.setBounds(160, 150, 180, 40);
        panelDelete.add(lblTitleDelete);

        JLabel lblEmailDelete = new JLabel("Email:");
        lblEmailDelete.setBounds(80, 230, 46, 14);
        panelDelete.add(lblEmailDelete);

        get_emplyoyee_emails();
        cbEmailDelete = new JComboBox<>(new DefaultComboBoxModel(emails));
        cbEmailDelete.setBounds(170, 230, 220, 20);
        panelDelete.add(cbEmailDelete);


        btnDelete = new JButton("Delete");
        btnDelete.setBackground(new Color(255,99,70));
        btnDelete.setBorder(null);
        btnDelete.setBounds(190, 520, 89, 23);
        panelDelete.add(btnDelete);

        l_delete_label = new JLabel("");
        l_delete_label.setBounds(30, 470, this.width, 30);
        panelDelete.add(l_delete_label);

        this.add(panelDelete);
    }

    private void set_edit_interface() {
        // panelEdit
        JPanel panelEdit = new JPanel();
        panelEdit.setLayout(null);
        panelEdit.setBounds(2 * this.width / 3, 0, this.width / 3, this.height);
        panelEdit.setBackground(new Color(191, 208, 233));

        JLabel lblTitleEdit = new JLabel("Edit Account");
        lblTitleEdit.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblTitleEdit.setBounds(180, 150, 180, 40);
        panelEdit.add(lblTitleEdit);

        JLabel lblEmailEdit = new JLabel("Email:");
        lblEmailEdit.setBounds(80, 230, 46, 14);
        panelEdit.add(lblEmailEdit);

        cbEmailEdit = new JComboBox<>(new DefaultComboBoxModel(emails));
        cbEmailEdit.setBounds(170, 230, 220, 20);
        panelEdit.add(cbEmailEdit);
        add_action_combobox();

        JLabel lblFirstNameEdit = new JLabel("First Name:");
        lblFirstNameEdit.setBounds(80, 270, 80, 14);
        panelEdit.add(lblFirstNameEdit);

        txtFirstNameEdit = new JTextField();
        txtFirstNameEdit.setBounds(170, 270, 220, 20);
        panelEdit.add(txtFirstNameEdit);

        JLabel lblLastNameEdit = new JLabel("Last Name:");
        lblLastNameEdit.setBounds(80, 310, 80, 14);
        panelEdit.add(lblLastNameEdit);

        txtLastNameEdit = new JTextField();
        txtLastNameEdit.setBounds(170, 310, 220, 20);
        panelEdit.add(txtLastNameEdit);

        JLabel lblPasswordEdit = new JLabel("Password:");
        lblPasswordEdit.setBounds(80, 350, 80, 14);
        panelEdit.add(lblPasswordEdit);

        txtPasswordEdit = new JPasswordField();
        txtPasswordEdit.setBounds(170, 350, 220, 20);
        panelEdit.add(txtPasswordEdit);

        JLabel lblMobileNoEdit = new JLabel("Mobile No:");
        lblMobileNoEdit.setBounds(80, 390, 80, 14);
        panelEdit.add(lblMobileNoEdit);

        txtMobileNoEdit = new JTextField();
        txtMobileNoEdit.setBounds(170, 390, 220, 20);
        panelEdit.add(txtMobileNoEdit);

        JLabel lblSalaryEdit = new JLabel("Salary:");
        lblSalaryEdit.setBounds(80, 430, 80, 14);
        panelEdit.add(lblSalaryEdit);

        txtSalaryEdit = new JTextField();
        txtSalaryEdit.setBounds(170, 430, 220, 20);
        panelEdit.add(txtSalaryEdit);

        btnUpdate = new JButton("Update");
        btnUpdate.setBackground(Color.orange);
        btnUpdate.setBorder(null);
        btnUpdate.setBounds(200, 520, 89, 23);
        panelEdit.add(btnUpdate);

        l_update_label = new JLabel("");
        l_update_label.setBounds(30, 470, this.width, 30);
        panelEdit.add(l_update_label);

        set_to_unknown();
        this.add(panelEdit);
    }

    private void add_action_combobox() {
        cbEmailEdit.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent event) {
                if (event.getStateChange() == ItemEvent.SELECTED) {
                    Object item = event.getItem();

                    if(item.toString().equals("-")) {
                        set_to_unknown();
                    }
                    else {
                        try {
                            Connection connection = DataBaseConnection.database_connection();
                            System.out.println("Database connected!");

                            Statement statement = connection.createStatement();
                            String query = "SELECT * FROM employee ";
                            ResultSet rs = statement.executeQuery(query);

                            while (rs.next()) {
                                if (item.toString().equals(rs.getString("email"))) {
                                    txtFirstNameEdit.setText(rs.getString("firstName"));
                                    txtLastNameEdit.setText(rs.getString("lastName"));
                                    txtPasswordEdit.setText("password");
                                    txtMobileNoEdit.setText(rs.getString("mobileNo"));
                                    txtSalaryEdit.setText(rs.getString("salary"));
                                    break;
                                }
                            }
                            statement.close();
                            connection.close();
                            System.out.println("Connection closed!");

                        } catch (SQLException ex) {
                            throw new IllegalStateException("Cannot connect the database!", ex);
                        }
                    }
                }
            }
        });
    }

    private void set_to_unknown() {
        String unknown = "-";
        txtFirstNameEdit.setText(unknown);
        txtLastNameEdit.setText(unknown);
        txtPasswordEdit.setText(unknown);
        txtMobileNoEdit.setText(unknown);
        txtSalaryEdit.setText(unknown);
    }

    private void set_logout_interface() {
        bLogout = new JButton("Logout");
        bLogout.setBackground(Color.white);
        bLogout.setForeground(Color.blue);

        bLogout.setBounds(1430, 0, 100, 30);
        this.add(bLogout);

        lconfirmlogout = new JLabel();
        lconfirmlogout.setBackground(Color.white);
        lconfirmlogout.setOpaque(true);
        lconfirmlogout.setBounds(550, 330, 400, 60);
        lconfirmlogout.setLayout(new BoxLayout(lconfirmlogout, BoxLayout.Y_AXIS));
        lconfirmlogout.setBorder(BorderFactory.createLineBorder(Color.black));

        JLabel lconfirmtext = new JLabel("Are you sure you want to sign out?");
        lconfirmtext.setForeground(new Color(85, 98, 254));
        lconfirmtext.setOpaque(false);
        lconfirmtext.setAlignmentX(Component.CENTER_ALIGNMENT);
        lconfirmlogout.add(lconfirmtext);

        JPanel bConfirm = new JPanel();
        bConfirm.setLayout(new FlowLayout(FlowLayout.CENTER));
        binteriorConfirm = new JButton("Confirm");
        binteriorConfirm.setBackground(Color.white);
        binteriorConfirm.setForeground(new Color(85, 98, 254));
        bConfirm.add(binteriorConfirm);
        bConfirm.setOpaque(false);
        bConfirm.setAlignmentX(Component.CENTER_ALIGNMENT);

        lconfirmlogout.add(Box.createVerticalGlue());
        lconfirmlogout.add(bConfirm);
        lconfirmlogout.add(Box.createVerticalGlue());

        this.add(lconfirmlogout);
        lconfirmlogout.setVisible(false);

        binteriorConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lconfirmlogout.setVisible(false);
                fa.set_login_interface();
            }
        });

        bLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!confirm_logout_open) {
                    lconfirmlogout.setVisible(true);
                    confirm_logout_open = true;
                }
                else {
                    lconfirmlogout.setVisible(false);
                    confirm_logout_open = false;
                }
            }
        });
    }

    private void add_buttons_actions() {
        add_btncreate_action();

        add_btndelete_action();

        add_btnedit_action();
    }

    private void get_emplyoyee_emails() {
        try {
            Connection connection = DataBaseConnection.database_connection();
            System.out.println("Database connected!");


            Statement statement = connection.createStatement();
            String query = "SELECT * FROM employee ";
            ResultSet rs = statement.executeQuery(query);

            emails = new Vector<String>();
            while (rs.next()) {
                emails.add(rs.getString("email"));
            }

            statement.close();
            connection.close();
            System.out.println("Connection closed!");

        } catch (SQLException ex) {
            throw new IllegalStateException("Cannot connect the database!", ex);
        }
    }

    private void add_btncreate_action() {
        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection connection = DataBaseConnection.database_connection();
                    System.out.println("Database connected!");

                    if(txtEmailCreate.getText().equals("") ||
                            String.valueOf(txtPasswordCreate.getPassword()).equals("") ||
                            txtFirstNameCreate.getText().equals("") ||
                            txtLastNameCreate.getText().equals("") ||
                            txtMobileNoCreate.getText().equals("") ||
                            txtSalaryCreate.getText().equals("")) {
                        l_create_label.setText("Please fill out all fields.");
                    }
                    else {
                        Statement statement = connection.createStatement();
                        String query = "SELECT * FROM employee ";
                        ResultSet rs = statement.executeQuery(query);
                        boolean email_exists = false;
                        while (rs.next()) {
                            if (txtEmailCreate.getText().equals(rs.getString("email"))) {
                                l_create_label.setText("E-mail is already used!");
                                email_exists = true;
                                break;
                            }
                        }
                        if(email_exists == false) {
                            statement.executeUpdate("INSERT INTO employee VALUES ('"+txtEmailCreate.getText()+"','"+txtFirstNameCreate.getText()+"','"+txtLastNameCreate.getText()+"','"+new String(txtPasswordCreate.getPassword()).hashCode()+"','"+txtMobileNoCreate.getText()+"','"+txtSalaryCreate.getText()+"')");

                            get_emplyoyee_emails();

                            cbEmailDelete.setModel(new DefaultComboBoxModel(emails));
                            cbEmailEdit.setModel(new DefaultComboBoxModel(emails));
                            set_to_unknown();

                            l_create_label.setText("Correct");

                            txtEmailCreate.setText(null);
                            txtPasswordCreate.setText(null);
                            txtFirstNameCreate.setText(null);
                            txtLastNameCreate.setText(null);
                            txtMobileNoCreate.setText(null);
                            txtSalaryCreate.setText(null);
                        }
                        statement.close();
                        connection.close();
                        System.out.println("Connection closed!");
                    }

                } catch (SQLException ex) {
                    throw new IllegalStateException("Cannot connect the database!", ex);
                }
            }
        });
    }

    private void add_btndelete_action() {
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection connection = DataBaseConnection.database_connection();
                    System.out.println("Database connected!");

                    if(cbEmailDelete.getSelectedItem().toString().equals("-")) {
                        l_delete_label.setText("Please select employee to be deleted.");
                    }
                    else {
                        String email = cbEmailDelete.getSelectedItem().toString();
                        String query = "DELETE FROM employee WHERE email = ?";

                        PreparedStatement statement = connection.prepareStatement(query);
                        statement.setString(1, email);
                        statement.executeUpdate();

                        get_emplyoyee_emails();

                        cbEmailDelete.setModel(new DefaultComboBoxModel(emails));
                        cbEmailEdit.setModel(new DefaultComboBoxModel(emails));
                        set_to_unknown();

                        l_delete_label.setText("Correct");

                        statement.close();
                        connection.close();
                        System.out.println("Connection closed!");
                    }

                } catch (SQLException ex) {
                    throw new IllegalStateException("Cannot connect the database!", ex);
                }
            }
        });
    }

    private void add_btnedit_action() {
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection connection = DataBaseConnection.database_connection();
                    System.out.println("Database connected!");

                    if(cbEmailEdit.getSelectedItem().toString().equals("-")) {
                        l_update_label.setText("Please select employee to be edited.");
                    }
                    else {
                        String query = "UPDATE employee SET firstName=?, lastName=?, password=?, mobileNo=?, salary=? WHERE email=?";

                        PreparedStatement statement = connection.prepareStatement(query);
                        statement.setString(1, txtFirstNameEdit.getText());
                        statement.setString(2, txtLastNameEdit.getText());
                        statement.setInt(3, new String(txtPasswordEdit.getPassword()).hashCode());
                        statement.setString(4, txtMobileNoEdit.getText());
                        statement.setDouble(5, Double.parseDouble(txtSalaryEdit.getText()));

                        String email = cbEmailEdit.getSelectedItem().toString();
                        statement.setString(6, email);

                        statement.executeUpdate();

                        l_update_label.setText("Correct");

                        statement.close();
                        connection.close();
                        System.out.println("Connection closed!");
                    }

                } catch (SQLException ex) {
                    throw new IllegalStateException("Cannot connect the database!", ex);
                }
            }
        });
    }



}