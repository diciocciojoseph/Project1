import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateMenu {

    private JFrame updateMenu;
    private TravProfDB db;

    public UpdateMenu(TravProfDB myDB) {
        this.db = myDB;
        this.updateMenu = initGUI();
    }

    private JFrame initGUI() {

        JFrame menu = new JFrame("Update Profile");
        menu.setSize(600, 400);
        menu.setLayout(new GridLayout(6, 2));
        menu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Labels and text fields
        JLabel travIDLabel = new JLabel("Traveler ID:");
        JLabel lstNameLabel = new JLabel("Last Name:");
        JLabel updateLabel = new JLabel("Update Field:");

        JTextField travIDInput = new JTextField();
        JTextField lstNameInput = new JTextField();

        // Drop down menu
        String[] traitsList = {"Address", "Phone Number", "Travel Type",
                "Trip Cost", "Payment Type", "Physician Name", "Physician Number",
                "Allergy Type", "Illness Type"};

        final JComboBox<String> traitsMenu = new JComboBox<String>(traitsList);

        // Update button
        JButton updateBtn = new JButton("Update");

        // Place Items on Layout
        menu.add(travIDLabel);
        menu.add(travIDInput);
        menu.add(lstNameLabel);
        menu.add(lstNameInput);
        menu.add(updateLabel);
        menu.add(traitsMenu);
        menu.add(updateBtn);

        menu.setVisible(true);

        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = traitsMenu.getSelectedIndex();
                TravProf profile = db.findProfile(lstNameInput.getText(), travIDInput.getText());
                if (profile == null){
                    JFrame optFrame = new JFrame("Profile Not Found");
                    JOptionPane.showMessageDialog(optFrame, "Profile Not Found.");
                } else {
                    // Create Update Frame
                    JFrame updateFrame = new JFrame("Update");
                    updateFrame.setSize(600, 400);
                    updateFrame.setLayout(new GridLayout(6, 2));
                    updateFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    JLabel title = new JLabel("Update Profile");
                    JLabel id = new JLabel("Traveler ID:");
                    JLabel idVal = new JLabel(profile.getTravAgentID());
                    JLabel lname = new JLabel("Last Name");
                    JLabel lnameVal = new JLabel(profile.getLastName());
                    JButton submitBtn = new JButton("Submit");

                    updateFrame.add(title);
                    updateFrame.add(new JLabel(""));
                    updateFrame.add(id);
                    updateFrame.add(idVal);
                    updateFrame.add(lname);
                    updateFrame.add(lnameVal);

                    switch (choice){
                        case 0:
                            // address
                            JLabel addressLab = new JLabel("Address: ");
                            JTextField addressField = new JTextField();
                            updateFrame.add(addressLab);
                            updateFrame.add(addressField);
                            updateFrame.add(submitBtn);
                            updateFrame.setVisible(true);

                            submitBtn.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    profile.updateAddress(addressField.getText());
                                    updateFrame.dispose();
                                }
                            });
                            break;

                        case 1:
                            // phone number
                            JLabel phoneLab = new JLabel("Phone Number: ");
                            JTextField phoneField = new JTextField();
                            updateFrame.add(phoneLab);
                            updateFrame.add(phoneField);
                            updateFrame.add(submitBtn);
                            updateFrame.setVisible(true);

                            submitBtn.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    profile.updatePhone(phoneField.getText());
                                    updateFrame.dispose();
                                }
                            });
                            break;

                        case 2:
                            // Travel Type
                            JLabel travelLab = new JLabel("Travel Type: ");
                            String[] travTypeL = {"Pleasure", "Business"};
                            JComboBox<String> travelTypeBox = new JComboBox<>(travTypeL);
                            updateFrame.add(travelLab);
                            updateFrame.add(travelTypeBox);
                            updateFrame.add(submitBtn);
                            updateFrame.setVisible(true);

                            submitBtn.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    profile.updateTravelType(travTypeL[travelTypeBox.getSelectedIndex()]);
                                    updateFrame.dispose();
                                }
                            });
                            break;

                        case 3:
                            // Trip Cost
                            JLabel tripLab = new JLabel("Trip Cost: ");
                            JTextField tripField = new JTextField();
                            updateFrame.add(tripLab);
                            updateFrame.add(tripField);
                            updateFrame.add(submitBtn);
                            updateFrame.setVisible(true);

                            submitBtn.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    profile.updateTripCost(Float.parseFloat(tripField.getText()));
                                    updateFrame.dispose();
                                }
                            });
                            break;

                        case 4:
                            // Payment Type
                            JLabel paymentLab = new JLabel("Payment Type: ");
                            String[] payTypeL = {"Credit", "Check", "Debit", "Invoice"};
                            JComboBox<String> payTypeBox = new JComboBox<>(payTypeL);
                            updateFrame.add(paymentLab);
                            updateFrame.add(payTypeBox);
                            updateFrame.add(submitBtn);
                            updateFrame.setVisible(true);

                            submitBtn.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    profile.updatePaymentType(payTypeL[payTypeBox.getSelectedIndex()]);
                                    updateFrame.dispose();
                                }
                            });
                            break;

                        case 5:
                            // Physician Name
                            JLabel pnameLab = new JLabel("Physician Name: ");
                            JTextField pnameField = new JTextField();
                            updateFrame.add(pnameLab);
                            updateFrame.add(pnameField);
                            updateFrame.add(submitBtn);
                            updateFrame.setVisible(true);

                            submitBtn.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    profile.getMedCondInfo().updateMdContact(pnameField.getText());
                                    updateFrame.dispose();
                                }
                            });
                            break;

                        case 6:
                            // Physician Number
                            JLabel pnumLab = new JLabel("Physician Number: ");
                            JTextField pnumField = new JTextField();
                            updateFrame.add(pnumLab);
                            updateFrame.add(pnumField);
                            updateFrame.add(submitBtn);
                            updateFrame.setVisible(true);

                            submitBtn.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    profile.getMedCondInfo().updateMdPhone(pnumField.getText());
                                    updateFrame.dispose();
                                }
                            });
                            break;

                        case 7:
                            // Allergy Type
                            JLabel allergyLab = new JLabel("Payment Type: ");
                            String[] allerL = {"None", "Food", "Medication", "Other"};
                            JComboBox<String> allergyTypeBox = new JComboBox<>(allerL);
                            updateFrame.add(allergyLab);
                            updateFrame.add(allergyTypeBox);
                            updateFrame.add(submitBtn);
                            updateFrame.setVisible(true);

                            submitBtn.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    profile.getMedCondInfo().updateAlgType(allerL[allergyTypeBox.getSelectedIndex()]);
                                    updateFrame.dispose();
                                }
                            });
                            break;

                        case 8:
                            // Illness Type
                            JLabel illLab = new JLabel("Payment Type: ");
                            String[] illL = {"None", "Heart", "Diabetes", "Asthma", "Other"};
                            JComboBox<String> illTypeBox = new JComboBox<>(illL);
                            updateFrame.add(illLab);
                            updateFrame.add(illTypeBox);
                            updateFrame.add(submitBtn);
                            updateFrame.setVisible(true);

                            submitBtn.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    profile.getMedCondInfo().updateIllType(illL[illTypeBox.getSelectedIndex()]);
                                    updateFrame.dispose();
                                }
                            });
                            break;
                    }
                }
                db.writeAllTravProf(db.getFileName());
            }
        });

        return menu;
    }

    public JFrame getUpdateMenu() {
        return this.updateMenu;
    }

}
