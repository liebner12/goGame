package com.michalliebner.sebastianmaraszek.team.gui_swing.ui.UIPanel;

import javax.swing.*;
import java.awt.*;

public class UI extends JPanel {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 700;
    private JLabel graWGo = new JLabel("GRA W GO");
    private SurrenderButton surrenderButton;
    private NextButton nextButton;
    public UI(){
        setLayout(null);
        graWGo.setBounds(140,50,150,24);
        graWGo.setFont(new Font("Dialog", Font.BOLD, 24));
        graWGo.setForeground(new Color(222, 227, 225));
        setBounds(770,30,WIDTH,HEIGHT);
        setBackground(new Color(193,161,121));
    }
    public void initUI(){
        nextButton= new NextButton();
        surrenderButton= new SurrenderButton();
        add(nextButton);
        add(surrenderButton);
        add(graWGo);
    }

}
