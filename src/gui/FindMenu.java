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

        // Place items on the layout
        findMenu.add(travIDLabel);
        findMenu.add(travIDInput);
        findMenu.add(lstName);
        findMenu.add(lstNameInput);
        findMenu.add(findBtn);

        findMenu.setVisible(true);

        // Listen for find button
        findBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Get input values for ID and last name
                String travIDValue = travIDInput.getText();
                String lstNameValue = lstNameInput.getText();
                // Execute actions on the database
                // TODO: retrieve from database

                // Show a popup message
                // TODO: implement display

                findMenu.dispose();
            }
        });

        return findMenu;
    }

    public JFrame getFindMenu() {
        return findMenu;
    }
}
