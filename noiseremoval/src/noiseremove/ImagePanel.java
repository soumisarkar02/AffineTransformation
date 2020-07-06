package noiseremove;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel{

	MyFrame frm;
	File f;
	BufferedImage img;
	
	
	ImagePanel(MyFrame frm) {
		this.frm=frm;
	}

	void removeNoise() {
		int k=Integer.parseInt(frm.text.getText());
		BufferedImage img2=new BufferedImage(img.getWidth(),img.getHeight(),BufferedImage.TYPE_INT_ARGB);
		int xrandom,yrandom,random;
				
		String inputFileName;
		File O[]=new File[k];
		BufferedImage inputImage[]=new BufferedImage[k];
		
		for(int i=0;i<k;i++)
		{	inputFileName="Noisy"+(i+1)+".png";
			O[i]=new File(inputFileName);
			try {
				inputImage[i]=ImageIO.read(f);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		
		for(int i=0;i<k;i++)
		{	int n=(int)(img.getHeight()*img.getWidth()/3);
		
			for(int j=1;j<=n;j++)
			{	xrandom=ThreadLocalRandom.current().nextInt(0,img.getWidth());
				yrandom=ThreadLocalRandom.current().nextInt(0,img.getHeight());
				random=ThreadLocalRandom.current().nextInt(1,76);
			
				Color oimg=new Color(img.getRGB(xrandom,yrandom));
				int r,g,b,sign=1;
				
				if(random<=45)
					sign=-1;
				else
					sign=1;
					
				r=oimg.getRed()-(sign*random);
				if(r<0)
					r=0;
				else if(r>255)
					r=255;
				g=oimg.getGreen()-(sign*random);
				if(g<0)
					g=0;
				else if(g>255)
					g=255;
				b=oimg.getBlue()-(sign*random);
				if(b<0)
					b=0;
				else if(b>255)
					b=255;
				
				Color c=new Color(r,g,b);
				inputImage[i].setRGB(xrandom, yrandom, c.getRGB());
			}
			
			try {
				ImageIO.write(inputImage[i], "png", O[i]);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		
		frm.noisePanel.showImage(inputImage[0]);
		
		BufferedImage outimage=new BufferedImage(img.getWidth(),img.getHeight(),BufferedImage.TYPE_INT_ARGB);
		
		for(int i=0;i<img.getWidth();i++)
			for(int j=0;j<img.getHeight();j++)
			{	int r=0;	int g=0;		int b=0;
				
				for(int m=0;m<k;m++)
				{	Color noise=new Color(inputImage[m].getRGB(i, j));
					r=r+noise.getRed();
					g=g+noise.getGreen();
					b=b+noise.getBlue();
				}
				
				Color outimg=new Color(r/k,g/k,b/k);
				outimage.setRGB(i, j, outimg.getRGB());
			}
		
		File outputImage=new File("Output.png");
		try {
			ImageIO.write(outimage, "png", outputImage);
		} catch (IOException e) {
			e.printStackTrace();
		}
		frm.outputPanel.showImage(outimage);
	}

	void readImage() {
		f=frm.fc.getSelectedFile();
		try {
			img=ImageIO.read(f);
			this.setPreferredSize(new Dimension(img.getWidth(),img.getHeight()));
			repaint();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public void paint(Graphics g)
	{	super.paint(g);
		g.drawImage(img, 0, 0, null);		
	}
}