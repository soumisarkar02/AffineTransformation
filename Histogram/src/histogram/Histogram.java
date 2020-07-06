package histogram;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Histogram extends JFrame
{
    BufferedImage image;
    Histogram() throws IOException
    {
        JFrame frame = new JFrame();
        frame.setBounds(10,10,1350,600);
        frame.setTitle("Histogram");
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        
        image = ImageIO.read(new File("image/sample2.jpg"));
        Graphics g = frame.getGraphics();
        g.drawImage(image,1000,200,200,200,null);
        g.setColor(Color.black);
        g.drawString("Input Image", 50+256+50+256+50+256+150, 420);
        draw_histogram(g);
    }
    
    public void draw_histogram(Graphics g)
    {
        int intensity[] = new int[256];
        int i,j;
        
        for(i=0;i<256;i++)
        {
            intensity[i] = 0;
        }
        int z=0;
        for(i=0;i<image.getWidth();i++)
        {
            for(j=0;j<image.getHeight();j++)
            {
                Color c = new Color(image.getRGB(i,j));
                intensity[c.getRed()] = intensity[c.getRed()] + 1;
                z=z+1;
            }
        }
        
        int max = 0;
        for(i=0;i<256;i++)
        {
            if(max < intensity[i])
                max = intensity[i];
        }
        
        int min = max;
        for(i=0;i<256;i++)
        {
            if(min > intensity[i])
                min = intensity[i];
        }
        
        for(i=0;i<256;i++)
        {
            intensity[i]=(500*(intensity[i] - min)/max);
        }
        
        int width = 256;
        int height = 500;
        
        g.setColor(Color.black);
        g.drawLine(50, (height+10+50), 50+width, (height+10+50));
        g.drawLine(50,(height+10+50),50,(10+50));
        
        g.setColor(Color.red);
        for(i=0;i<256;i++)
        {
            g.drawLine((i+50+1), (height+10+50), (i+50+1), (height+10+50-intensity[i]));
        }
        
        g.setColor(Color.black);
        g.drawString("Red Intensity", 128, (height+10+70));
        
        
        for(i=0;i<256;i++)
        {
            intensity[i] = 0;
        }
        z=0;
        for(i=0;i<image.getWidth();i++)
        {
            for(j=0;j<image.getHeight();j++)
            {
                Color c = new Color(image.getRGB(i,j));
                intensity[c.getGreen()] = intensity[c.getGreen()] + 1;
                z=z+1;
            }
        }
        
        max = 0;
        for(i=0;i<256;i++)
        {
            if(max < intensity[i])
                max = intensity[i];
        }
        
        min = max;
        for(i=0;i<256;i++)
        {
            if(min > intensity[i])
                min = intensity[i];
        }
        
        for(i=0;i<256;i++)
        {
            intensity[i]=(500*(intensity[i] - min)/max);
        }
        
        g.setColor(Color.black);
        g.drawLine(50+256+50, (height+10+50), 50+256+50+width, (height+10+50));
        g.drawLine(50+256+50,(height+10+50),50+256+50,(10+50));
        
        g.setColor(Color.green);
        for(i=0;i<256;i++)
        {
            g.drawLine((i+50+256+50+1), (height+10+50), (i+50+256+50+1), (height+10+50-intensity[i]));
        }
        
        g.setColor(Color.black);
        g.drawString("Green Intensity", 50+256+128, (height+10+70));
        
        
        
        for(i=0;i<256;i++)
        {
            intensity[i] = 0;
        }
        z=0;
        for(i=0;i<image.getWidth();i++)
        {
            for(j=0;j<image.getHeight();j++)
            {
                Color c = new Color(image.getRGB(i,j));
                intensity[c.getBlue()] = intensity[c.getBlue()] + 1;
                z=z+1;
            }
        }
        
        max = 0;
        for(i=0;i<256;i++)
        {
            if(max < intensity[i])
                max = intensity[i];
        }
        
        min = max;
        for(i=0;i<256;i++)
        {
            if(min > intensity[i])
                min = intensity[i];
        }
        
        for(i=0;i<256;i++)
        {
            intensity[i]=(500*(intensity[i] - min)/max);
        }
        
        g.setColor(Color.black);
        g.drawLine(50+256+50+256+50, (height+10+50), 50+256+50+256+50+width, (height+10+50));
        g.drawLine(50+256+50+256+50,(height+10+50),50+256+50+256+50,(10+50));
        
        g.setColor(Color.blue);
        for(i=0;i<256;i++)
        {
            g.drawLine((i+50+256+50+256+50+1), (height+10+50), (i+50+256+50+256+50+1), (height+10+50-intensity[i]));
        }
        
        g.setColor(Color.black);
        g.drawString("Blue Intensity", 50+256+50+256+128, (height+10+70));
    }
    
    public static void main(String[] args) throws IOException
    {
        Histogram h = new Histogram();
    }   
}