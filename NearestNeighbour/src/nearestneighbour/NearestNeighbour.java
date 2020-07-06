package nearestneighbour;

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

public class NearestNeighbour extends JPanel
{
    double sx,sy;
    NearestNeighbour()
    {
        Scanner ob = new Scanner(System.in);
        
        System.out.println("Enter the Scale factor along x-axis : ");
        sx = ob.nextDouble();
        System.out.println("Enter the Scale factor along y-axis : ");
        sy = ob.nextDouble();
    }
    BufferedImage LoadImage(String FileName)
    {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(FileName));
        } catch (IOException ex) {
            Logger.getLogger(NearestNeighbour.class.getName()).log(Level.SEVERE, null, ex);
        }
        return img;
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        BufferedImage image = LoadImage("image/input.jpeg");
        g.drawString("Original Image", 20, 20);
        g.drawImage(image, 20, 30, image.getWidth(), image.getHeight(), null);
        
        int w = (int)Math.round(sx*image.getWidth());
        int h = (int)Math.round(sy*image.getHeight());
            
        BufferedImage img=new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);	
        for(int i=0;i<w;i++)
        {
            for(int j=0;j<h;j++)
            {	
                //after scale either the area in which image to plot increases or deceases
                //so for proper mapping we color each pixel of the scaled image 
                //depending upon the pixel intensity of its nearest neighbour
                //(i/sx) or (j/sy) helps in finding the nearest pixel coordinate in the original image
                //that oordinates color intensity is used for the new image
                int x=(int)Math.floor(i/sx);
                int y=(int)Math.floor(j/sy);
                Color c=new Color(image.getRGB(x, y));
                img.setRGB(i, j, c.getRGB());
            }
        }
        
        g.drawString("Image After Nearest Neighbour Interpolation", image.getWidth()+40, 20);
        g.drawImage(img, image.getWidth()+40, 30, w, h, null);
        try {
            ImageIO.write(img, "png", new File("image//Output.png"));
        } catch (IOException ex) {
            Logger.getLogger(NearestNeighbour.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String args[])
    {
        NearestNeighbour in = new NearestNeighbour();
        JFrame F = new JFrame();
        F.add(in);
        F.setTitle("Nearest Neighbour");
        F.setSize(1500,800);
        F.setResizable(false);
        F.setVisible(true);
        F.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}