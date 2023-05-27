package View;

import Model.DataBaseConnection;

import javax.print.attribute.standard.JobName;
import javax.swing.*;
import javax.swing.plaf.nimbus.State;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Random;
import java.util.Vector;

public class HomepageUser extends JPanel{
    private int width, height;

    private JList tickets;
    private JButton bSearch, bSupport, bProfile, bLogout;
    private JLabel lconfirmlogout, linfo, lresult;
    private JButton binteriorConfirm, bSeeTickets, bReserve;
    boolean confirm_logout_open = false;

    private JScrollPane ticketScrollPane;
    private JTextField textDepart, textArrival, textData;
    private JComboBox<String> cbType;
    private JLabel ldepart, larrival, ldata, ltype;
    private Vector<String> tickets_full;
    private Vector<String> tickets_database;
    private ApplicationWindow fa;
    public HomepageUser(int width, int height, ApplicationWindow fa) {
        this.width = width;
        this.height = height;
        this.fa = fa;

        this.setBounds(0, 0, this.width + 1000, this.height + 1000);


        this.setLayout(null);

        set_user_interface();

        this.setVisible(true);
    }

    private void set_user_interface() {
        set_components();
        set_labels();
        add_buttons_action();
    }

    private void set_labels() {
        BackgroundHomepage background_hp = new BackgroundHomepage(this.width, this.height);
        this.add(background_hp);
    }

    private void set_components() {
        bSearch = new JButton("Search");
        bSearch.setBounds(1230, 375, 150, 30);
        bSearch.setBackground(new Color(255, 197,42));
        bSearch.setBorder(null);
        this.add(bSearch);

        bSupport = new JButton("Support");
        bSupport.setBounds(1300, 0, 100, 30);
        this.add(bSupport);

        bProfile = new JButton("Profil");
        bProfile.setBounds(1200, 0, 100, 30);
        this.add(bProfile);


        linfo = new JLabel();
        linfo.setText("<html>Profita de reducerile companiilor aeriene!<br>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;Rezerva acum! :)</html>");
        linfo.setForeground(Color.white);
        linfo.setFont(new Font("Times New Roman", Font.BOLD, 32));
        linfo.setBounds(520, 180, 800, 100);
        this.add(linfo);

        set_logout_interface();
        add_search_texts();
    }

    private void set_logout_interface() {
        bLogout = new JButton("Logout");
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
    }

    private void add_search_texts() {
        textDepart = new JTextField();
        textDepart.setBounds(150, 380, 225, 25);

        textArrival = new JTextField();
        textArrival.setBounds(420, 380, 225, 25);

        textData = new JTextField();
        textData.setBounds(690, 380, 225, 25);

        cbType = new JComboBox<>();
        Vector<String> types = new Vector<String>();
        types.add("Full ticket");
        types.add("Student discount");
        types.add("Retiree discount");
        cbType.setModel(new DefaultComboBoxModel<>(types));
        cbType.setBounds(960, 380, 225, 25);

        ldepart = new JLabel("Depart");
        ldepart.setForeground(Color.white);
        ldepart.setBounds(150, 342, 225, 25);

        larrival = new JLabel("Arrival");
        larrival.setForeground((Color.white));
        larrival.setBounds(420, 342, 225, 25);

        ldata = new JLabel("Data");
        ldata.setForeground(Color.white);
        ldata.setBounds(690, 342, 225, 25);

        ltype = new JLabel("Type");
        ltype.setForeground(Color.white);
        ltype.setBounds(960, 342, 150, 25);

        lresult = new JLabel();
        lresult.setBounds(10, 650, 225, 25);

        bSeeTickets = new JButton("See tickets");
        bSeeTickets.setBorder(null);
        bSeeTickets.setBackground(Color.white);
        bSeeTickets.setForeground(Color.BLUE);
        bSeeTickets.setBounds(160, 650, 70, 25);


        tickets = new JList<String>();
        tickets.setBackground(Color.lightGray);
        ticketScrollPane = new JScrollPane(tickets);
        ticketScrollPane.setBounds(300, 570, 1000, 200);

        bReserve = new JButton("Reserve");
        bReserve.setBounds(1320, 650, 100, 20);
        bReserve.setBorder(null);
        bReserve.setBackground(Color.white);
        bReserve.setForeground(Color.BLUE);

        this.add(textDepart);
        this.add(textArrival);
        this.add(textData);
        this.add(cbType);
        this.add(lresult);
        this.add(bSeeTickets);
        this.add(ldepart);
        this.add(larrival);
        this.add(ldata);
        this.add(ltype);
        this.add(ticketScrollPane);
        this.add(bReserve);
    }

