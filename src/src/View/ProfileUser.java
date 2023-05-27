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

public class ProfileUser extends JPanel {
    private int width, height;

    private boolean profileChecked = false;
    private ApplicationWindow fa;
    private JButton bBackSearch;
    private JLabel lemail, llastname, lfirstname, laddress, lmobile;
    private JList<String> tickets;
    private JScrollPane ticketScrollPane;

    public ProfileUser(int width, int height, ApplicationWindow fa) {
        this.height = height;
        this.width = width;
        this.fa = fa;

        this.setBounds(0, 0, this.width, this.height);

        this.setLayout(null);

        this.setBackground(Color.gray);

        set_interface();

        try {
            Image image = ImageIO.read(new File("src//Resources//profile_background.jpg"));
            JLabel label = new JLabel(new ImageIcon(image));
            label.setBounds(0, 0,  this.width,  this.height);

            add(label);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void set_interface() {
        bBackSearch = new JButton("Back to booking");
        bBackSearch.setBounds(30, 50, 200, 30);
        bBackSearch.setBackground(new Color(255, 197,42));
        bBackSearch.setBorder(null);

        set_buttons_action();

        this.add(bBackSearch);

        int pos_x = 20, pos_y = 270;
        lemail = new JLabel();
        lemail.setFont(new Font("Arial", Font.BOLD, 19));
        //lemail.setForeground(Color.white);
        lemail.setBounds(pos_x, pos_y, 400, 30);

        llastname = new JLabel();
        llastname.setFont(new Font("Arial", Font.ITALIC, 19));
        //llastname.setForeground((Color.white));
        llastname.setBounds(pos_x, pos_y += 50, 400, 30);

        lfirstname = new JLabel();
        lfirstname.setFont(new Font("Arial", Font.ITALIC, 19));
        //lfirstname.setForeground(Color.white);
        lfirstname.setBounds(pos_x, pos_y += 50, 400, 30);

        laddress = new JLabel();
        laddress.setFont(new Font("Arial", Font.PLAIN, 19));
        //laddress.setForeground(Color.white);
        laddress.setBounds(pos_x, pos_y += 50, 400, 30);

        lmobile = new JLabel();
        lmobile.setFont(new Font("Arial", Font.PLAIN, 19));
        //lmobile.setForeground(Color.white);
        lmobile .setBounds(pos_x, pos_y += 50, 400, 30);

        tickets = new JList<String>();
        tickets.setBackground(Color.lightGray);
        ticketScrollPane = new JScrollPane(tickets);
        ticketScrollPane.setBounds(500, 200,520, 420);
        this.add(ticketScrollPane);

        this.add(lemail);
        this.add(llastname);
        this.add(lfirstname);
        this.add(laddress);
        this.add(lmobile);
    }

    private void set_buttons_action() {
        bBackSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fa.login_user_successful();
            }
        });
    }

    public void set_infos() {
        try {
            Connection connection = DataBaseConnection.database_connection();
            System.out.println("Database connected!");

            profileChecked = true;

            Statement statement = connection.createStatement();
            String query = "SELECT * FROM user ";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                if (DataBaseConnection.email_connected.equals(rs.getString("email"))) {
                    lemail.setText("Email: " + DataBaseConnection.email_connected);
                    lfirstname.setText("Firstname: " + rs.getString("firstName"));
                    llastname.setText("Lastname: " + rs.getString("lastName"));
                    laddress.setText("Address: " + rs.getString("address"));
                    lmobile.setText("Mobile: " + rs.getString("mobileNo"));
                }
            }


            String query2 = "SELECT * FROM ticket ";
            ResultSet rs2 = statement.executeQuery(query2);
            DefaultListModel<String> listModel = new DefaultListModel<>();
            while (rs2.next()) {
                if (DataBaseConnection.email_connected.equals(rs2.getString("emaiUser"))) {
                    listModel.addElement("Ticket: " + rs2.getInt("idTicket") + "   Flight: " + rs2.getInt("idFlight") + "   Type: "
                            + rs2.getString("type") + "   Price: " + rs2.getInt("price") + "    Payment: " + rs2.getString("payment"));
                }
            }
            tickets.setModel(listModel);

            statement.close();
            connection.close();
            System.out.println("Connection closed!");
        } catch (SQLException ex) {
            throw new IllegalStateException("Cannot connect the database!", ex);
        }
    }

}
