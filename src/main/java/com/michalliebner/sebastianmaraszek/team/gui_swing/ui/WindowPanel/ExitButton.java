package com.michalliebner.sebastianmaraszek.team.gui_swing.ui.WindowPanel;

import javax.swing.*;

public class ExitButton extends JButton {

    public ExitButton(){
        setBounds(1172,8,16,16);
        setBorderPainted(false);
        setContentAreaFilled(false);
        ExitIcon icon = new ExitIcon();
        setIcon(icon);
    }


}

