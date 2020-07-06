package noiseremove;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class NoisePanel extends JPanel{

	BufferedImage img;
	
	void showImage(BufferedImage img2) {
		img=img2;
		repaint();
	}
	
	public void paint(Graphics g)
	{	super.paint(g);
		g.drawImage(img, 0, 0, null);
	}
}