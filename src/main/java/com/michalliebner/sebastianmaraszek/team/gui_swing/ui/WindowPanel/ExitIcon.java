package com.michalliebner.sebastianmaraszek.team.gui_swing.ui.WindowPanel;

import javax.swing.*;
import java.awt.*;

public class ExitIcon implements Icon {
    private int WIDTH = 32;
    private int HEIGHT = 32;

    private BasicStroke stroke = new BasicStroke(4);

    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(new Color(217, 226, 221));
        g2d.setStroke(stroke);
        g2d.drawLine(x + 10, y + 10, x + WIDTH - 10, y + HEIGHT - 10);
        g2d.drawLine(x + 10, y + HEIGHT - 10, x + WIDTH - 10, y + 10);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    public int getIconWidth() {
        return WIDTH;
    }

    public int getIconHeight() {
        return HEIGHT;
    }
}

