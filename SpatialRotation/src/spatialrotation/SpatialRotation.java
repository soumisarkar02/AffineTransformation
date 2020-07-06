package spatialrotation;

import java.awt.Color;
import java.awt.Graphics;
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

public class SpatialRotation extends JPanel
{
    JFrame F;
    
   
    public static void main(String args[])
    {
        new  SpatialRotation();
    }
    
    SpatialRotation()
    {
        F = new JFrame();
        F.setSize(800,600);
        F.setVisible(true);
        F.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Graphics g = F.getGraphics();
        BufferedImage image = LoadImage("image/sample.png");
        g.drawImage(image,0,0,image.getWidth(),image.getHeight(),null);
        g.drawString("Input Image", 100, image.getHeight()+10);
        
        double theta;
        int tx,ty;
        Scanner ob = new Scanner(System.in);
        System.out.println("Enter Rotation Factor: \nTheta: ");
        theta = ob.nextInt();
        System.out.println("Enter Fixed Rotation Point: \nX: ");
        tx = ob.nextInt();
        System.out.println("Y: ");
        ty = ob.nextInt();
        
        int i,j,x1,y1;
        for(i=0;i<image.getWidth();i++)
        {
            for(j=0;j<image.getHeight();j++)
            {
               x1 = (int) (tx + (i-tx) * Math.cos(theta * 3.14 / 180) - (j-ty) * Math.sin(theta * 3.14 / 180));
               y1 = (int) (ty + (i-tx) * Math.sin(theta * 3.14 / 180) + (j-ty) * Math.cos(theta * 3.14 / 180));
               g.setColor(new Color(image.getRGB(i, j)));
               g.drawRect(x1, y1, 1, 1);
            }
        }
    }
    
    BufferedImage LoadImage(String FileName)
    {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(FileName));
        } catch (IOException ex) {
            Logger.getLogger(SpatialRotation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return img;
    }
}
