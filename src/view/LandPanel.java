package view;

import java.awt.GridLayout;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class LandPanel extends JPanel {
	
	ImageIcon iP = new ImageIcon("C:/Users/Federico/Documents/GitHub/JPanzer/src/model/pianura.jpg");
	ImageIcon iC = new ImageIcon("C:/Users/Federico/Documents/GitHub/JPanzer/src/model/pianura.jpg");
	ImageIcon iM = new ImageIcon("C:/Users/Federico/Documents/GitHub/JPanzer/src/model/pianura.jpg");
	ImageIcon iF = new ImageIcon("C:/Users/Federico/Documents/GitHub/JPanzer/src/model/pianura.jpg");
	ImageIcon iL = new ImageIcon("C:/Users/Federico/Documents/GitHub/JPanzer/src/model/pianura.jpg");
	
	JButton pianura = new JButton("Pianura",iP);
	JButton collina = new JButton("Collina",iC);
	JButton montagna = new JButton("Montagna",iM);	
	JButton foresta = new JButton("Foresta",iF);
	JButton lago = new JButton("Lago",iL);
	
	
	public LandPanel(){
		super();
		this.setLayout(new GridLayout(5,1));
		
		this.pianura.setVerticalTextPosition(AbstractButton.TOP);
		this.pianura.setHorizontalTextPosition(AbstractButton.CENTER);
		
		this.collina.setVerticalTextPosition(AbstractButton.TOP);
		this.collina.setHorizontalTextPosition(AbstractButton.CENTER);
		
		this.montagna.setVerticalTextPosition(AbstractButton.TOP);
		this.montagna.setHorizontalTextPosition(AbstractButton.CENTER);
		
		this.foresta.setVerticalTextPosition(AbstractButton.TOP);
		this.foresta.setHorizontalTextPosition(AbstractButton.CENTER);
		
		this.lago.setVerticalTextPosition(AbstractButton.TOP);
		this.lago.setHorizontalTextPosition(AbstractButton.CENTER);
		
		this.add(pianura);
		this.add(collina);
		this.add(montagna);
		this.add(foresta);
		this.add(lago);
		
	}
}
