package j3chess;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JComponent;


class DrawPanel extends JComponent {

    private Image mImg;

    public DrawPanel(Image img) {
        mImg = img;
        Dimension dm = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(dm);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(mImg, 0, 0, this.getWidth(), this.getHeight(), null);
    }
}