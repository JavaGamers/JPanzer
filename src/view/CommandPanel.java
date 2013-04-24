package view;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class CommandPanel extends JPanel {
	
	JButton zoom = new JButton("ZOOM");
	JButton move = new JButton("MUOVI");
	JButton attack = new JButton("ATTACCA");
	JButton undo = new JButton("UNDO");
	JButton abandon = new JButton("ABBANDONA");
	JButton save = new JButton("SALVA");
	JButton load = new JButton("CARICA");
	JButton scorpora = new JButton("SCORPORA");
	JButton accorpa = new JButton("ACCORPA");
	JButton passa = new JButton("PASSA");
	
	public CommandPanel(){
		super();
		this.setLayout(new GridLayout(10,1));
		this.add(zoom);
		this.add(move);
		this.add(attack);
		this.add(undo);
		this.add(abandon);
		this.add(save);
		this.add(load);
		this.add(scorpora);
		this.add(accorpa);
		this.add(passa);		
		
	}
	
}
