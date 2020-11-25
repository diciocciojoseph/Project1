package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateMenu {

    private JFrame updateMenu;

    public UpdateMenu() {
        this.updateMenu = initGUI();
    }

    private JFrame initGUI() {

        JFrame menu = new JFrame("Update Profile");
        menu.setSize(400, 400);
        menu.setLayout(new GridLayout(5, 2));
        menu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Labels and text fields and combo box
        JLabel travIDLabel = new JLabel("Traveler ID:");
        JLabel lstNameLabel = new JLabel("Last Name:");
        JLabel updateLabel = new JLabel("Update Field:");

        JTextField travIDInput = new JTextField();
        JTextField lstNameInput = new JTextField();

        String[] traitsList = {"Address", "Phone Number", "Travel Type",
                "Trip Cost", "Payment Type", "Physician Type", "Physician " +
                "Phone Number", "Allergy", "Illness Type"};
        JComboBox traitsMenu = new JComboBox(traitsList);

        // Place Items on Layout
        menu.add(travIDLabel);
        menu.add(travIDInput);
        menu.add(lstNameLabel);
        menu.add(lstNameInput);
        menu.add(updateLabel);
        menu.add(traitsMenu);


        return menu;
    }

    public JFrame getUpdateMenu() {
        return this.updateMenu;
    }

}
