package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import model.Mappa;
import model.Montagna;
import model.Territorio;

public class DisegnaQualcosaTiPrego extends JFrame {
	
	public DisegnaQualcosaTiPrego(String title){
		super(title);
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		super.setPreferredSize(new Dimension(800,600));
		super.pack();
	}
		
	public static void main(String[] args) {
		Mappa m = new Mappa(3);
		Territorio t = new Montagna();
		for(int i=0;i<m.getComponent().length;i++){
			m.getComponent()[i].setTerritorio(t);
		}
		MappaGrafica dm = new MappaGrafica(m,700,500);
		CommandPanel cP = new CommandPanel();
		InfoPanel iF = new InfoPanel();
		LandPanel lP = new LandPanel();
		DisegnaQualcosaTiPrego d=new DisegnaQualcosaTiPrego("Java meglio");
		Container c =d.getContentPane();
		c.setLayout(new BorderLayout());
		c.add(lP,BorderLayout.EAST);
		c.add(iF,BorderLayout.NORTH);
		c.add(dm,BorderLayout.CENTER);
		d.setVisible(true);
	}

}
