package com.michalliebner.sebastianmaraszek.team.gui_swing.ui;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;

public class GoGameGui extends JFrame {
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 760;
    private Board Board;
    private ExitButton exitButton;
    private UI UI;
    private Container container = getContentPane();
    private Window window;
    private NextButton nextButton;
    public GoGameGui() {
        setLayout(null);
        setTitle("GoGame");
        setUndecorated(true);
        setShape(new RoundRectangle2D.Double(0,0,WIDTH,HEIGHT,20,20));
        setSize(WIDTH,HEIGHT);
        setContentPane(components());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public JButton getExitButton(){
        return exitButton;
    }
    public JPanel getUIPanel(){
        return UI;
    }
    public JPanel getBoard(){
        return Board;
    }
    public JPanel getWindow(){
        return window;
    }
    public JButton getNextButton(){
        return nextButton;
    }
    private Container components(){
        window = new Window();
        UI = new UI();
        Board = new Board();
        exitButton= new ExitButton();
        nextButton= new NextButton();
        add(exitButton);
        add(nextButton);
        add(UI);
        add(Board);
        add(window);

        return container;
    }
}

