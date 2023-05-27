package View;

import Model.DataBaseConnection;

import javax.swing.*;
import java.awt.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.Vector;

public class EmployeeInterface extends JPanel {
    private int width, height;
    private ApplicationWindow fa;

    private boolean confirm_logout_open = false;

    JButton addCompanyButton, deleteCompanyButton, addPlaneButton, deletePlaneButton,
            addAirportDetailsButton, deleteAirportDetailsButton, addFlightButton,
            deleteFlightButton, updateFlightButton, generateReportsButton, bLogout, binteriorConfirm,
            bSupport, saveCompanyButton, savePlaneButton, removeCompanyButton, removePlaneButton, bSubmit,
            saveAirportButton, removeAirportButton, saveFlightButton, upFlightButton, removeFlightButton;


    JLabel lconfirmlogout, lnameCompany, lCompany, lAddCompany, lDeleteCompany, lAddPlane, lcapacityPlane, lnamePlane, lDeletePlane,
            lPlane, lAddAirport, llocationAirport, lNameAirport , lAirport, lDeleteAirport, lAddFlight, lselectNameCompany, lselectPlane,
            lselectDepartureAirport, lselectArrivalAirport, lDeleteFlight, ldepartureDateFlight, larrivalDateFlight, lFlight, lUpdateFlight,
            lselectUpFlight, lupdateDepartureDate, lupdateArrivalDate, lsolve;
    JComboBox<String> cbDeteteCompany, cbDetetePlane, cbUpdatePlane, cbDeteteAirport, cbUpdateAirport, cbDeteteFlight,
            cbUpdateFlight, cbSelectCompany, cbSelectPlane, cbSelectDepartureAirport, cbSelectArrivalAirport;
    JTextField txtnameCompany, txtcapacityPlane, txtnamePlane, txtlocationAirport,
            txtNameAirport, txtdepartureDataFlight, txtarrivalDataFlight,
            txtupdateDepartureDate, txtupdateArrivalDate, txtsolve;

    JList<String> listQuestions;
    Vector<String> companies;
    Vector<String> planes;
    Vector<String> airports;
    Vector<String> flights;

    public EmployeeInterface(int width, int height, ApplicationWindow fa) {
        this.width = width;
        this.height = height;
        this.fa = fa;

        this.setBounds(0, 0, this.width, this.height);
        this.setOpaque(true);

        this.setLayout(null);

        set_infos();

        set_navbar();
        set_logout_interface();
        set_addCompany_interface();
        set_deleteCompany_interface();
        set_addPlane_interface();
        set_deletePlane_interface();
        //set_updatePlane_interface();
        set_addAirport_interface();
        set_deleteAirport_interface();
        //set_updateAirport_interface();
        set_addFlight_interface();
        set_deleteFlight_interface();
        set_updateFlight_interface();
        set_support_interface();

        add_actions_buttons();
        add_save_remove_update_buttons_action();
    }
    private void set_navbar() {
        addCompanyButton = new JButton("AddCompany");
        addCompanyButton.setBounds(0, 20, 160, 40);

        deleteCompanyButton = new JButton("DeleteCompany");
        deleteCompanyButton.setBounds(0, 70, 160, 40);

        addPlaneButton = new JButton("AddPlane");
        addPlaneButton.setBounds(0, 145, 160, 40);

        deletePlaneButton = new JButton("DeletePlane");
        deletePlaneButton.setBounds(0, 195, 160, 40);

        addAirportDetailsButton = new JButton("AddAirportDetails");
        addAirportDetailsButton.setBounds(0, 270, 160, 40);

        deleteAirportDetailsButton = new JButton("DeleteAirportDetails");
        deleteAirportDetailsButton.setBounds(0, 320, 160, 40);

        addFlightButton = new JButton("AddFlight");
        addFlightButton.setBounds(0, 400, 160, 40);

        deleteFlightButton = new JButton("DeleteFlight");
        deleteFlightButton.setBounds(0, 450, 160, 40);

        updateFlightButton = new JButton("UpdateFlight");
        updateFlightButton.setBounds(0, 500, 160, 40);

        generateReportsButton = new JButton("GenerateReports");
        generateReportsButton.setBounds(0, 570, 160, 40);

        bSupport = new JButton("Support");
        bSupport.setBounds(0, 620, 160, 40);

        this.add(addCompanyButton);
        this.add(deleteCompanyButton);
        this.add(addPlaneButton);
        this.add(deletePlaneButton);
        this.add(addAirportDetailsButton);
        this.add(deleteAirportDetailsButton);
        this.add(addFlightButton);
        this.add(deleteFlightButton);
        this.add(updateFlightButton);
        this.add(generateReportsButton);
        this.add(bSupport);
    }

