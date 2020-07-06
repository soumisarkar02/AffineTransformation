package histogram_equalization;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Histogram_equalization extends JFrame
{
    BufferedImage image;
    Histogram_equalization() throws IOException
    {
        JFrame frame = new JFrame();
        frame.setBounds(0,0,1350,800);
        frame.setTitle("Histogram");
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        
        image = ImageIO.read(new File("image/rose.jpg"));
        Graphics g = frame.getGraphics();
        g.drawImage(image,image.getWidth()+10,0,image.getWidth(),image.getHeight(),null);
        g.setColor(Color.black);
        draw_histogram(g);
    }
    
    public void draw_histogram(Graphics g)
    {
        BufferedImage img=new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_ARGB);	
        
        int intensity[] = new int[256];
        int output_intensity[] = new int[256];
        double probfreq[] = new double[256];
        int i,j;
        
        for(i=0;i<256;i++)
        {
            intensity[i] = 0;
            output_intensity[i] = 0;
        }
        int z=0;
        for(i=0;i<image.getWidth();i++)
        {
            for(j=0;j<image.getHeight();j++)
            {
                Color c = new Color(image.getRGB(i,j));
                intensity[c.getRed()] = intensity[c.getRed()] + 1;
                probfreq[c.getRed()] = intensity[c.getRed()]/(image.getHeight()*image.getWidth());
                z=z+1;
            }
        }
        int sum = 0,lmax = 255;
        double cdf[] = new double[256];
        double probc[] = new double[256];
        int output_frequency[] = new int[256];
        
        for (i=0;i<256;i++)
        {
            sum = sum + intensity[i];
            cdf[i]=sum;
            probc[i]=cdf[i]/(image.getHeight()*image.getWidth());
            output_frequency[i]=(int) (probc[i]*lmax);//sk
        }
        for (i=0;i<image.getHeight();i++)
        {
            for(j=0;j<image.getWidth();j++)
            {
                Color c = new Color(image.getRGB(j,i));
                g.setColor(new Color(output_frequency[c.getRed()],output_frequency[c.getRed()],output_frequency[c.getRed()]));
                g.fillRect(j,i,1,1);
                Color c1 = new Color(output_frequency[c.getRed()],output_frequency[c.getRed()],output_frequency[c.getRed()]);
                output_intensity[c1.getRed()] = output_intensity[c1.getRed()] + 1;
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
            intensity[i]=(250*(intensity[i] - min)/max);
        }
        
        int width = 256;
        int height = 250;
        
        g.setColor(Color.black);
        g.drawLine(image.getWidth()+10+50+100, (height+10+420), image.getWidth()+10+50+100+width, (height+10+420));
        g.drawLine(image.getWidth()+10+50+100,(height+10+420),image.getWidth()+10+100+50,(10+420));
        g.setColor(Color.black);
        g.drawString("Original Image", image.getWidth()+10+50+170, (height+10+420)+20);
        g.drawString("Equalized Image", 10+50+160, (height+10+420)+20);

        g.setColor(Color.gray);
        for(i=0;i<256;i++)
        {
            g.drawLine(image.getWidth()+10+(i+50+1)+100, (height+10+420), image.getWidth()+100+10+(i+50+1), (height+10+420 -intensity[i]));
        }
        
        max = 0;
        for(i=0;i<256;i++)
        {
            if(max < output_intensity[i])
                max = output_intensity[i];
        }
        
        min = max;
        for(i=0;i<256;i++)
        {
            if(min > output_intensity[i])
                min = output_intensity[i];
        }
        
        for(i=0;i<256;i++)
        {
            output_intensity[i]=(250*(output_intensity[i] - min)/max);
        }
        
        width = 256;
        height = 250;
        
        g.setColor(Color.black);
        g.drawLine(50+100, (height+10+420), 50+100+width, (height+10+420));
        g.drawLine(50+100,(height+10+420), 100+50,(10+420));
        
        g.setColor(Color.gray);
        for(i=0;i<256;i++)
        {
            g.drawLine((i+50+1)+100, (height+10+420), 100+(i+50+1), (height+10+420 -output_intensity[i]));
        }
    }
    
    public static void main(String[] args) throws IOException
    {
        Histogram_equalization h = new Histogram_equalization();
    }   
}