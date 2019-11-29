package com.michalliebner.sebastianmaraszek.team.gui_swing.ui.WindowPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitButton extends JButton {
    public ExitButton(){
        setBounds(1172,8,16,16);
        setBorderPainted(false);
        setContentAreaFilled(false);
        ExitIcon icon = new ExitIcon();
        setIcon(icon);
    }
    public static class ExitButtonLister implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}

