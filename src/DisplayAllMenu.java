import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DisplayAllMenu {
    JFrame menu;
    private TravProfDB db;
    private int idx;
    private String TravProfId;
    private ArrayList<JFrame> profiles;

    public DisplayAllMenu(TravProfDB myDB) {
        this.db = myDB;
        this.idx = 0;
        this.menu = initMenu();
    }

    private void createFrames(){
        ArrayList<JFrame> profileFrames = new ArrayList<>();

        for(int i = 0; i < db.getTravelerList().size(); i++){

            // Get current profile
            TravProf profile = db.getTravelerList().get(i);

            if(!profile.getTravAgentID().equals(this.TravProfId)){
                continue;
            }

            // Create the JFrame for the profile info
            JFrame profileFrame = new JFrame("Traveler Profile");
            profileFrame.setSize(800, 400);
            profileFrame.setLayout(new GridLayout(14,2));
            profileFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            JButton closeBtn = new JButton("Close");

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

            for (int j = 0; j < labels.length; j++) {
                profileFrame.add(labels[j]);
                profileFrame.add(values[j]);
            }
            profileFrame.add(closeBtn);
            closeBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    profileFrame.dispose();
                }
            });

            profileFrames.add(profileFrame);
        }
        this.profiles = profileFrames;
    }

    public JFrame initMenu() {
        JFrame menu = new JFrame("Display Profiles");
        menu.setSize(300, 300);
        menu.setLayout(new GridLayout(2,2));
        menu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel idLab = new JLabel("Enter your TravProfID");
        JTextField idVal = new JTextField();

        JButton submitBtn = new JButton("Submit ID");
        JButton nextBtn = new JButton("Next Profile");

        nextBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(getIdx() < getProfiles().size()){
                    JFrame currentFrame = getProfiles().get(getIdx());
                    currentFrame.setVisible(true);
                    increment();
                } else {
                    JFrame optFrame = new JFrame();
                    JOptionPane.showMessageDialog(optFrame, "No more profiles to display.");
                }
            }
        });

        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setID(idVal.getText());
                createFrames();
            }
        });

        menu.add(idLab);
        menu.add(idVal);
        menu.add(submitBtn);
        menu.add(nextBtn);

        return menu;
    }

    public JFrame getMenu() {
        return menu;
    }

    private int getIdx() {
        return idx;
    }

    public void increment(){
        this.idx += 1;
    }

    private void setID(String id){
        this.TravProfId = id;
    }

    public ArrayList<JFrame> getProfiles() {
        return profiles;
    }
}