    private void add_buttons_action() {
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

        bProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fa.set_profile_interface();
            }
        });

        bSupport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fa.set_support_user_interface();
            }
        });

        bSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection connection = DataBaseConnection.database_connection();
                    System.out.println("Database connected!");

                    tickets_full = new Vector<>();
                    tickets_database = new Vector<>();

                    String query = "SELECT f.* FROM flight f JOIN airportdetails a1 ON f.idAirportdetailsDeparture = a1.idAirportdetails JOIN airportdetails a2 ON f.idAirportdetailsArrival = a2.idAirportdetails WHERE a1.nameAirport=? AND a2.nameAirport=? AND f.DepartureData=?";

                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, textDepart.getText());
                    statement.setString(2, textArrival.getText());
                    statement.setString(3, textData.getText());

                    ResultSet rs = statement.executeQuery();
                    while (rs.next()) {
                        String query2 = "SELECT name FROM plane WHERE idPlane=?";
                        PreparedStatement statement2 = connection.prepareStatement(query2);
                        statement2.setString(1, rs.getString("idPlane"));
                        ResultSet rs2 = statement2.executeQuery();
                        while(rs2.next()) {
                            Random rand = new Random();
                            int price = 0;

                            if(cbType.getSelectedItem().toString().equals("Full ticket")) {
                                price = rand.nextInt(100) + 200;
                            }
                            else if(cbType.getSelectedItem().toString().equals("Student discount")) {
                                price = rand.nextInt(50) + 150;
                            }
                            else if(cbType.getSelectedItem().toString().equals("Retiree discount")) {
                                price = rand.nextInt(30) + 70;
                            }

                            String ticket1 = "IdFlight: " + rs.getString("idFlight") + "   Plane: " + rs2.getString(1) + "   Depart: " +
                                    textDepart.getText() + "   Arrival: " + textArrival.getText() + "   Departure Data: " +
                                    rs.getString("DepartureData") + "   Arrival Data: " + rs.getString("ArrivalData") +
                                    "   Price: " + price + "   Type: " + cbType.getSelectedItem().toString();

                            String ticket2 = rs.getString("idFlight") + " " + cbType.getSelectedItem().toString() + " " + price;

                            tickets_full.add(ticket1);
                            tickets_database.add(ticket2);
                        }
                    }

                    textArrival.setText("");
                    textDepart.setText("");
                    cbType.setSelectedIndex(1);
                    textData.setText("");

                    if(tickets_full.size() == 0) {
                        lresult.setText("No tickets were found!");
                    } else if(tickets_full.size() == 1) {
                        lresult.setText("1 ticket was found!");
                    } else if(tickets_full.size() > 1) {
                        lresult.setText(tickets_full.size() + " tickets were found!");
                    }


                    statement.close();
                    connection.close();
                    System.out.println("Connection closed!");

                } catch (SQLException ex) {
                    throw new IllegalStateException("Cannot connect the database!", ex);
                }
            }
        });

        bSeeTickets.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultListModel<String> listModel = new DefaultListModel<>();
                for (String ticket : tickets_full) {
                    listModel.addElement(ticket);
                }
                tickets.setModel(listModel);

            }
        });

        bReserve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = tickets.getSelectedIndex();

                String info_ticket = tickets_database.get(id);

                String words[] = info_ticket.split(" ");

                int idFlight = Integer.parseInt(words[0]);

                try {
                    Connection connection = DataBaseConnection.database_connection();
                    System.out.println("Database connected!");

                    Statement statement = connection.createStatement();
                    String query = "SELECT MAX(idTicket) FROM ticket ";
                    ResultSet rs = statement.executeQuery(query);

                    int idTicket = 1;
                    if(rs.next()) {
                        idTicket = rs.getInt(1);
                    }
                    idTicket++;

                    String type = words[1] + " " + words[2];
                    String query2 = "INSERT INTO ticket VALUES ('"+idTicket+"','"+idFlight+"','"+DataBaseConnection.email_connected+"','"+type+"','"+Double.parseDouble(words[3])+"','reserved')";

                    statement.executeUpdate(query2);

                    statement.close();
                    connection.close();
                    System.out.println("Connection closed!");

                } catch (SQLException ex) {
                    throw new IllegalStateException("Cannot connect the database!", ex);
                }
            }
        });
    }
}
