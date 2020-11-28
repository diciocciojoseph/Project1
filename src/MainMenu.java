import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu {
    JFrame menu;

    public MainMenu() {
        this.menu = initMenu();
    }

    // MAIN MENU
    private JFrame initMenu() {
        // Create JFrame for main menu
        menu = new JFrame("Traveler ITS");
        menu.setLayout(new GridLayout(7, 0));
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setSize(400, 500);

        // Initialize and connect to database
        TravProfDB myDB = new TravProfDB("TravelerDatabase.txt");
        myDB.initializeDatabase(myDB.getFileName());

        // Create labels and buttons
        JLabel titleLabel = new JLabel("Integrated Travel System");
        JRadioButton createBtn = new JRadioButton("Create Profile");
        JRadioButton deleteBtn = new JRadioButton("Delete Profile");
        JRadioButton updateBtn = new JRadioButton("Update Profile");
        JRadioButton find_displayBtn = new JRadioButton("Find and Display");
        JRadioButton display_allBtn = new JRadioButton("Display all Profiles");
        JButton selectBtn = new JButton("Select");

        // Group Radio buttons so options are exclusive
        ButtonGroup group = new ButtonGroup();
        group.add(createBtn);
        group.add(deleteBtn);
        group.add(updateBtn);
        group.add(find_displayBtn);
        group.add(display_allBtn);

        // Add everything to menu frame
        menu.add(titleLabel);
        menu.add(createBtn);
        menu.add(deleteBtn);
        menu.add(updateBtn);
        menu.add(find_displayBtn);
        menu.add(display_allBtn);
        menu.add(selectBtn);

        menu.setVisible(true);

        // Event listener for select button
        selectBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(createBtn.isSelected()){
                    CreateMenu createMenu = new CreateMenu(myDB);
                    createMenu.getCreateMenu().setVisible(true);

                } else if (deleteBtn.isSelected()){
                    DeleteMenu deleteMenu = new DeleteMenu(myDB);
                    deleteMenu.getDeleteMenu().setVisible(true);

                } else if (updateBtn.isSelected()){
                    UpdateMenu updateMenu = new UpdateMenu(myDB);
                    updateMenu.getUpdateMenu().setVisible(true);

                } else if (find_displayBtn.isSelected()){
                    FindMenu findMenu = new FindMenu(myDB);
                    findMenu.getFindMenu().setVisible(true);

                } else if (display_allBtn.isSelected()){
                    DisplayAllMenu displayAllMenu = new DisplayAllMenu(myDB);
                    displayAllMenu.getMenu().setVisible(true);
                } else {
                    // System.out.println("Make Selection");
                }
            }
        });

        return menu;
    }

    public static void main(String[] args) {
        MainMenu gui = new MainMenu();
    }
}
