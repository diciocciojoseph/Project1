import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class MainMenu {
    JFrame menu;
    private String fileName;

    public MainMenu() throws IOException, ClassNotFoundException {
        this.fileName = "TravelerDatabase.txt";
        this.menu = initMenu();
    }

    // MAIN MENU
    private JFrame initMenu() throws IOException, ClassNotFoundException {
        // Create JFrame for main menu
        menu = new JFrame("Traveler ITS");
        menu.setLayout(new GridLayout(7, 0));
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setSize(400, 500);

        // Initialize and connect to database
        TravProfDB myDB = new TravProfDB(this.fileName);
        myDB.initializeDatabase(this.fileName);

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

        // Add event listener for select button
        selectBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(createBtn.isSelected()){
                    System.out.println("Create Selected");
                    CreateMenu createMenu = new CreateMenu(myDB);
                    createMenu.getCreateMenu().setVisible(true);

                } else if (deleteBtn.isSelected()){
                    System.out.println("Delete Selected");
                    DeleteMenu deleteMenu = new DeleteMenu(myDB);
                    deleteMenu.getDeleteMenu().setVisible(true);
                } else if (updateBtn.isSelected()){
                    System.out.println("Update Selected");
                    UpdateMenu updateMenu = new UpdateMenu(myDB);
                    updateMenu.getUpdateMenu().setVisible(true);
                } else if (find_displayBtn.isSelected()){
                    System.out.println("Find/Display Selected");
                    FindMenu findMenu = new FindMenu(myDB);
                    findMenu.getFindMenu().setVisible(true);
                } else if (display_allBtn.isSelected()){
                    System.out.println("Display All Selected");
                } else {
                    System.out.println("Make Selection");
                }
            }
        });

        return menu;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        MainMenu gui = new MainMenu();
    }
}
