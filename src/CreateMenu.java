import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CreateMenu {
    private JFrame createMenu;
    private TravProfDB db;

    public CreateMenu(TravProfDB myDB){
        this.db = myDB;
        this.createMenu = initMenu();
    }

    // CREATE PROFILE MENU
    public JFrame initMenu(){
        JFrame menu = new JFrame("Create Profile");
        menu.setSize(400, 500);
        menu.setLayout(new GridLayout(14, 2));
        menu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Labels and text fields
        JLabel idLabel = new JLabel("Traveler ID:");
        JLabel fName = new JLabel("First Name:");
        JLabel lName = new JLabel("Last Name:");
        JLabel addr = new JLabel("Traveler's Address:");
        JLabel phone = new JLabel("Traveler's Phone Number:");
        JLabel travType = new JLabel("Travel Type:");
        JLabel payType = new JLabel("Payment Type:");
        JLabel tripCost = new JLabel("Trip Cost:");
        JLabel physName = new JLabel("Physician's Name:");
        JLabel physPhone = new JLabel("Physician's Phone Number:");
        JLabel aller = new JLabel("Allergy Type:");
        JLabel ill = new JLabel("Illness Type:");

        JTextField idInput = new JTextField();
        JTextField fNameInput = new JTextField();
        JTextField lNameInput = new JTextField();

        // Text Fields
        JTextField addrIn, phoneIn, tripCostIn, physNameIn, physPhoneIn;
        addrIn = new JTextField();
        phoneIn = new JTextField();
        tripCostIn = new JTextField();
        physNameIn = new JTextField();
        physPhoneIn = new JTextField();

        // Dropdown menu
        JComboBox travTypeIn, payTypeIn, allerIn, illIn;
        // Options for each dropdown menu
        String[] travTypeL = {"Pleasure", "Business"};
        String[] payTypeL = {"Credit", "Check", "Debit", "Invoice"};
        String[] allerL = {"None", "Food", "Medication", "Other"};
        String[] illL = {"None", "Heart", "Diabetes", "Asthma", "Other"};
        travTypeIn = new JComboBox(travTypeL);
        payTypeIn = new JComboBox(payTypeL);
        allerIn = new JComboBox(allerL);
        illIn = new JComboBox(illL);

        JButton submitBtn = new JButton("Submit");

        menu.add(idLabel);
        menu.add(idInput);
        menu.add(fName);
        menu.add(fNameInput);
        menu.add(lName);
        menu.add(lNameInput);
        menu.add(addr);
        menu.add(addrIn);
        menu.add(phone);
        menu.add(phoneIn);
        menu.add(travType);
        menu.add(travTypeIn);
        menu.add(payType);
        menu.add(payTypeIn);
        menu.add(tripCost);
        menu.add(tripCostIn);
        menu.add(physName);
        menu.add(physNameIn);
        menu.add(physPhone);
        menu.add(physPhoneIn);
        menu.add(aller);
        menu.add(allerIn);
        menu.add(ill);
        menu.add(illIn);
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
