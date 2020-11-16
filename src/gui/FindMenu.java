package gui;

import javax.swing.*;

public class FindMenu {
    private JFrame findMenu;

    public FindMenu(){
        this.findMenu = initMenu();
    }

    private JFrame initMenu(){
        JFrame menu = new JFrame("Find Profile");
        menu.setSize(400, 500);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        return menu;
    }

    public JFrame getFindMenu() {
        return findMenu;
    }
}
