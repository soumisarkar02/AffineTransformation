package spatialtransformation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class translation extends JPanel
{
    JFrame F;
    public static void main(String args[])
    {
        new  translation();
    }
    
    translation()
    {
        F = new JFrame();
        F.setSize(800,600);
        F.setVisible(true);
        F.setTitle("Translation");
        
        F.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Graphics g = F.getGraphics();
        BufferedImage image = LoadImage("image/sample.png");
        g.drawImage(image,0,0,image.getWidth(),image.getHeight(),null);
        g.drawString("Input Image", 100, image.getHeight()+10);
        int tx,ty;
        Scanner ob = new Scanner(System.in);
        System.out.println("Enter Translation Factor: \nX: ");
        tx = ob.nextInt();
        System.out.println("Y: ");
        ty = ob.nextInt();
        
        int i,j;
        for(i=0;i<image.getWidth();i++)
        {
            for(j=0;j<image.getHeight();j++)
            {
               g.setColor(new Color(image.getRGB(i, j)));
               g.drawRect(i+tx, j+ty, 1, 1);
            }
        }
        
        g.setColor(Color.black);
        g.drawString("Output Image", (100+tx), (image.getHeight()+10+ty));
    }
    
    BufferedImage LoadImage(String FileName)
    {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(FileName));
        } catch (IOException ex) {
            Logger.getLogger(translation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return img;
    }
}
