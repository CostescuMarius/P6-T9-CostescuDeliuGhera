package View;


import Model.DataBaseConnection;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;

public class LoginScreen extends JLayeredPane {
    private int width, height;
    //private String url = "jdbc:mysql://localhost:3306/conturi", username = "root", password = "16092001";


    private JLabel l_login_email, l_login_password,
            l_logo_company, l_company_name, l_motto, l_info,
            l_register_firstname, l_register_lastname, l_register_email, l_register_password, l_register_mobile_no, l_register_address,
            l_login_info, l_register_info;
    private JTextField login_text_email, register_text_email, register_text_firstname,
            register_text_lastname, register_text_address, register_text_mobile_no;
    private JPasswordField login_text_password, register_text_password;
    private BackgroundLogin background_login;
    private JButton b_login_interface, b_register_interface, b_sign_in, b_sign_up;

    private ApplicationWindow app_wind;

    public LoginScreen(int width, int height, ApplicationWindow app_wind) {
        this.app_wind = app_wind;
        this.width = width;
        this.height = height;

        set_labels();

        set_login_info();

        set_register_info();

        set_buttons();

        add_action_buttons();
    }

    private void set_labels() {
        background_login = new BackgroundLogin(this.width, this.height);
        this.add(background_login, 1, 0);

        l_logo_company = new JLabel();
        ImageIcon background = new ImageIcon("src//Resources//logo_company.jpg");
        if (background != null) {
            l_logo_company.setIcon(background);
        }
        l_logo_company.setBounds(1100, 0, 600, 400);
        this.add(l_logo_company, 2, 0);

        l_company_name = new JLabel("MMS AirLine");
        l_company_name.setBounds(1200, 220, 150, 20);
        l_company_name.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
        l_company_name.setForeground(Color.black);
        this.add(l_company_name, 2, 0);

        l_motto = new JLabel("Embrace the Sky!");
        l_motto.setBounds(1170, 280, 250, 50);
        l_motto.setFont(new Font("Freestyle Script", Font.ITALIC, 40));
        l_motto.setForeground(Color.black);
        this.add(l_motto, 2, 0);

        l_info = new JLabel("<html>Don't have an account yet?<br/>Sign up using Register button!</html>");
        l_info.setBounds(90, 420, 500, 50);
        l_info.setFont(new Font("Arial", Font.ITALIC, 15));
        l_info.setForeground(Color.white);
        this.add(l_info, 2, 0);

        l_login_info = new JLabel();
        l_login_info.setBounds(90, 310, 300, 50);
        l_login_info.setFont(new Font("Arial", Font.BOLD, 15));
        l_login_info.setForeground(new Color(240,128,128));
        this.add(l_login_info, 2, 0);

        l_register_info = new JLabel();
        l_register_info.setBounds(90, 337, 300, 50);
        l_register_info.setFont(new Font("Arial", Font.BOLD, 15));
        l_register_info.setForeground(new Color(240,128,128));
        this.add(l_register_info, 2, 0);
    }

