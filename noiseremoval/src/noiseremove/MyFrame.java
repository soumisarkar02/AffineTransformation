package noiseremove;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

public class MyFrame extends JFrame implements ActionListener{
	
	JPanel toppanel;
	ImagePanel imgpanel;
	JLabel k;
	JTextField text;
	JButton select,rmv;
	boolean fileselected;
	JFileChooser fc;
	JScrollPane scroll;
	JSplitPane splitPanelH;
	JSplitPane panel;
	OutputPanel outputPanel;
	NoisePanel noisePanel;
	
	MyFrame(String str)
	{	initComponents();
		
		this.setTitle(str);
		this.setSize(1366, 768);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	void initComponents() {
		toppanel=new JPanel();
		k=new JLabel("No. of Different Noisy Images(K) : ");
		text=new JTextField(5);
		select=new JButton("Select Image");
		rmv=new JButton("Remove Noise");
		fileselected=false;
		fc=new JFileChooser(".");
		
		toppanel.add(k);
		toppanel.add(text);
		toppanel.add(select);
		toppanel.add(rmv);
		
		select.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int result=fc.showOpenDialog(imgpanel);
				
				if(result==JFileChooser.APPROVE_OPTION)
				{	fileselected=true;
					imgpanel.readImage();
				}
			}
		});
		
		rmv.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(fileselected==true)
					imgpanel.removeNoise();
				else
					select.doClick();
			}
		});
		
		this.add(toppanel,BorderLayout.NORTH);
		
		imgpanel=new ImagePanel(this);
		scroll=new JScrollPane(imgpanel);
		scroll.setAutoscrolls(true);
		outputPanel=new OutputPanel();
		noisePanel=new NoisePanel();
		
		splitPanelH=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		splitPanelH.setLeftComponent(scroll);
		splitPanelH.setRightComponent(noisePanel);
		splitPanelH.setDividerLocation(455);
		
		panel=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		panel.setLeftComponent(splitPanelH);
		panel.setRightComponent(outputPanel);
		panel.setDividerLocation(910);
		this.add(panel);
	}

	public static void main(String[] args) {
		new MyFrame("Remove Noise");

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}
}