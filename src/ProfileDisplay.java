import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ProfileDisplay {
    JFrame displayProfiles;
    private TravProfDB db;

    public ProfileDisplay(TravProfDB myDB) {
        this.db = myDB;
        this.displayProfiles = initDisplay();

    }

    public JFrame initDisplay() {
        JFrame menu = new JFrame("Traveler Profiles");
        menu.setSize(800, 400);
        menu.setLayout(new GridLayout(14, 2));
        menu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        return menu;
    }
}
