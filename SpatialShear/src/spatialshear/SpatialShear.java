package spatialshear;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SpatialShear extends JPanel
{
    JFrame F;
    public static void main(String args[])
    {
        new SpatialShear();
    }
    
    SpatialShear()
    {
        F = new JFrame();
        F.setSize(800,800);
        F.setVisible(true);
        F.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Graphics g = F.getGraphics();
        BufferedImage image = LoadImage("image/sample.png");
        g.drawImage(image,20,20,image.getWidth(),image.getHeight(),null);
        
        int shx,shy,tx,ty;
        Scanner ob = new Scanner(System.in);
        System.out.println("Enter Shear Factor Along: \nX: ");
        shx = ob.nextInt();
        System.out.println("Y: ");
        shy = ob.nextInt();
        System.out.println("Enter Translation Factor Along: \nX: ");
        tx = ob.nextInt();
        System.out.println("Y: ");
        ty = ob.nextInt();
        
        int i,j,x1,y1;
        for(i=0;i<image.getWidth();i++)
        {
            for(j=0;j<image.getHeight();j++)
            {
                x1 = (i - tx)+shx*(j-ty)+tx;
                y1 = (i-tx)*shy + (j - ty+ty);
               g.setColor(new Color(image.getRGB(i, j)));
               g.drawRect(x1+20, y1+20, 1, 1);
            }
        }
    }
    
    BufferedImage LoadImage(String FileName)
    {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(FileName));
        } catch (IOException ex) {
            Logger.getLogger(SpatialShear.class.getName()).log(Level.SEVERE, null, ex);
        }
        return img;
    }
}