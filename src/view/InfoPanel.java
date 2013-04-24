package view;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InfoPanel extends JPanel {
	JLabel tipo= new JLabel("tipo: ");
	JLabel costo= new JLabel("costo attraversamento: ");
	JLabel bonus= new JLabel("bonus territorio: ");
	JLabel attacco= new JLabel("attacco: ");
	JLabel difesa= new JLabel("difesa: ");
	JLabel esperienza= new JLabel("esperienza: ");
	JLabel passi= new JLabel("passi: ");
	JLabel numUnits= new JLabel("numero unità: ");
	JPanel sx,dx;
	
	public InfoPanel(){
		super();
		
		sx = new JPanel();
		dx = new JPanel();
		
		sx.setLayout(new GridLayout(3,1));
		dx.setLayout(new GridLayout(3,2));
		
		this.add(sx);
		this.add(dx);
		
		sx.add(tipo);
		sx.add(costo);
		sx.add(bonus);
		
		dx.add(attacco);
		dx.add(difesa);
		dx.add(esperienza);
		dx.add(passi);
		dx.add(numUnits);

	}
	
	public void setTipo(String s){
		this.tipo.setText(this.getName()+s);
	}
}