    private void set_register_info() {
        l_register_email = new JLabel("E-mail:");
        l_register_email.setBounds(55, 190, 100, 20);
        l_register_email.setFont(new Font("Arial", Font.ITALIC, 15));
        l_register_email.setForeground(Color.white);
        this.add(l_register_email, 2, 0);
        l_register_email.setVisible(false);

        register_text_email = new JTextField();
        register_text_email.setBounds(150, 190, 380, 20);
        register_text_email.setBorder(null);
        register_text_email.setBackground(new Color(81, 97, 200));
        register_text_email.setForeground(new Color(224, 213, 240));
        this.add(register_text_email, 2, 0);
        register_text_email.setVisible(false);

        l_register_firstname = new JLabel("First Name:");
        l_register_firstname.setBounds(55, 225, 100, 20);
        l_register_firstname.setFont(new Font("Arial", Font.ITALIC, 15));
        l_register_firstname.setForeground(Color.white);
        this.add(l_register_firstname, 2, 0);
        l_register_firstname.setVisible(false);

        register_text_firstname = new JTextField();
        register_text_firstname.setBounds(150, 225, 170, 20);
        register_text_firstname.setBorder(null);
        register_text_firstname.setBackground(new Color(81, 97, 200));
        register_text_firstname.setForeground(new Color(224, 213, 240));
        this.add(register_text_firstname, 2, 0);
        register_text_firstname.setVisible(false);

        l_register_lastname = new JLabel("Last Name:");
        l_register_lastname.setBounds(340, 225, 100, 20);
        l_register_lastname.setFont(new Font("Arial", Font.ITALIC, 15));
        l_register_lastname.setForeground(Color.white);
        this.add(l_register_lastname, 2, 0);
        l_register_lastname.setVisible(false);

        register_text_lastname = new JTextField();
        register_text_lastname.setBounds(430, 225, 100, 20);
        register_text_lastname.setBorder(null);
        register_text_lastname.setBackground(new Color(81, 97, 200));
        register_text_lastname.setForeground(new Color(224, 213, 240));
        this.add(register_text_lastname, 2, 0);
        register_text_lastname.setVisible(false);

        l_register_address = new JLabel("Address:");
        l_register_address.setBounds(55, 260, 100, 20);
        l_register_address.setFont(new Font("Arial", Font.ITALIC, 15));
        l_register_address.setForeground(Color.white);
        this.add(l_register_address, 2, 0);
        l_register_address.setVisible(false);

        register_text_address = new JTextField();
        register_text_address.setBounds(150, 260, 380, 20);
        register_text_address.setBorder(null);
        register_text_address.setBackground(new Color(81, 97, 200));
        register_text_address.setForeground(new Color(224, 213, 240));
        this.add(register_text_address, 2, 0);
        register_text_address.setVisible(false);

        l_register_mobile_no = new JLabel("Mobile No:");
        l_register_mobile_no.setBounds(55, 295, 120, 20);
        l_register_mobile_no.setFont(new Font("Arial", Font.ITALIC, 15));
        l_register_mobile_no.setForeground(Color.white);
        this.add(l_register_mobile_no, 2, 0);
        l_register_mobile_no.setVisible(false);

        register_text_mobile_no = new JTextField();
        register_text_mobile_no.setBounds(150, 295, 380, 20);
        register_text_mobile_no.setBorder(null);
        register_text_mobile_no.setBackground(new Color(81, 97, 200));
        register_text_mobile_no.setForeground(new Color(224, 213, 240));
        this.add(register_text_mobile_no, 2, 0);
        register_text_mobile_no.setVisible(false);

        l_register_password = new JLabel("Password:");
        l_register_password.setBounds(55, 330, 100, 20);
        l_register_password.setFont(new Font("Arial", Font.ITALIC, 15));
        l_register_password.setForeground(Color.white);
        this.add(l_register_password, 2, 0);
        l_register_password.setVisible(false);

        register_text_password = new JPasswordField();
        register_text_password.setBounds(150, 330, 380, 20);
        register_text_password.setBorder(null);
        register_text_password.setBackground(new Color(81, 97, 200));
        register_text_password.setForeground(new Color(224, 213, 240));
        this.add(register_text_password, 2, 0);
        register_text_password.setVisible(false);
    }

    private void set_login_info() {
        l_login_email = new JLabel("E-mail:");
        l_login_email.setBounds(70, 240, 100, 20);
        l_login_email.setFont(new Font("Arial", Font.ITALIC, 15));
        l_login_email.setForeground(Color.white);
        this.add(l_login_email, 2, 0);

        login_text_email = new JTextField();
        login_text_email.setBounds(170, 240, 360, 20);
        login_text_email.setBorder(null);
        login_text_email.setBackground(new Color(81, 97, 200));
        login_text_email.setForeground(new Color(224, 213, 240));
        this.add(login_text_email, 2, 0);

        l_login_password = new JLabel("Password:");
        l_login_password.setBounds(70, 280, 100, 20);
        l_login_password.setFont(new Font("Arial", Font.ITALIC, 15));
        l_login_password.setForeground(Color.white);
        this.add(l_login_password, 2, 0);

        login_text_password = new JPasswordField();
        login_text_password.setBounds(170, 280, 360, 20);
        login_text_password.setBorder(null);
        login_text_password.setBackground(new Color(81, 97, 200));
        login_text_password.setForeground(new Color(224, 213, 240));
        this.add(login_text_password, 2, 0);
    }

