package j3chess;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JComponent;



class DrawPanel extends JComponent {

    Image img;


    
    public DrawPanel(Image img) {
    	this.img=img;
    	Dimension dm = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(dm);
    }
    
  
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(img, 0, 0, null);
    }
}