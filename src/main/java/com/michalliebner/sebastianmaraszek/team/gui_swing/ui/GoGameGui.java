package com.michalliebner.sebastianmaraszek.team.gui_swing.ui;

import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.BoardPanel.Board;
import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.UIPanel.NextButton;
import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.UIPanel.SurrenderButton;
import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.UIPanel.UI;
import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.WindowPanel.ExitButton;
import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.WindowPanel.Window;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;

public class GoGameGui extends JFrame {
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 760;

    private com.michalliebner.sebastianmaraszek.team.gui_swing.ui.BoardPanel.Board Board;
    private ExitButton exitButton;
    private com.michalliebner.sebastianmaraszek.team.gui_swing.ui.UIPanel.UI UI;
    private Container container = getContentPane();
    private com.michalliebner.sebastianmaraszek.team.gui_swing.ui.WindowPanel.Window window;
    private NextButton nextButton;
    private SurrenderButton surrenderButton;
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
    public ExitButton getExitButton(){
        return exitButton;
    }
    public UI getUIPanel(){
        return UI;
    }
    public Board getBoard(){
        return Board;
    }
    public com.michalliebner.sebastianmaraszek.team.gui_swing.ui.WindowPanel.Window getWindow(){
        return window;
    }
    public NextButton getNextButton(){
        return nextButton;
    }
    public SurrenderButton getSurrenderButton(){
        return surrenderButton;
    }
    private Container components(){
        window = new Window();
        UI = new UI();
        Board = new Board();
        exitButton= new ExitButton();
        UI.initUI();

        add(exitButton);
        add(UI);
        add(Board);
        add(window);

        return container;
    }
}

