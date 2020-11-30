import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FindMenu {
    private JFrame findMenu;
    private TravProfDB db;

    public FindMenu(TravProfDB myDB){
        this.db = myDB;
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

        // Create a new panel that will display the traveler information if found
        // Otherwise display a "not found" panel

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

                // Labels for Profile
                JLabel travelerIDLab = new JLabel("Traveler ID:");
                JLabel firstNameLab = new JLabel("First Name:");
                JLabel lastNameLab = new JLabel("Last Name:");
                JLabel addressLab = new JLabel("Address:");
                JLabel phoneLab = new JLabel("Phone:");
                JLabel tripCostLab = new JLabel("Trip Cost:");
                JLabel travelTypeLab = new JLabel("Travel Type:");
                JLabel payTypeLab = new JLabel("Payment Type:");
                JLabel physNameLab = new JLabel("Physician's Name:");
                JLabel physPhoLab = new JLabel("Physician's Phone Number:");
                JLabel allergyLab = new JLabel("Allergy Type:");
                JLabel illnessLab = new JLabel("Illness Type:");

                // Execute actions on the database
                TravProf profile = db.findProfile(lstNameValue, travIDValue);

                if(profile == null) {
                    // Display not found panel
                    JFrame optFrame = new JFrame("Profile Not Found");
                    JOptionPane.showMessageDialog(optFrame, "The profile was not found.");

                } else {
                    // Values for Profile
                    JLabel travelerIDVal = new JLabel(profile.getTravAgentID());
                    JLabel firstNameVal = new JLabel(profile.getFirstName());
                    JLabel lastNameVal = new JLabel(profile.getLastName());
                    JLabel addressVal = new JLabel(profile.getAddress());
                    JLabel phoneVal = new JLabel(profile.getPhone());
                    JLabel tripCostVal = new JLabel(String.valueOf(profile.getTripCost()));
                    JLabel travelTypeVal = new JLabel(profile.getTravelType());
                    JLabel payTypeVal = new JLabel(profile.getPaymentType());
                    JLabel physNameVal = new JLabel(profile.getMedCondInfo().getMdContact());
                    JLabel physPhoVal = new JLabel(profile.getMedCondInfo().getMdPhone());
                    JLabel allergyVal = new JLabel(profile.getMedCondInfo().getAlgType());
                    JLabel illnessVal = new JLabel(profile.getMedCondInfo().getIllType());

                    // Add labels, textfields, buttons to displayFrame
                    JLabel[] labels = {travelerIDLab, firstNameLab, lastNameLab,
                            addressLab, phoneLab, tripCostLab, travelTypeLab,
                            payTypeLab, physNameLab, physPhoLab,
                            allergyLab, illnessLab};

                    JLabel[] values = {travelerIDVal, firstNameVal, lastNameVal,
                            addressVal, phoneVal, tripCostVal, travelTypeVal,
                            payTypeVal, physNameVal, physPhoVal, allergyVal, illnessVal};

                    for (int i = 0; i < labels.length; i++) {
                        displayFrame.add(labels[i]);
                        displayFrame.add(values[i]);
                    }
                    displayFrame.add(closeBtn);
                    displayFrame.setVisible(true);
                }
            }
        });

        closeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayFrame.getContentPane().removeAll();
                displayFrame.dispose();
            }
        });

        return findMenu;
    }

    public JFrame getFindMenu() {
        return findMenu;
    }
}
