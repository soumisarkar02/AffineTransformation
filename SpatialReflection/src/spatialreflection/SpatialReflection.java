package spatialreflection;

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

public class SpatialReflection extends JPanel
{
    JFrame F;
    public static void main(String args[])
    {
        new SpatialReflection();
    }
    
    SpatialReflection()
    {
        F = new JFrame();
        F.setSize(800,800);
        F.setVisible(true);
        F.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Graphics g = F.getGraphics();
        BufferedImage image = LoadImage("image/fb.png");
        g.drawImage(image,0,0,image.getWidth(),image.getHeight(),null);
        
        int x1,y1,x2,y2;
        Scanner ob = new Scanner(System.in);
        System.out.println("Enter End Points: \nX1: ");
        x1 = ob.nextInt();
        System.out.println("Y1: ");
        y1 = ob.nextInt();
        System.out.println("Enter End Points: \nX2: ");
        x2 = ob.nextInt();
        System.out.println("Y2: ");
        y2 = ob.nextInt();
        
        int i,j,x,y;
        double m,m_ang,mc,ms,y_inc;
        m = (y2 - y1)/(x2 - x1);
        m_ang = Math.atan(m);
        y_inc = y1 - (x1 * m);
        
        System.out.println((m_ang * 180/3.14));
        mc = Math.cos(m_ang);
        ms = Math.sin(m_ang);
        //m_ang = 2*(m_ang * 180/3.14);
        g.setColor(Color.black);
        g.drawLine(x1, y1, x2, y2);
        
        for(i=0;i<image.getWidth();i++)
        {
            for(j=0;j<image.getHeight();j++)
            {
               x = -(int)((mc*mc - ms*ms)*i + (-2*ms*mc)*j + (2*y_inc*ms*mc));
               y = (int)((-2*ms*mc)*i + (ms*ms - mc*mc)*j + (y_inc*(y_inc*mc*mc - y_inc*ms*ms) + y_inc));
               
               System.out.println("X:"+x+" Y:"+y);
               g.setColor(new Color(image.getRGB(i, j)));
               g.drawRect(x, y, 1, 1);
            }
        }
    }
    
    BufferedImage LoadImage(String FileName)
    {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(FileName));
        } catch (IOException ex) {
            Logger.getLogger(SpatialReflection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return img;
    }
    
}