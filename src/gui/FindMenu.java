package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FindMenu {
    private JFrame findMenu;

    public FindMenu(){
        this.findMenu = initMenu();
    }

    private JFrame initMenu(){
        JFrame findMenu = new JFrame("Find Profile");
        findMenu.setSize(400, 500);
        findMenu.setLayout(new GridLayout(4, 2));
        findMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Labels and text fields
        JLabel travIDLabel = new JLabel("Traveler ID:");
        JLabel lstName = new JLabel("Last Name:");

        JTextField travIDInput = new JTextField();
        JTextField lstNameInput = new JTextField();

        JButton findBtn = new JButton("Find");
        JButton closeBtn = new JButton("Close");

        // Place items on the layout
        findMenu.add(travIDLabel);
        findMenu.add(travIDInput);
        findMenu.add(lstName);
        findMenu.add(lstNameInput);
        findMenu.add(findBtn);

        findMenu.setVisible(true);

        // Show a popup message
        // TODO: implement display
        JOptionPane JOpt = new JOptionPane();
        JFrame displayFrame = new JFrame("Traveler Profile");
        displayFrame.setSize(800, 400);
        displayFrame.setLayout(new GridLayout(14,2));
        displayFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Listen for find button
        findBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Get input values for ID and last name
                String travIDValue = travIDInput.getText();
                String lstNameValue = lstNameInput.getText();
                // Execute actions on the database
                // TODO: retrieve from database

                // Labels for Profile
                JLabel travelerIDLab = new JLabel("Traveler ID:");
                JLabel firstNameLab = new JLabel("First Name:");
                JLabel lastNameLab = new JLabel("Last Name:");
                JLabel addressLab = new JLabel("Address:");
                JLabel phoneLab = new JLabel("Phone:");
                JLabel tripCostLab = new JLabel("Trip Cost:");
                JLabel travelTypeLab = new JLabel("Travel Type:");
                JLabel payTypeLab = new JLabel("Payment Type:");
                JLabel modeTransLab = new JLabel("Mode of Traavel:");
                JLabel compNameLab = new JLabel("Company Name:");
                JLabel rewardsIDLab = new JLabel("Rewards ID:");
                JLabel rewardsBalLab = new JLabel("Rewards Balance:");

                // TextFields for Profile
                JTextField travIDVal = new JTextField();
                JTextField firstNameVal = new JTextField();
                JTextField lstNameVal = new JTextField();
                JTextField addrVal = new JTextField();
                JTextField phoneVal = new JTextField();
                JTextField tripCostVal = new JTextField();
                JTextField travTypeVal = new JTextField();
                JTextField payTypeVal = new JTextField();
                JTextField travModeVal = new JTextField();
                JTextField compNameVal = new JTextField();
                JTextField rewardsIDVal = new JTextField();
                JTextField rewardsBalVal = new JTextField();

                displayFrame.add(travelerIDLab);
                displayFrame.add(travIDVal);

                displayFrame.add(firstNameLab);
                displayFrame.add(firstNameVal);

                displayFrame.add(lastNameLab);
                displayFrame.add(lstNameVal);

                displayFrame.add(addressLab);
                displayFrame.add(addrVal);

                displayFrame.add(phoneLab);
                displayFrame.add(phoneVal);

                displayFrame.add(tripCostLab);
                displayFrame.add(tripCostVal);

                displayFrame.add(travelTypeLab);
                displayFrame.add(travTypeVal);

                displayFrame.add(payTypeLab);
                displayFrame.add(payTypeVal);

                displayFrame.add(modeTransLab);
                displayFrame.add(travModeVal);

                displayFrame.add(compNameLab);
                displayFrame.add(compNameVal);

                displayFrame.add(rewardsIDLab);
                displayFrame.add(rewardsIDVal);

                displayFrame.add(rewardsBalLab);
                displayFrame.add(rewardsBalVal);

                displayFrame.add(closeBtn);

                displayFrame.setVisible(true);

                findMenu.dispose();
            }
        });

        closeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayFrame.dispose();
            }
        });

        return findMenu;
    }

    public JFrame getFindMenu() {
        return findMenu;
    }
}