    private void set_buttons(){
        b_login_interface = new JButton("Login");
        b_login_interface.setBounds(50, 150, 75, 25);
        b_login_interface.setBackground(new Color(123, 126, 230));
        b_login_interface.setForeground(Color.white);
        this.add(b_login_interface, 2, 0);

        b_register_interface = new JButton("Register");
        b_register_interface.setBounds(125, 150, 425, 25);
        this.add(b_register_interface, 2, 0);

        b_sign_in = new JButton("Sign in");
        b_sign_in.setBounds(250, 365, 100, 25);
        b_sign_in.setBorder(null);
        b_sign_in.setBackground(new Color(52, 184, 255));
        b_sign_in.setForeground(Color.white);
        this.add(b_sign_in, 2, 0);

        b_sign_up = new JButton("Sign up");
        b_sign_up.setBounds(250, 370, 100, 25);
        b_sign_up.setBorder(null);
        b_sign_up.setBackground(new Color(52, 184, 255));
        b_sign_up.setForeground(Color.white);
        this.add(b_sign_up, 2, 0);
        b_sign_up.setVisible(false);
    }

    private void add_action_buttons() {
        b_login_interface.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b_login_interface.setBackground(new Color(123, 126, 230));
                b_login_interface.setForeground(Color.white);
                b_register_interface.setBackground(null);
                b_register_interface.setForeground(null);

                b_sign_in.setVisible(true);
                b_sign_up.setVisible(false);

                l_login_info.setVisible(true);
                l_register_info.setVisible(false);
                l_register_info.setText("");

                l_register_firstname.setVisible(false);
                register_text_email.setVisible(false);

                l_register_lastname.setVisible(false);
                register_text_firstname.setVisible(false);

                l_register_email.setVisible(false);
                register_text_lastname.setVisible(false);

                l_register_address.setVisible(false);
                register_text_address.setVisible(false);

                l_register_mobile_no.setVisible(false);
                register_text_mobile_no.setVisible(false);

                l_register_password.setVisible(false);
                register_text_password.setVisible(false);

                l_login_email.setVisible(true);
                l_login_password.setVisible(true);
                login_text_email.setVisible(true);
                login_text_password.setVisible(true);

                l_info.setText("<html>Don't have an account yet?<br/>Sign up using Register button!</html>");
            }
        });

        b_register_interface.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b_register_interface.setBackground(new Color(123, 126, 230));
                b_register_interface.setForeground(Color.white);
                b_login_interface.setBackground(null);
                b_login_interface.setForeground(null);

                b_sign_in.setVisible(false);
                b_sign_up.setVisible(true);

                l_login_info.setVisible(false);
                l_login_info.setText("");
                l_register_info.setVisible(true);

                l_login_email.setVisible(false);
                l_login_password.setVisible(false);
                login_text_email.setVisible(false);
                login_text_password.setVisible(false);

                l_register_firstname.setVisible(true);
                register_text_email.setVisible(true);

                l_register_lastname.setVisible(true);
                register_text_firstname.setVisible(true);

                l_register_email.setVisible(true);
                register_text_lastname.setVisible(true);

                l_register_address.setVisible(true);
                register_text_address.setVisible(true);

                l_register_mobile_no.setVisible(true);
                register_text_mobile_no.setVisible(true);

                l_register_password.setVisible(true);
                register_text_password.setVisible(true);

                l_info.setText("<html>Already have an account?<br/>Sign in!</html>");
            }
        });

        b_sign_in.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean account_found = false;
                try {
                    Connection connection = DataBaseConnection.database_connection();
                    System.out.println("Database connected!");

                    if(login_text_email.getText().equals("") ||
                            String.valueOf(login_text_password.getPassword()).equals("")) {
                        l_login_info.setText("Please fill out all fields.");
                    }
                    else {
                        Statement statement = connection.createStatement();
                        String query = "SELECT * FROM user ";
                        ResultSet rs = statement.executeQuery(query);
                        boolean email_exists = false;
                        while (rs.next()) {
                            if (login_text_email.getText().equals(rs.getString("email"))) {
                                email_exists = true;

                                if (new String(login_text_password.getPassword()).hashCode() == (rs.getInt("password"))) {
                                    account_found = true;
                                    DataBaseConnection.email_connected = rs.getString("email");
                                    login_text_email.setText(null);
                                    login_text_password.setText(null);
                                    l_login_info.setText("");
                                    if(rs.getInt("type") == 0) {
                                        app_wind.login_user_successful();
                                        break;
                                    }
                                    else if(rs.getInt("type") == 1) {
                                        app_wind.login_admin_successful();
                                        break;
                                    }
                                } else {
                                    l_login_info.setText("Wrong Password!");
                                }
                            }
                        }

                        if(!account_found) {
                            String query2 = "SELECT * FROM employee ";
                            ResultSet rs2 = statement.executeQuery(query2);
                            while (rs2.next()) {
                                if (login_text_email.getText().equals(rs2.getString("email"))) {
                                    email_exists = true;

                                    if (new String(login_text_password.getPassword()).hashCode() == (rs2.getInt("password"))) {
                                        DataBaseConnection.email_connected = rs2.getString("email");
                                        login_text_email.setText(null);
                                        login_text_password.setText(null);
                                        l_login_info.setText("");

                                        app_wind.login_employee_successful();
                                        break;

                                    } else {
                                        l_login_info.setText("Wrong Password!");
                                    }
                                }
                            }
                        }

                        if(email_exists == false) {
                            l_login_info.setText("Wrong E-mail or Password!");
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

        b_sign_up.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection connection = DataBaseConnection.database_connection();
                    System.out.println("Database connected!");

                    if(register_text_email.getText().equals("") ||
                            String.valueOf(register_text_password.getPassword()).equals("") ||
                            register_text_firstname.getText().equals("") ||
                            register_text_lastname.getText().equals("") ||
                            register_text_address.getText().equals("") ||
                            register_text_mobile_no.getText().equals("")) {
                        l_register_info.setText("Please fill out all fields.");
                    }
                    else {
                        Statement statement = connection.createStatement();
                        String query = "SELECT * FROM user ";
                        ResultSet rs = statement.executeQuery(query);
                        boolean email_exists = false;
                        while (rs.next()) {
                            if (register_text_email.getText().equals(rs.getString("email"))) {
                                l_register_info.setText("E-mail is already used!");
                                email_exists = true;
                                break;
                            }
                        }
                        if(email_exists == false) {
                            statement.executeUpdate("INSERT INTO user VALUES ('"+register_text_email.getText()+"','"+register_text_firstname.getText()+"','"+register_text_lastname.getText()+"','"+register_text_address.getText()+"','"+register_text_mobile_no.getText()+"','"+new String(register_text_password.getPassword()).hashCode()+"', 0)");
                            l_register_info.setText("Correct");

                            register_text_email.setText(null);
                            register_text_firstname.setText(null);
                            register_text_lastname.setText(null);
                            register_text_address.setText(null);
                            register_text_mobile_no.setText(null);
                            register_text_password.setText(null);

                            b_login_interface.doClick();
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

    /*private Connection database_connection() throws SQLException {
        System.out.println("Connecting database...");
        Connection connection = DriverManager.getConnection(url, username, password);
        return connection;

        /*try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Database connected!");


            //statement.executeUpdate("DELETE FROM user ");
            statement.close();
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }*/
    //}
}