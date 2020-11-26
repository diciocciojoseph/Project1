package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateMenu {
    private JFrame createMenu;

    public CreateMenu(){
        this.createMenu = initMenu();
    }

    // CREATE PROFILE MENU
    public JFrame initMenu(){
        JFrame menu = new JFrame("Create Profile");
        menu.setSize(400, 500);
        menu.setLayout(new GridLayout(4, 2));
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Labels and text fields
        JLabel idLabel = new JLabel("Traveler ID:");
        JLabel fName = new JLabel("First Name:");
        JLabel lName = new JLabel("Last Name:");

        TextField idInput = new TextField();
        TextField fNameInput = new TextField();
        TextField lNameInput = new TextField();

        JButton submitBtn = new JButton("Submit");

        menu.add(idLabel);
        menu.add(idInput);
        menu.add(fName);
        menu.add(fNameInput);
        menu.add(lName);
        menu.add(lNameInput);
        menu.add(submitBtn);

        menu.setVisible(true);

        // Event Listeners
        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Profile Created Successfully!");
                createMenu.dispose();
            }
        });

        return menu;
    }

    public JFrame getCreateMenu() {
        return createMenu;
    }
}
