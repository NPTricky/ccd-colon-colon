package j3chess;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

public class DrawPanel extends JComponent {

	private BufferedImage mImg;

	public DrawPanel(int sizeX, int sizeY) {
		setPreferredSize(new Dimension(sizeX, sizeY));
		mImg = new BufferedImage(sizeX, sizeY, BufferedImage.TYPE_INT_ARGB);
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
}
