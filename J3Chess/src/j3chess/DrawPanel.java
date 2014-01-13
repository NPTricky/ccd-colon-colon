package j3chess;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

import org.apache.logging.log4j.Level;

public class DrawPanel extends JComponent implements MouseListener {

    private BufferedImage mImg;

    public DrawPanel(int sizeX, int sizeY) {
        setPreferredSize(new Dimension(sizeX, sizeY));
        mImg = new BufferedImage(sizeX, sizeY, BufferedImage.TYPE_INT_ARGB);
        this.addMouseListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        J3ChessApp.getInstance().update();
        g.drawImage(mImg, 0, 0, null);
    }

    public Graphics2D getPersistentGraphics() {
        return (Graphics2D) mImg.getGraphics();
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        // Resize x and y to [-1,1] range
        final float x = ((float) arg0.getX() / (float) arg0.getComponent().getWidth()) * 2.0f - 1.0f;
        final float y = ((float) arg0.getY() / (float) arg0.getComponent().getHeight()) * 2.0f - 1.0f;

        // Get the field that the player clicked on
        final Field clickedField = J3ChessApp.getInstance().getGame().getChessboard().getFieldByXY(x, y);

        // Notify the game about a clicked field
        if (clickedField != null) {
            J3ChessApp.getInstance().getGame().notifyFieldClicked(clickedField);
        }
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub
    }
}