    private void set_logout_interface() {
        bLogout = new JButton("Logout");
        bLogout.setBounds(20, 720, 100, 30);
        this.add(bLogout);

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

    private void set_addCompany_interface() {
        lAddCompany = new JLabel("Add Company");
        lAddCompany.setBounds(500, 170, 100, 20);

        lnameCompany = new JLabel("Name: ");
        lnameCompany.setBounds(500, 200, 100, 20);

        txtnameCompany = new JTextField();
        txtnameCompany.setBounds(600, 200, 100, 20);

        saveCompanyButton = new JButton("Add");
        saveCompanyButton.setBounds(500, 240, 100, 20);

        this.add(lAddCompany);
        this.add(lnameCompany);
        this.add(txtnameCompany);
        this.add(saveCompanyButton);

        show_addCompany_interface(false);
    }

    private void show_addCompany_interface(boolean state) {
        lAddCompany.setVisible(state);
        lnameCompany.setVisible(state);
        txtnameCompany.setVisible(state);
        saveCompanyButton.setVisible(state);
    }

    private void set_deleteCompany_interface() {
        lDeleteCompany = new JLabel("Delete Company");
        lDeleteCompany.setBounds(500, 170, 100, 20);

        lCompany = new JLabel("Company: ");
        lCompany.setBounds(500, 200, 100, 20);

        cbDeteteCompany = new JComboBox<String>(new DefaultComboBoxModel<>(companies));
        cbDeteteCompany.setBounds(600, 200, 100, 20);

        removeCompanyButton = new JButton("Delete");
        removeCompanyButton.setBounds(500, 240, 100, 20);

        this.add(lDeleteCompany);
        this.add(lCompany);
        this.add(cbDeteteCompany);
        this.add(removeCompanyButton);

        show_deleteCompany_interface(false);
    }

    private void show_deleteCompany_interface(boolean state) {
        lDeleteCompany.setVisible(state);
        lCompany.setVisible(state);
        cbDeteteCompany.setVisible(state);
        removeCompanyButton.setVisible(state);
    }

    private void set_addPlane_interface() {
        lAddPlane = new JLabel("Add Plane");
        lAddPlane.setBounds(500, 140, 100, 20);

        lselectNameCompany = new JLabel("Company: ");
        lselectNameCompany.setBounds(500, 170, 100, 20);

        cbSelectCompany = new JComboBox<>(new DefaultComboBoxModel<>(companies));
        cbSelectCompany.setBounds(600, 170, 100, 20);

        lcapacityPlane = new JLabel("Capacity: ");
        lcapacityPlane.setBounds(500, 200, 100, 20);

        txtcapacityPlane = new JTextField();
        txtcapacityPlane.setBounds(600, 200, 100, 20);

        lnamePlane = new JLabel("Name: ");
        lnamePlane.setBounds(500, 230, 100, 20);

        txtnamePlane = new JTextField();
        txtnamePlane.setBounds(600, 230, 100, 20);

        savePlaneButton = new JButton("Add");
        savePlaneButton.setBounds(500, 270, 100, 20);

        this.add(lAddPlane);
        this.add(lcapacityPlane);
        this.add(txtcapacityPlane);
        this.add(lnamePlane);
        this.add(txtnamePlane);
        this.add(savePlaneButton);
        this.add(cbSelectCompany);
        this.add(lselectNameCompany);

        show_addPlane_interface(false);
    }

    private void show_addPlane_interface(boolean state) {
        lAddPlane.setVisible(state);
        lcapacityPlane.setVisible(state);
        txtcapacityPlane.setVisible(state);
        lnamePlane.setVisible(state);
        txtnamePlane.setVisible(state);
        savePlaneButton.setVisible(state);
        cbSelectCompany.setVisible(state);
        lselectNameCompany.setVisible(state);
    }

    private void set_deletePlane_interface() {
        lDeletePlane = new JLabel("Delete Plane");
        lDeletePlane.setBounds(500, 170, 100, 20);

        lPlane = new JLabel("Plane: ");
        lPlane.setBounds(500, 200, 100, 20);

        cbDetetePlane = new JComboBox<String>(new DefaultComboBoxModel<>(planes));
        cbDetetePlane.setBounds(600, 200, 100, 20);

        removePlaneButton = new JButton("Delete");
        removePlaneButton.setBounds(500, 240, 100, 20);

        this.add(lDeletePlane);
        this.add(lPlane);
        this.add(cbDetetePlane);
        this.add(removePlaneButton);

        show_deletePlane_interface(false);
    }

    private void show_deletePlane_interface(boolean state) {
        lDeletePlane.setVisible(state);
        lPlane.setVisible(state);
        cbDetetePlane.setVisible(state);
        removePlaneButton.setVisible(state);
    }

    /*private void set_updatePlane_interface() {
        lUpdatePlane = new JLabel("Update Plane");
        lUpdatePlane.setBounds(500, 170, 100, 20);

        lselectUpPlane = new JLabel("Plane: ");
        lselectUpPlane.setBounds(500, 200, 100, 20);

        cbUpdatePlane = new JComboBox<String>(new DefaultComboBoxModel<>(planes));
        cbUpdatePlane.setBounds(600, 200, 100, 20);

        lupdateCapacityPlane = new JLabel("Capacity: ");
        lupdateCapacityPlane.setBounds(500, 230, 100, 20);

        txtupdateCapacityPlane = new JTextField();
        txtupdateCapacityPlane.setBounds(600, 230, 100, 20);

        lupdateNamePlane = new JLabel("Name: ");
        lupdateNamePlane.setBounds(500, 260, 100, 20);

        txtupdateNamePlane = new JTextField();
        txtupdateNamePlane.setBounds(600, 260, 100, 20);

        this.add(lUpdatePlane);
        this.add(lselectUpPlane);
        this.add(cbUpdatePlane);
        this.add(lupdateCapacityPlane);
        this.add(txtupdateCapacityPlane);
        this.add(lupdateNamePlane);
        this.add(txtupdateNamePlane);

        show_updatePlane_interface(false);
    }

    private void show_updatePlane_interface(boolean state) {
        lUpdatePlane.setVisible(state);
        lselectUpPlane.setVisible(state);
        cbUpdatePlane.setVisible(state);
        lupdateCapacityPlane.setVisible(state);
        txtupdateCapacityPlane.setVisible(state);
        lupdateNamePlane.setVisible(state);
        txtupdateNamePlane.setVisible(state);
    }*/

    private void set_addAirport_interface() {
        lAddAirport = new JLabel("Add Airport");
        lAddAirport.setBounds(500, 170, 100, 20);

        llocationAirport = new JLabel("Location: ");
        llocationAirport.setBounds(500, 200, 100, 20);

        txtlocationAirport = new JTextField();
        txtlocationAirport.setBounds(600, 200, 100, 20);

        lNameAirport = new JLabel("Name: ");
        lNameAirport.setBounds(500, 230, 100, 20);

        txtNameAirport = new JTextField();
        txtNameAirport.setBounds(600, 230, 100, 20);

        saveAirportButton = new JButton("Add");
        saveAirportButton.setBounds(500, 270, 100, 20);

        this.add(lAddAirport);
        this.add(llocationAirport);
        this.add(txtlocationAirport);
        this.add(lNameAirport);
        this.add(txtNameAirport);
        this.add(saveAirportButton);

        show_addAirport_interface(false);
    }

    private void show_addAirport_interface(boolean state) {
        lAddAirport.setVisible(state);
        llocationAirport.setVisible(state);
        txtlocationAirport.setVisible(state);
        lNameAirport.setVisible(state);
        txtNameAirport.setVisible(state);
        saveAirportButton.setVisible(state);
    }

    private void set_deleteAirport_interface() {
        lDeleteAirport = new JLabel("Delete Airport");
        lDeleteAirport.setBounds(500, 170, 100, 20);

        lAirport = new JLabel("Airport: ");
        lAirport.setBounds(500, 200, 100, 20);

        cbDeteteAirport = new JComboBox<String>(new DefaultComboBoxModel<>(airports));
        cbDeteteAirport.setBounds(600, 200, 100, 20);

        removeAirportButton = new JButton("Delete");
        removeAirportButton.setBounds(500, 240, 100, 20);

        this.add(lDeleteAirport);
        this.add(lAirport);
        this.add(cbDeteteAirport);
        this.add(removeAirportButton);

        show_deleteAirport_interface(false);
    }

    private void show_deleteAirport_interface(boolean state) {
        lDeleteAirport.setVisible(state);
        lAirport.setVisible(state);
        cbDeteteAirport.setVisible(state);
        removeAirportButton.setVisible(state);
    }

    /*private void set_updateAirport_interface() {
        lUpdateAirport = new JLabel("Update Airport");
        lUpdateAirport.setBounds(500, 170, 100, 20);

        lselectUpAirport = new JLabel("Airport: ");
        lselectUpAirport.setBounds(500, 200, 100, 20);

        cbUpdateAirport = new JComboBox<String>(new DefaultComboBoxModel<>(airports));
        cbUpdateAirport.setBounds(600, 200, 100, 20);

        lupdateLocationAirport = new JLabel("Location: ");
        lupdateLocationAirport.setBounds(500, 230, 100, 20);

        txtupdateLocationAirport = new JTextField();
        txtupdateLocationAirport.setBounds(600, 230, 100, 20);

        lupdateNameAirport = new JLabel("Name: ");
        lupdateNameAirport.setBounds(500, 260, 100, 20);

        txtupdateNameAirport = new JTextField();
        txtupdateNameAirport.setBounds(600, 260, 100, 20);

        this.add(lUpdateAirport);
        this.add(lselectUpAirport);
        this.add(cbUpdateAirport);
        this.add(lupdateNameAirport);
        this.add(txtupdateNameAirport);
        this.add(lupdateLocationAirport);
        this.add(txtupdateLocationAirport);

        show_updateAirport_interface(false);
    }

    private void show_updateAirport_interface(boolean state) {
        lUpdateAirport.setVisible(state);
        lselectUpAirport.setVisible(state);
        cbUpdateAirport.setVisible(state);
        lupdateNameAirport.setVisible(state);
        txtupdateNameAirport.setVisible(state);
        lupdateLocationAirport.setVisible(state);
        txtupdateLocationAirport.setVisible(state);
    }*/

    private void set_addFlight_interface() {
        lAddFlight = new JLabel("Add Flight");
        lAddFlight.setBounds(500, 170, 100, 20);

        lselectPlane = new JLabel("Plane: ");
        lselectPlane.setBounds(500, 200, 120, 20);
        cbSelectPlane = new JComboBox<>(new DefaultComboBoxModel<>(planes));
        cbSelectPlane.setBounds(620, 200, 120, 20);

        lselectDepartureAirport = new JLabel("Departure airport: ");
        lselectDepartureAirport.setBounds(500, 230, 120, 20);
        cbSelectDepartureAirport = new JComboBox<>(new DefaultComboBoxModel<>(airports));
        cbSelectDepartureAirport.setBounds(620, 230, 120, 20);

        lselectArrivalAirport = new JLabel("Arrival airport: ");
        lselectArrivalAirport.setBounds(500, 260, 120, 20);
        cbSelectArrivalAirport = new JComboBox<>(new DefaultComboBoxModel<>(airports));
        cbSelectArrivalAirport.setBounds(620, 260, 120, 20);

        ldepartureDateFlight = new JLabel("Departure Date: ");
        ldepartureDateFlight.setBounds(500, 290, 120, 20);

        txtdepartureDataFlight = new JTextField();
        txtdepartureDataFlight.setBounds(600, 290, 120, 20);

        larrivalDateFlight = new JLabel("Arrival Date: ");
        larrivalDateFlight.setBounds(500, 320, 120, 20);

        txtarrivalDataFlight = new JTextField();
        txtarrivalDataFlight.setBounds(600, 320, 120, 20);

        saveFlightButton = new JButton("Add");
        saveFlightButton.setBounds(500, 360, 100, 20);

        this.add(lAddFlight);
        this.add(ldepartureDateFlight);
        this.add(txtdepartureDataFlight);
        this.add(larrivalDateFlight);
        this.add(txtarrivalDataFlight);
        this.add(saveFlightButton);
        this.add(cbSelectPlane);
        this.add(cbSelectDepartureAirport);
        this.add(cbSelectArrivalAirport);
        this.add(lselectPlane);
        this.add(lselectDepartureAirport);
        this.add(lselectArrivalAirport);

        show_addFlight_interface(false);
    }

    private void show_addFlight_interface(boolean state) {
        lAddFlight.setVisible(state);
        ldepartureDateFlight.setVisible(state);
        txtdepartureDataFlight.setVisible(state);
        larrivalDateFlight.setVisible(state);
        txtarrivalDataFlight.setVisible(state);
        saveFlightButton.setVisible(state);
        cbSelectPlane.setVisible(state);
        cbSelectDepartureAirport.setVisible(state);
        cbSelectArrivalAirport.setVisible(state);
        lselectPlane.setVisible(state);
        lselectDepartureAirport.setVisible(state);
        lselectArrivalAirport.setVisible(state);
    }

    private void set_deleteFlight_interface() {
        lDeleteFlight = new JLabel("Delete Flight");
        lDeleteFlight.setBounds(500, 170, 100, 20);

        lFlight = new JLabel("Flight: ");
        lFlight.setBounds(500, 200, 100, 20);

        cbDeteteFlight = new JComboBox<String>(new DefaultComboBoxModel<>(flights));
        cbDeteteFlight.setBounds(600, 200, 300, 20);

        removeFlightButton = new JButton("Delete");
        removeFlightButton.setBounds(500, 240, 100, 20);

        this.add(lDeleteFlight);
        this.add(lFlight);
        this.add(cbDeteteFlight);
        this.add(removeFlightButton);

        show_deleteFlight_interface(false);
    }

    private void show_deleteFlight_interface(boolean state) {
        lDeleteFlight.setVisible(state);
        lFlight.setVisible(state);
        cbDeteteFlight.setVisible(state);
        removeFlightButton.setVisible(state);
    }

    private void set_updateFlight_interface() {
        lUpdateFlight = new JLabel("Update Flight");
        lUpdateFlight.setBounds(500, 170, 100, 20);
        lselectUpFlight = new JLabel("Flight: ");
        lselectUpFlight.setBounds(500, 200, 100, 20);

        cbUpdateFlight = new JComboBox<String>(new DefaultComboBoxModel<>(flights));
        cbUpdateFlight.setBounds(600, 200, 300, 20);

        lupdateDepartureDate = new JLabel("Departure Date: ");
        lupdateDepartureDate.setBounds(500, 230, 100, 20);

        txtupdateDepartureDate = new JTextField();
        txtupdateDepartureDate.setBounds(600, 230, 100, 20);

        lupdateArrivalDate = new JLabel("Arrival Date: ");
        lupdateArrivalDate.setBounds(500, 260, 100, 20);

        txtupdateArrivalDate = new JTextField();
        txtupdateArrivalDate.setBounds(600, 260, 100, 20);

        upFlightButton = new JButton("Update");
        upFlightButton.setBounds(500, 300, 100, 20);

        this.add(lUpdateFlight);
        this.add(lselectUpFlight);
        this.add(cbUpdateFlight);
        this.add(lupdateDepartureDate);
        this.add(txtupdateDepartureDate);
        this.add(lupdateArrivalDate);
        this.add(txtupdateArrivalDate);
        this.add(upFlightButton);

        show_updateFlight_interface(false);
    }

    private void show_updateFlight_interface(boolean state) {
        lUpdateFlight.setVisible(state);
        lselectUpFlight.setVisible(state);
        cbUpdateFlight.setVisible(state);
        lupdateDepartureDate.setVisible(state);
        txtupdateDepartureDate.setVisible(state);
        lupdateArrivalDate.setVisible(state);
        txtupdateArrivalDate.setVisible(state);
        upFlightButton.setVisible(state);
    }

    private void set_support_interface() {
        listQuestions = new JList<String>();
        listQuestions.setBounds(300, 50, 1050, 450);

        lsolve = new JLabel("Solve: ");
        lsolve.setBounds(300, 550, 100, 30);

        txtsolve = new JTextField();
        txtsolve.setBounds(400, 550, 800, 30);

        bSubmit = new JButton("Submit");
        bSubmit.setBounds(1230, 550, 100, 30);

        this.add(listQuestions);
        this.add(lsolve);
        this.add(txtsolve);
        this.add(bSubmit);
        show_support_interface(false);
    }

    private void show_support_interface(boolean state) {
        listQuestions.setVisible(state);
        lsolve.setVisible(state);
        txtsolve.setVisible(state);
        bSubmit.setVisible(state);
    }

    private void add_actions_buttons() {
        addCompanyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                show_addCompany_interface(true);
                show_deleteCompany_interface(false);
                show_addPlane_interface(false);
                show_deletePlane_interface(false);
                //show_updatePlane_interface(false);
                show_addAirport_interface(false);
                show_deleteAirport_interface(false);
                //show_updateAirport_interface(false);
                show_addFlight_interface(false);
                show_deleteFlight_interface(false);
                show_updateFlight_interface(false);
                show_support_interface(false);
            }
        });

        deleteCompanyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                show_addCompany_interface(false);
                show_deleteCompany_interface(true);
                show_addPlane_interface(false);
                show_deletePlane_interface(false);
                //show_updatePlane_interface(false);
                show_addAirport_interface(false);
                show_deleteAirport_interface(false);
                //show_updateAirport_interface(false);
                show_addFlight_interface(false);
                show_deleteFlight_interface(false);
                show_updateFlight_interface(false);
                show_support_interface(false);
            }
        });


        addPlaneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                show_addCompany_interface(false);
                show_deleteCompany_interface(false);
                show_addPlane_interface(true);
                show_deletePlane_interface(false);
                //show_updatePlane_interface(false);
                show_addAirport_interface(false);
                show_deleteAirport_interface(false);
                //show_updateAirport_interface(false);
                show_addFlight_interface(false);
                show_deleteFlight_interface(false);
                show_updateFlight_interface(false);
                show_support_interface(false);
            }
        });


        deletePlaneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                show_addCompany_interface(false);
                show_deleteCompany_interface(false);
                show_addPlane_interface(false);
                show_deletePlane_interface(true);
                //show_updatePlane_interface(false);
                show_addAirport_interface(false);
                show_deleteAirport_interface(false);
                //show_updateAirport_interface(false);
                show_addFlight_interface(false);
                show_deleteFlight_interface(false);
                show_updateFlight_interface(false);
                show_support_interface(false);
            }
        });

        addAirportDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                show_addCompany_interface(false);
                show_deleteCompany_interface(false);
                show_addPlane_interface(false);
                show_deletePlane_interface(false);
                //show_updatePlane_interface(false);
                show_addAirport_interface(true);
                show_deleteAirport_interface(false);
                //show_updateAirport_interface(false);
                show_addFlight_interface(false);
                show_deleteFlight_interface(false);
                show_updateFlight_interface(false);
                show_support_interface(false);
            }
        });

        deleteAirportDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                show_addCompany_interface(false);
                show_deleteCompany_interface(false);
                show_addPlane_interface(false);
                show_deletePlane_interface(false);
                //show_updatePlane_interface(false);
                show_addAirport_interface(false);
                show_deleteAirport_interface(true);
                //show_updateAirport_interface(false);
                show_addFlight_interface(false);
                show_deleteFlight_interface(false);
                show_updateFlight_interface(false);
                show_support_interface(false);
            }
        });

        addFlightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                show_addCompany_interface(false);
                show_deleteCompany_interface(false);
                show_addPlane_interface(false);
                show_deletePlane_interface(false);
                //show_updatePlane_interface(false);
                show_addAirport_interface(false);
                show_deleteAirport_interface(false);
                //show_updateAirport_interface(false);
                show_addFlight_interface(true);
                show_deleteFlight_interface(false);
                show_updateFlight_interface(false);
                show_support_interface(false);
            }
        });

        deleteFlightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                show_addCompany_interface(false);
                show_deleteCompany_interface(false);
                show_addPlane_interface(false);
                show_deletePlane_interface(false);
                //show_updatePlane_interface(false);
                show_addAirport_interface(false);
                show_deleteAirport_interface(false);
                //show_updateAirport_interface(false);
                show_addFlight_interface(false);
                show_deleteFlight_interface(true);
                show_updateFlight_interface(false);
                show_support_interface(false);
            }
        });

        updateFlightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                show_addCompany_interface(false);
                show_deleteCompany_interface(false);
                show_addPlane_interface(false);
                show_deletePlane_interface(false);
                //show_updatePlane_interface(false);
                show_addAirport_interface(false);
                show_deleteAirport_interface(false);
                //show_updateAirport_interface(false);
                show_addFlight_interface(false);
                show_deleteFlight_interface(false);
                show_updateFlight_interface(true);
                show_support_interface(false);
            }
        });

        bSupport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                show_addCompany_interface(false);
                show_deleteCompany_interface(false);
                show_addPlane_interface(false);
                show_deletePlane_interface(false);
                //show_updatePlane_interface(false);
                show_addAirport_interface(false);
                show_deleteAirport_interface(false);
                //show_updateAirport_interface(false);
                show_addFlight_interface(false);
                show_deleteFlight_interface(false);
                show_updateFlight_interface(false);
                show_support_interface(true);

                show_questions();
            }
        });


        generateReportsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                File file = new File("src//Raports//raport.txt");

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    try {
                        Connection connection = DataBaseConnection.database_connection();
                        System.out.println("Database connected!");


                        Statement statement = connection.createStatement();
                        String query = "SELECT name FROM company ";
                        ResultSet rs = statement.executeQuery(query);
                        writer.write("Companies: \n");
                        while (rs.next()) {
                            writer.write("\t" + rs.getString(1) + "\n");
                        }

                        String query2 = "SELECT name FROM plane ";
                        ResultSet rs2 = statement.executeQuery(query2);
                        writer.write("\n\nPlanes: \n");
                        while (rs2.next()) {
                            writer.write("\t" + rs2.getString(1) + "\n");
                        }

                        String query3 = "SELECT nameAirport FROM airportDetails ";
                        ResultSet rs3 = statement.executeQuery(query3);
                        writer.write("\n\nAirports: \n");
                        while (rs3.next()) {
                            writer.write("\t" + rs3.getString(1) + "\n");
                        }

                        String query4 = "SELECT DepartureData, ArrivalData FROM flight ";
                        ResultSet rs4 = statement.executeQuery(query4);
                        writer.write("\n\nFlights: \n");
                        while (rs4.next()) {
                            writer.write("\t" + rs4.getString(1));
                            writer.write("    -    " + rs4.getString(2) + "\n");
                        }

                        statement.close();
                        connection.close();
                        System.out.println("Connection closed!");
                    } catch (SQLException ex) {
                        throw new IllegalStateException("Cannot connect the database!", ex);
                    }

                } catch (IOException eWrite) {
                    System.err.println("Eroare la scrierea fisierului: " + eWrite.getMessage());
                }
            }
        });
    }

    private void set_infos(){

        companies = new Vector<String>();
        planes = new Vector<String>();
        airports = new Vector<String>();
        flights = new Vector<String>();
        try {
            Connection connection = DataBaseConnection.database_connection();
            System.out.println("Database connected!");


            Statement statement = connection.createStatement();
            String query = "SELECT name FROM company ";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                companies.add(rs.getString(1));
            }

            String query2 = "SELECT name FROM plane ";
            ResultSet rs2 = statement.executeQuery(query2);
            while (rs2.next()) {
                planes.add(rs2.getString(1));
            }

            String query3 = "SELECT nameAirport FROM airportDetails ";
            ResultSet rs3 = statement.executeQuery(query3);
            while (rs3.next()) {
                airports.add(rs3.getString(1));
            }

            String query4 = "SELECT * FROM flight ";
            ResultSet rs4 = statement.executeQuery(query4);
            while (rs4.next()) {
                flights.add(rs4.getString(1) + " "  + rs4.getString(5) + " " + rs4.getString(6));
            }

            statement.close();
            connection.close();
            System.out.println("Connection closed!");
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    private void add_save_remove_update_buttons_action() {
        saveCompanyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

                    statement.executeUpdate("INSERT INTO company VALUES ('"+idCompany+"','"+txtnameCompany.getText()+"')");

                    txtnameCompany.setText("");

                    update_all_comboboxes();

                } catch (SQLException ex) {
                    throw new IllegalStateException("Cannot connect the database!", ex);
                }
            }
        });

        removeCompanyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection connection = DataBaseConnection.database_connection();
                    System.out.println("Database connected!");

                    String name = cbDeteteCompany.getSelectedItem().toString();
                    String query = "DELETE FROM company WHERE name = ?";

                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, name);
                    statement.executeUpdate();

                    update_all_comboboxes();

                    statement.close();
                    connection.close();
                    System.out.println("Connection closed!");

                } catch (SQLException ex) {
                    throw new IllegalStateException("Cannot connect the database!", ex);
                }
            }
        });

        savePlaneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                    statement2.setString(1, cbSelectCompany.getSelectedItem().toString());
                    ResultSet resultSet = statement2.executeQuery();
                    if (resultSet.next()) {
                        idCompany = resultSet.getString("idCompany");
                    }

                    statement.executeUpdate("INSERT INTO plane VALUES ('"+idPlane+"','"+Integer.parseInt(idCompany)+"','"+Integer.parseInt(txtcapacityPlane.getText())+"','"+txtnamePlane.getText()+"')");

                    txtcapacityPlane.setText("");
                    txtnamePlane.setText("");
                    cbSelectCompany.setSelectedIndex(1);

                    update_all_comboboxes();

                } catch (SQLException ex) {
                    throw new IllegalStateException("Cannot connect the database!", ex);
                }
            }
        });

        removePlaneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection connection = DataBaseConnection.database_connection();
                    System.out.println("Database connected!");

                    String name = cbDetetePlane.getSelectedItem().toString();
                    String query = "DELETE FROM plane WHERE name = ?";

                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, name);
                    statement.executeUpdate();

                    update_all_comboboxes();

                    statement.close();
                    connection.close();
                    System.out.println("Connection closed!");

                } catch (SQLException ex) {
                    throw new IllegalStateException("Cannot connect the database!", ex);
                }
            }
        });

        saveAirportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

                    statement.executeUpdate("INSERT INTO airportdetails VALUES ('"+idAirport+"','"+txtNameAirport.getText()+"','"+txtlocationAirport.getText()+"')");

                    txtNameAirport.setText("");
                    txtlocationAirport.setText("");

                    update_all_comboboxes();


                } catch (SQLException ex) {
                    throw new IllegalStateException("Cannot connect the database!", ex);
                }
            }
        });

        removeAirportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection connection = DataBaseConnection.database_connection();
                    System.out.println("Database connected!");

                    String name = cbDeteteAirport.getSelectedItem().toString();
                    String query = "DELETE FROM airportdetails WHERE nameAirport = ?";

                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, name);
                    statement.executeUpdate();

                    update_all_comboboxes();

                    statement.close();
                    connection.close();
                    System.out.println("Connection closed!");

                } catch (SQLException ex) {
                    throw new IllegalStateException("Cannot connect the database!", ex);
                }
            }
        });

        saveFlightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                    statement2.setString(1, cbSelectPlane.getSelectedItem().toString());
                    ResultSet rs2 = statement2.executeQuery();
                    if (rs2.next()) {
                        idPlane = rs2.getString("idPlane");
                    }

                    String idDepartureAirport = null;
                    String query3 = "SELECT idAirportdetails FROM airportdetails WHERE nameAirport = ? ";
                    PreparedStatement statement3 = connection.prepareStatement(query3);
                    statement3.setString(1, cbSelectDepartureAirport.getSelectedItem().toString());
                    ResultSet rs3 = statement3.executeQuery();
                    if (rs3.next()) {
                        idDepartureAirport = rs3.getString("idAirportdetails");
                    }

                    String idArrivalAirport = null;
                    String query4 = "SELECT idAirportdetails FROM airportdetails WHERE nameAirport = ? ";
                    PreparedStatement statement4 = connection.prepareStatement(query4);
                    statement4.setString(1, cbSelectArrivalAirport.getSelectedItem().toString());
                    ResultSet rs4 = statement4.executeQuery();
                    if (rs4.next()) {
                        idArrivalAirport = rs4.getString("idAirportdetails");
                    }

                    statement.executeUpdate("INSERT INTO flight VALUES ('"+idFlight+"','"+idPlane+"','"+idDepartureAirport+"','"+idArrivalAirport+"','"+txtdepartureDataFlight.getText()+"','"+txtarrivalDataFlight.getText()+"')");

                    txtarrivalDataFlight.setText("");
                    txtdepartureDataFlight.setText("");

                    update_all_comboboxes();


                } catch (SQLException ex) {
                    throw new IllegalStateException("Cannot connect the database!", ex);
                }
            }
        });

        removeFlightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection connection = DataBaseConnection.database_connection();
                    System.out.println("Database connected!");

                    String id = cbDeteteFlight.getSelectedItem().toString();

                    String[] words = id.split(" ");

                    String query = "DELETE FROM flight WHERE idFlight = ?";

                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setInt(1, Integer.parseInt(words[0]));
                    statement.executeUpdate();

                    update_all_comboboxes();

                    statement.close();
                    connection.close();
                    System.out.println("Connection closed!");

                } catch (SQLException ex) {
                    throw new IllegalStateException("Cannot connect the database!", ex);
                }
            }
        });

        upFlightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection connection = DataBaseConnection.database_connection();
                    System.out.println("Database connected!");

                    String id = cbUpdateFlight.getSelectedItem().toString();

                    String query = "UPDATE flight SET DepartureData=?, ArrivalData=? WHERE idFlight=?";

                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, txtupdateArrivalDate.getText());
                    statement.setString(2, txtupdateDepartureDate.getText());
                    statement.setInt(3, Character.getNumericValue(id.charAt(0)));

                    statement.executeUpdate();

                    update_all_comboboxes();

                    statement.close();
                    connection.close();
                    System.out.println("Connection closed!");

                } catch (SQLException ex) {
                    throw new IllegalStateException("Cannot connect the database!", ex);
                }
            }
        });

        bSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection connection = DataBaseConnection.database_connection();
                    System.out.println("Database connected!");

                    String id = listQuestions.getSelectedValue();
                    String words[] = id.split(" ");

                    String query = "UPDATE problem SET status=?, solve=? WHERE idProblem=?";

                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, "solved");
                    statement.setString(2, txtsolve.getText());
                    statement.setInt(3, Integer.parseInt(words[0]));

                    statement.executeUpdate();

                    show_questions();
                    txtsolve.setText("");

                    statement.close();
                    connection.close();
                    System.out.println("Connection closed!");

                } catch (SQLException ex) {
                    throw new IllegalStateException("Cannot connect the database!", ex);
                }
            }
        });
    }

    private void update_all_comboboxes() {
        set_infos();

        cbDeteteCompany.setModel(new DefaultComboBoxModel<>(companies));
        cbSelectCompany.setModel(new DefaultComboBoxModel<>(companies));
        cbDetetePlane.setModel(new DefaultComboBoxModel<>(planes));
        cbDeteteAirport.setModel(new DefaultComboBoxModel<>(airports));
        cbSelectPlane.setModel(new DefaultComboBoxModel<>(planes));
        cbSelectArrivalAirport.setModel(new DefaultComboBoxModel<>(airports));
        cbSelectDepartureAirport.setModel(new DefaultComboBoxModel<>(airports));
        cbDeteteFlight.setModel(new DefaultComboBoxModel<>(flights));
        cbUpdateFlight.setModel(new DefaultComboBoxModel<>(flights));
    }

    private void show_questions() {
        listQuestions.removeAll();


        try {
            Connection connection = DataBaseConnection.database_connection();
            System.out.println("Database connected!");

            Statement statement = connection.createStatement();

            String query = "SELECT * FROM problem ";
            ResultSet rs = statement.executeQuery(query);

            DefaultListModel<String> model = new DefaultListModel<String>();
            while (rs.next()) {
                if ("in progress".equals(rs.getString("status"))) {
                    int idProblem = rs.getInt("idProblem");
                    String subject = rs.getString("subject");
                    String status = rs.getString("status");
                    String content = rs.getString("content");
                    String solve = rs.getString("solve");

                    String problemx = idProblem + "    Subject: "+ subject + "\n    Content: " +content + "    Status: " + status + "    Solve: " + solve ;

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

