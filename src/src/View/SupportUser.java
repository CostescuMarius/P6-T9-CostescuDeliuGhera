package View;

import Model.DataBaseConnection;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SupportUser extends JPanel {
    private int width, height;

    private boolean profileChecked = false;
    private ApplicationWindow fa;
    private JButton bBackSearch, bNewQuestion, bQuestions, bSubmitNewQuestion, bContact;
    private JLabel lsubject, lcontent, lcontactinfo;
    private JTextField txtsubjext;
    private JTextArea txtcontent;

    private JList<String> listQuestions;

    public SupportUser(int width, int height, ApplicationWindow fa) {
        this.height = height;
        this.width = width;
        this.fa = fa;

        this.setBounds(0, 0, this.width, this.height);

        this.setLayout(null);

        this.setBackground(Color.gray);

        set_interface();

        try {
            Image image = ImageIO.read(new File("src//Resources//support_background.jpg"));
            JLabel label = new JLabel(new ImageIcon(image));
            label.setBounds(0, 0,  this.width,  this.height);

            add(label);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void set_interface() {
        bNewQuestion = new JButton("Submit new question");
        bNewQuestion.setBounds(3, 145, 170, 20);
        bNewQuestion.setOpaque(false);
        bNewQuestion.setBackground(new Color(0, 0, 0, 0));
        bNewQuestion.setFont(new Font("Arial", Font.ITALIC, 17));
        bNewQuestion.setBorder(null);
        bNewQuestion.setForeground(Color.white);
        this.add(bNewQuestion);

        bContact = new JButton("Contact");
        bContact.setBounds(950, 145, 170, 20);
        bContact.setOpaque(false);
        bContact.setBackground(new Color(0, 0, 0, 0));
        bContact.setFont(new Font("Arial", Font.ITALIC, 17));
        bContact.setBorder(null);
        bContact.setForeground(Color.white);
        this.add(bContact);

        bQuestions = new JButton("See my questions");
        bQuestions.setBounds(1, 175, 150, 20);
        bQuestions.setOpaque(false);
        bQuestions.setBackground(new Color(0, 0, 0, 0));
        bQuestions.setFont(new Font("Arial", Font.ITALIC, 17));
        bQuestions.setBorder(null);
        bQuestions.setForeground(Color.white);
        this.add(bQuestions);

        bBackSearch = new JButton("Back to booking");
        bBackSearch.setBounds(30, 50, 200, 30);
        bBackSearch.setBackground(new Color(255, 197,42));
        bBackSearch.setBorder(null);
        this.add(bBackSearch);

        set_new_question_interface();
        set_buttons_action();

        lcontactinfo = new JLabel("<html>E-mail 1: deliu.madalina.u8u@student.ucv.ro <br><br> E-mail 2: costescu.constantin.u8h@student.ucv.ro <br><br> E-mail 3: ghera.stefan.i6i@student.ucv.ro <br><br> Tel: 0770106521<html>");
        lcontactinfo.setFont(new Font("Arial", Font.BOLD, 20));
        lcontactinfo.setForeground(Color.white);
        lcontactinfo.setBounds(650, 450, 500, 210);
        this.add(lcontactinfo);

        listQuestions = new JList<String>();
        listQuestions.setBounds(50, 220, 1050, 450);
        this.add(listQuestions);
        listQuestions.setVisible(false);
    }

    private void set_new_question_interface() {
        lsubject = new JLabel("Subject:");
        lsubject.setFont(new Font("Arial", Font.BOLD, 19));
        lsubject.setBounds(30, 248, 100, 30);
        this.add(lsubject);
        lsubject.setVisible(false);

        txtsubjext = new JTextField();
        txtsubjext.setBounds(120, 250, 600, 30);
        this.add(txtsubjext);
        txtsubjext.setVisible(false);

        lcontent = new JLabel("Content:");
        lcontent.setFont(new Font("Arial", Font.BOLD, 19));
        lcontent.setBounds(30, 285, 100, 30);
        this.add(lcontent);
        lcontent.setVisible(false);

        txtcontent = new JTextArea();
        txtcontent.setBounds(120, 290, 600, 320);
        txtcontent.setLineWrap(true);
        txtcontent.setWrapStyleWord(true);
        this.add(txtcontent);
        txtcontent.setVisible(false);

        bSubmitNewQuestion = new JButton("Submit");
        bSubmitNewQuestion.setBounds(370, 650, 100, 20);
        bSubmitNewQuestion.setForeground(new Color(255, 197,42));
        bSubmitNewQuestion.setBackground(Color.gray);
        this.add(bSubmitNewQuestion);
        bSubmitNewQuestion.setVisible(false);
    }

    private void set_buttons_action() {
        bBackSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fa.login_user_successful();
            }
        });

        bNewQuestion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                create_new_question();
            }
        });

        bSubmitNewQuestion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection connection = DataBaseConnection.database_connection();
                    System.out.println("Database connected!");

                    Statement statement = connection.createStatement();

                    String query = "SELECT MAX(idProblem) FROM problem ";
                    ResultSet rs = statement.executeQuery(query);

                    int idProblem = -1;
                    if(rs.next()) {
                        idProblem = rs.getInt(1);
                    }
                    idProblem++;

                    statement.executeUpdate("INSERT INTO problem VALUES ('"+idProblem+"','"+txtcontent.getText()+"','in progress','"+DataBaseConnection.email_connected+"','-','"+txtsubjext.getText()+"', '-')");

                    statement.close();
                    connection.close();
                    System.out.println("Connection closed!");
                } catch (SQLException ex) {
                    throw new IllegalStateException("Cannot connect the database!", ex);
                }
                txtcontent.setText("");
                txtsubjext.setText("");
            }
        });

        bQuestions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                show_questions(true);
                show_create_question_interface(false);
                show_contact(false);
            }
        });

        bContact.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                show_contact(true);
                show_create_question_interface(false);
                show_questions(false);
            }
        });
    }

    private void create_new_question() {
        show_contact(false);
        show_create_question_interface(true);
        show_questions(false);
    }

    private void show_contact(boolean state) {
        lcontactinfo.setVisible(state);
    }

    private void show_create_question_interface(boolean state) {
        lsubject.setVisible(state);
        lcontent.setVisible(state);

        txtsubjext.setVisible(state);
        txtcontent.setVisible(state);

        bSubmitNewQuestion.setVisible(state);
    }

    private void show_questions(boolean state) {
        listQuestions.setVisible(state);

        listQuestions.removeAll();

        if(state) {
            try {
                Connection connection = DataBaseConnection.database_connection();
                System.out.println("Database connected!");

                Statement statement = connection.createStatement();

                String query = "SELECT * FROM problem ";
                ResultSet rs = statement.executeQuery(query);

                DefaultListModel<String> model = new DefaultListModel<String>();
                while (rs.next()) {
                    if (DataBaseConnection.email_connected.equals(rs.getString("emailUser"))) {
                        int idProblem = rs.getInt("idProblem");
                        String subject = rs.getString("subject");
                        String status = rs.getString("status");
                        String content = rs.getString("content");
                        String solve = rs.getString("solve");

                        String problemx = "Problem number: " + idProblem + "    Subject: "+ subject + "\n    Content: " +content + "    Status: " + status + "    Solve: " + solve ;

                        model.addElement(problemx);

                    }
                }
                listQuestions.setModel(model);



                statement.close();
                connection.close();
                System.out.println("Connection closed!");
            } catch (SQLException ex) {
                throw new IllegalStateException("Cannot connect the database!", ex);
            }
        }
    }

}

