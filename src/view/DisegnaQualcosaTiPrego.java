package view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import model.Mappa;
import model.Montagna;
import model.Pianura;
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
		DisegnaQualcosaTiPrego d=new DisegnaQualcosaTiPrego("Java meglio");
		Container c =d.getContentPane();
		c.add(dm);
		d.setVisible(true);
	}

}
