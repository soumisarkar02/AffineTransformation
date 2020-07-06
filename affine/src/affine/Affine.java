package affine;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Affine extends JPanel
{
    public static void main(String args[])
    {
        JFrame F = new JFrame();
        F.add(new Affine());
        F.setSize(800,800);
        F.setVisible(true);
        F.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void paintComponent(Graphics g)
    {
        BufferedImage apple = LoadImage("image/fb.png");
        AffineTransform at = AffineTransform.getTranslateInstance(100,100);
        at.rotate(Math.toRadians(45),apple.getWidth(),apple.getHeight());
        Graphics2D g2d = (Graphics2D) g;
        //g2d.drawImage(apple, at, null);
        //repaint();
        at.scale(2,2);
        g2d.drawImage(apple, at, null);
        repaint();
    }
    
    BufferedImage LoadImage(String FileName)
    {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(FileName));
        } catch (IOException ex) {
            Logger.getLogger(Affine.class.getName()).log(Level.SEVERE, null, ex);
        }
        return img;
    }
}