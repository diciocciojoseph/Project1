import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteMenu {
    private JFrame deleteMenu;
    private TravProfDB db;

    public DeleteMenu(TravProfDB myDB) {
        this.db = myDB;
        this.deleteMenu = initGUI();
    }

    private JFrame initGUI() {
        // Init the page and set the exit operation
        JFrame deleteMenu = new JFrame("Delete Profile");
        deleteMenu.setSize(400, 400);
        deleteMenu.setLayout(new GridLayout(4, 2));
        deleteMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Labels and text fields
        JLabel travIDLabel = new JLabel("Traveler ID:");
        JLabel lstName = new JLabel("Last Name:");

        JTextField travIDInput = new JTextField();
        JTextField lstNameInput = new JTextField();

        JButton submit = new JButton("Delete");

        // Place items on layout
        deleteMenu.add(travIDLabel);
        deleteMenu.add(travIDInput);
        deleteMenu.add(lstName);
        deleteMenu.add(lstNameInput);
        deleteMenu.add(submit);

        deleteMenu.setVisible(true);

        // Listen for submit button
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Execute actions in database for deleting
                String travProfID = travIDInput.getText();
                String travLstName = lstNameInput.getText();

                boolean success = db.deleteProfile(travProfID, travLstName);
                JFrame optFrame = new JFrame("Delete Status");
                if (success) {
                    JOptionPane.showMessageDialog(optFrame, "Profile has been deleted.");
                } else {
                    JOptionPane.showMessageDialog(optFrame, "Profile not found.");
                }
                deleteMenu.dispose();
            }
        });
        return deleteMenu;
    }

    public JFrame getDeleteMenu() {
        return deleteMenu;
    }

}
