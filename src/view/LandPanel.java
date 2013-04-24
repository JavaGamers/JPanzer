package view;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class LandPanel extends JPanel {
	
	ImageIcon iP = new ImageIcon("C:/Users/Federico/Documents/GitHub/JPanzer/src/model/pianura.jpg");
	ImageIcon iC = new ImageIcon("C:/Users/Federico/Documents/GitHub/JPanzer/src/model/pianura.jpg");
	ImageIcon iM = new ImageIcon("C:/Users/Federico/Documents/GitHub/JPanzer/src/model/pianura.jpg");
	ImageIcon iF = new ImageIcon("C:/Users/Federico/Documents/GitHub/JPanzer/src/model/pianura.jpg");
	ImageIcon iL = new ImageIcon("C:/Users/Federico/Documents/GitHub/JPanzer/src/model/pianura.jpg");
	
	JButton pianura = new JButton(iP);
	JButton collina = new JButton(iC);
	JButton montagna = new JButton(iM);
	JButton foresta = new JButton(iF);
	JButton lago = new JButton(iL);
	
	
	public LandPanel(){
		super();
		this.setLayout(new GridLayout(5,1));
		this.add(pianura);
		this.add(collina);
		this.add(montagna);
		this.add(foresta);
		this.add(lago);
		
	}
}
