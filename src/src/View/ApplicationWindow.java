package View;

import Model.DataBaseConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;


public class ApplicationWindow extends JFrame {
    private CardLayout cardLayout;
    private JPanel container_interfaces;
    private LoginScreen login_screen;
    private HomepageUser homepageUser;
    private ProfileUser profileUser;
    private SupportUser supportUser;
    private AdminInterface adminInterface;
    private EmployeeInterface employeeInterface;

    public ApplicationWindow() {
        this.setTitle("FLIGHT MANAGEMENT");

        ImageIcon icon = new ImageIcon("src//Resources//logo.jpg");
        this.setIconImage(icon.getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH));

        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setBounds(0, 0, 1540, 830);

        cardLayout = new CardLayout();
        container_interfaces = new JPanel(cardLayout);


        this.add(container_interfaces);

        login_screen = new LoginScreen(this.getWidth(), this.getHeight(), this);
        container_interfaces.add(login_screen, "panel_login");

        homepageUser = new HomepageUser(this.getWidth(), this.getHeight(), this);
        container_interfaces.add(homepageUser, "panel_hpuser");

        profileUser = new ProfileUser(this.getWidth(), this.getHeight(), this);
        container_interfaces.add(profileUser, "panel_profuser");

        supportUser = new SupportUser(this.getWidth(), this.getHeight(), this);
        container_interfaces.add(supportUser, "panel_supuser");

        adminInterface = new AdminInterface(this.getWidth(), this.getHeight(), this);
        container_interfaces.add(adminInterface, "panel_adminui");

        employeeInterface = new EmployeeInterface(this.getWidth(), this.getHeight(), this);
        container_interfaces.add(employeeInterface, "panel_employeeui");

        set_login_interface();

        //login_user_successful();

        //login_admin_successful();
        //login_employee_successful();

