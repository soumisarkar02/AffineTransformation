package saltpeppernoise;

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

public class SaltPepperNoise extends JPanel
{
    int sx,sy;
    int a,b;
    int t;
    SaltPepperNoise()
    {
        Scanner ob = new Scanner(System.in);
        
        System.out.println("Enter width of the mask : ");
        sx = ob.nextInt();
        System.out.println("Enter height of the mask : ");
        sy = ob.nextInt();
    }
    BufferedImage LoadImage(String FileName)
    {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(FileName));
        } catch (IOException ex) {
            Logger.getLogger(SaltPepperNoise.class.getName()).log(Level.SEVERE, null, ex);
        }
        return img;
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        BufferedImage image = LoadImage("image/fruits.jpg");
        g.drawString("Original Image", 20, 20);
        g.drawImage(image, 20, 30, image.getWidth(), image.getHeight(), null);
        
        BufferedImage img=new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_ARGB);	
        
        a=(sx-1)/2;
        b=(sy-1)/2;
        for(int i=a;i<image.getWidth()-a;i++)
        {
            for(int j=b;j<image.getHeight()-b;j++)
            {
                t = 0;
                Color c=getMedian(image, i, j);
                img.setRGB(i, j, c.getRGB());
            }
        }
        
        g.drawString("Image After Noise Reduction", image.getWidth()+40, 20);
        g.drawImage(img, image.getWidth()+40, 30, image.getWidth(), image.getHeight(), null);
    }
    
    Color getMedian(BufferedImage image,int x,int y)
    {
        int i,j;
        long values[] = new long[sx*sy];
        /*int values[]={image.getRGB(x+1, y+1),image.getRGB(x+1, y-1),image.getRGB(x-1, y-1),image.getRGB(x-1, y+1),
                    image.getRGB(x+1, y),image.getRGB(x-1, y),image.getRGB(x, y-1),image.getRGB(x, y+1),image.getRGB(x, y)};*/
        a=(sx-1)/2;
        b=(sy-1)/2;
        for(i=-a;i<=a;i++)
        {
            for(j=-b;j<=b;j++)
            {
                values[t]=image.getRGB(x+i, y+j);
                t=t+1;
            }
        }
        
        for (i = 0; i < values.length; i++)
        {
            for (j = 0; j < values.length - i - 1; j++)
            {
                if (values[j] > values[j + 1]) 
                {
                    long temp = values[j];
                    values[j] = values[j + 1];
                    values[j + 1] = temp;
                }
            }
        }
                
        Color c = new Color((int)values[values.length / 2]);
        return c;
    }
    
    public static void main(String[] args) 
    {
        SaltPepperNoise in = new SaltPepperNoise();
        JFrame F = new JFrame();
        F.add(in);
        F.setSize(1500,800);
        F.setResizable(false);
        F.setVisible(true);
        F.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}