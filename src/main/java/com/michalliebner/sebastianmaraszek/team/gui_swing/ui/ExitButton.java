package com.michalliebner.sebastianmaraszek.team.gui_swing.ui;

import javax.swing.*;
import java.awt.*;

class ExitButton extends JButton {

    ExitButton(){
        setBounds(1172,8,16,16);
        setBorderPainted(false);
        setContentAreaFilled(false);
        ExitIcon icon = new ExitIcon();
        setIcon(icon);



    }


}