        this.setVisible(true);
    }

    public void stop() {
        this.dispose();
    }

    public void set_login_interface() {
        cardLayout.show(container_interfaces, "panel_login");
    }

    public void login_user_successful() {
        cardLayout.show(container_interfaces, "panel_hpuser");
    }


    public void login_admin_successful() {
        cardLayout.show(container_interfaces, "panel_adminui");
    }

    public void login_employee_successful() {
        cardLayout.show(container_interfaces, "panel_employeeui");
    }

    public void set_profile_interface() {
        profileUser.set_infos();
        cardLayout.show(container_interfaces, "panel_profuser");
    }

    public void set_support_user_interface() {
        cardLayout.show(container_interfaces, "panel_supuser");
    }

    public boolean test_login(String email, String password) {
        boolean account_found = false;
        try {
            Connection connection = DataBaseConnection.database_connection();
            System.out.println("Database connected!");

            Statement statement = connection.createStatement();
            String query = "SELECT * FROM user ";
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                if (email.equals(rs.getString("email"))) {
                    if (password.hashCode() == (rs.getInt("password"))) {
                        account_found = true;
                    }
                }
            }


            statement.close();
            connection.close();
            System.out.println("Connection closed!");


        } catch (SQLException ex) {
            throw new IllegalStateException("Cannot connect the database!", ex);
        }

        return account_found;
    }

    public boolean test_register(String email, String firstName, String lastName, String address, String mobileNo, String password, int type) {
        boolean emailExists = false;
        boolean succesRegister = false;

        try {
            Connection connection = DataBaseConnection.database_connection();
            System.out.println("Database connected!");

            Statement statement = connection.createStatement();
            String query = "SELECT * FROM user ";
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                if (email.equals(rs.getString("email"))) {
                    emailExists = true;
                    break;
                }
            }
            if(emailExists == false) {
                statement.executeUpdate("INSERT INTO user VALUES ('"+email+"','"+firstName+"','"+lastName+"','"+address+"','"+mobileNo+"','"+password.hashCode()+"','"+type+"')");
                succesRegister = true;
            }
            statement.close();
            connection.close();
            System.out.println("Connection closed!");

        } catch (SQLException ex) {
            throw new IllegalStateException("Cannot connect the database!", ex);
        }

        return succesRegister;
    }


    public boolean test_add_company(String nameCompany) {
        boolean successAdd = false;
        try {
            Connection connection = DataBaseConnection.database_connection();
            System.out.println("Database connected!");


            Statement statement = connection.createStatement();

            String query = "SELECT MAX(idCompany) FROM company ";
            ResultSet rs = statement.executeQuery(query);

            int idCompany = -1;
            if(rs.next()) {
                idCompany = rs.getInt(1);
            }
            idCompany++;

            int rows = statement.executeUpdate("INSERT INTO company VALUES ('"+idCompany+"','"+nameCompany+"')");
            if(rows != 0) {
                successAdd = true;
            }

            statement.close();
            connection.close();
            System.out.println("Connection closed!");
        } catch (SQLException ex) {
            throw new IllegalStateException("Cannot connect the database!", ex);
        }

        return successAdd;
    }

    public boolean test_delete_company(String name) {
        boolean successDelete = false;
        try {
            Connection connection = DataBaseConnection.database_connection();
            System.out.println("Database connected!");

            String query = "DELETE FROM company WHERE name = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            int rows = statement.executeUpdate();
            if(rows != 0) {
                successDelete = true;
            }

            statement.close();
            connection.close();
            System.out.println("Connection closed!");

        } catch (SQLException ex) {
            throw new IllegalStateException("Cannot connect the database!", ex);
        }

        return successDelete;
    }

    public boolean test_add_plane(String nameCompany, int capacity, String namePlane) {
        boolean successAdd = false;
        try {
            Connection connection = DataBaseConnection.database_connection();
            System.out.println("Database connected!");

            Statement statement = connection.createStatement();

            String query = "SELECT MAX(idPlane) FROM plane ";
            ResultSet rs = statement.executeQuery(query);

            int idPlane = -1;
            if(rs.next()) {
                idPlane = rs.getInt(1);
            }
            idPlane++;

            String idCompany = null;
            String query2 = "SELECT idCompany FROM company WHERE name = ? ";
            PreparedStatement statement2 = connection.prepareStatement(query2);
            statement2.setString(1, nameCompany);
            ResultSet resultSet = statement2.executeQuery();
            if (resultSet.next()) {
                idCompany = resultSet.getString("idCompany");
            }

            if(idCompany == null) {
                return false;
            }
            int rows = statement.executeUpdate("INSERT INTO plane VALUES ('"+idPlane+"','"+Integer.parseInt(idCompany)+"','"+capacity+"','"+namePlane+"')");
            if(rows != 0) {
                successAdd = true;
            }

        } catch (SQLException ex) {
            throw new IllegalStateException("Cannot connect the database!", ex);
        }

        return successAdd;
    }

    public boolean test_delete_plane(String namePlane) {
        boolean successDelete = false;
        try {
            Connection connection = DataBaseConnection.database_connection();
            System.out.println("Database connected!");

            String query = "DELETE FROM plane WHERE name = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, namePlane);
            int rows = statement.executeUpdate();
            if(rows != 0) {
                successDelete = true;
            }

            statement.close();
            connection.close();
            System.out.println("Connection closed!");

        } catch (SQLException ex) {
            throw new IllegalStateException("Cannot connect the database!", ex);
        }

        return successDelete;
    }

    public boolean test_add_airport(String nameAirport, String locationAirport) {
        boolean addSuccess = false;
        try {
            Connection connection = DataBaseConnection.database_connection();
            System.out.println("Database connected!");


            Statement statement = connection.createStatement();

            String query = "SELECT MAX(idAirportdetails) FROM airportdetails ";
            ResultSet rs = statement.executeQuery(query);

            int idAirport = -1;
            if(rs.next()) {
                idAirport = rs.getInt(1);
            }
            idAirport++;

            int rows = statement.executeUpdate("INSERT INTO airportdetails VALUES ('"+idAirport+"','"+nameAirport+"','"+locationAirport+"')");
            if(rows != 0) {
                addSuccess = true;
            }

        } catch (SQLException ex) {
            throw new IllegalStateException("Cannot connect the database!", ex);
        }

        return addSuccess;
    }

    public boolean test_delete_airport(String nameAirport) {
        boolean succesDelete = false;
        try {
            Connection connection = DataBaseConnection.database_connection();
            System.out.println("Database connected!");

            String query = "DELETE FROM airportdetails WHERE nameAirport = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, nameAirport);
            int rows = statement.executeUpdate();

            if(rows != 0) {
                succesDelete = true;
            }

            statement.close();
            connection.close();
            System.out.println("Connection closed!");

        } catch (SQLException ex) {
            throw new IllegalStateException("Cannot connect the database!", ex);
        }

        return succesDelete;
    }

    public boolean test_add_flight(String namePlane, String nameAirportDeparture, String nameAirportArrival, String arrivalData, String departureData) {
        boolean addSuccess = false;
        try {
            Connection connection = DataBaseConnection.database_connection();
            System.out.println("Database connected!");


            Statement statement = connection.createStatement();

            String query = "SELECT MAX(idFlight) FROM flight ";
            ResultSet rs = statement.executeQuery(query);

            int idFlight = -1;
            if(rs.next()) {
                idFlight = rs.getInt(1);
            }
            idFlight++;

            String idPlane = null;
            String query2 = "SELECT idPlane FROM plane WHERE name = ? ";
            PreparedStatement statement2 = connection.prepareStatement(query2);
            statement2.setString(1, namePlane);
            ResultSet rs2 = statement2.executeQuery();
            if (rs2.next()) {
                idPlane = rs2.getString("idPlane");
            }

            String idDepartureAirport = null;
            String query3 = "SELECT idAirportdetails FROM airportdetails WHERE nameAirport = ? ";
            PreparedStatement statement3 = connection.prepareStatement(query3);
            statement3.setString(1, nameAirportDeparture);
            ResultSet rs3 = statement3.executeQuery();
            if (rs3.next()) {
                idDepartureAirport = rs3.getString("idAirportdetails");
            }

            String idArrivalAirport = null;
            String query4 = "SELECT idAirportdetails FROM airportdetails WHERE nameAirport = ? ";
            PreparedStatement statement4 = connection.prepareStatement(query4);
            statement4.setString(1, nameAirportArrival);
            ResultSet rs4 = statement4.executeQuery();
            if (rs4.next()) {
                idArrivalAirport = rs4.getString("idAirportdetails");
            }

            if(idPlane == null || idArrivalAirport == null || idDepartureAirport == null) {
                return false;
            }
            int rows = statement.executeUpdate("INSERT INTO flight VALUES ('"+idFlight+"','"+idPlane+"','"+idDepartureAirport+"','"+idArrivalAirport+"','"+departureData+"','"+arrivalData+"')");
            if(rows != 0) {
                addSuccess = true;
            }

        } catch (SQLException ex) {
            throw new IllegalStateException("Cannot connect the database!", ex);
        }

        return addSuccess;
    }

    public boolean test_add_employee(String email, String firstName, String lastName, String password, String mobileNo, float salary) {
        boolean addSucces = false;
        try {
            Connection connection = DataBaseConnection.database_connection();
            System.out.println("Database connected!");

            Statement statement = connection.createStatement();
            String query = "SELECT * FROM employee ";
            ResultSet rs = statement.executeQuery(query);
            boolean email_exists = false;
            while (rs.next()) {
                if (email.equals(rs.getString("email"))) {
                    email_exists = true;
                    break;
                }
            }
            if(email_exists == false) {
                int rows = statement.executeUpdate("INSERT INTO employee VALUES ('"+email+"','"+firstName+"','"+lastName+"','"+password.hashCode()+"','"+mobileNo+"','"+salary+"')");
                if(rows != 0) {
                    addSucces = true;
                }
            }
            statement.close();
            connection.close();
            System.out.println("Connection closed!");

        } catch (SQLException ex) {
            throw new IllegalStateException("Cannot connect the database!", ex);
        }

        return addSucces;
    }

    public boolean test_delete_employee(String email) {
        boolean deleteSucces = false;
        try {
            Connection connection = DataBaseConnection.database_connection();
            System.out.println("Database connected!");

            String query = "DELETE FROM employee WHERE email = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            int rows = statement.executeUpdate();
            if (rows != 0) {
                deleteSucces = true;
            }

            statement.close();
            connection.close();
            System.out.println("Connection closed!");

        } catch (SQLException ex) {
            throw new IllegalStateException("Cannot connect the database!", ex);
        }
        return deleteSucces;
    }
}
