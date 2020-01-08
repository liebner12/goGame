package com.michalliebner.sebastianmaraszek.team.gui_swing.ui;
import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.BoardPanel.Board;
import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.UIPanel.Results.Results;
import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.UIPanel.UI;
import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.WindowPanel.Window;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;
import javax.swing.*;
public class GoGameGui extends JFrame {
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 760;
    private Board Board;
    private UI UI;
    private Window window;
    private Container container = getContentPane();
    private Results results;

    public GoGameGui() throws IOException {
        setLayout(null);
        setTitle("GoGame");
        setUndecorated(true);
        setShape(new RoundRectangle2D.Double(0,0,WIDTH,HEIGHT,20,20));
        setSize(WIDTH,HEIGHT);
        setContentPane(components());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public UI getUIPanel(){
        return UI;
    }
    public Board getBoard(){
        return Board;
    }
    public Window getWindow(){
        return window;
    }
    public Results getResults(){
        return results;
    }
    private Container components() throws IOException {
        results = new Results();
        results.initResults();
        window = new Window();
        window.initWindow();
        Board = new Board();
        UI = new UI();
        UI.initUI();
        add(results);
        add(UI);
        add(Board);
        add(window);
        return container;
    }
}