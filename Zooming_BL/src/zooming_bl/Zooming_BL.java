package zooming_bl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
public class Zooming_BL
{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedImage image;
    Zooming_BL() throws IOException
    {
        System.out.println("Enter The scale factor along x-axis");
        float sx = Float.valueOf(br.readLine());
        
        System.out.println("Enter The scale factor along x-axis");
        float sy = Float.valueOf(br.readLine());
        
        JFrame frame = new JFrame();
        ImgInp obj = new ImgInp();
        frame.setSize(1500,800);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setTitle("Bicubic Interpolation");
        frame.add(obj);
        
        image = ImageIO.read(new File("image/input.jpeg"));
        Graphics gr = frame.getGraphics();
        gr.setColor(Color.white);
        gr.fillRect(0, 0, 1350, 700);
        gr.drawImage(image, 0, 10, image.getWidth(), image.getHeight(), null);
        System.out.println(image.getWidth()+"    "+image.getHeight());
        int newWidth = (int) (image.getWidth() * sx);
        int newHeight = (int) (image.getHeight() * sy);
        BufferedImage newimg = obj.scale(image,sx,sy);
        gr.drawImage(newimg, image.getWidth()+10, 10, newWidth, newHeight, null);
        ImageIO.write(newimg, "png", new File("image//Output.png"));
    }
    
    public static void main(String[] args) throws IOException
    {
        Zooming_BL zm = new Zooming_BL();
    }
}