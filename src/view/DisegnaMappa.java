package view;

import java.awt.Color;
import java.awt.Graphics;
import model.Mappa;

public class DisegnaMappa extends Drawing {
	private int xC;
	private int yC;
	private Mappa mappa;
	
	public DisegnaMappa(Mappa m, int x, int y){
		this.xC=x;
		this.yC=y;
		this.mappa=m;
	}

	public void paint(Graphics g) {
		g.setColor(Color.black);
		EsagonoGrafico centro, dE;
		double raggio = 100; //raggio degli esagoni
		centro = new EsagonoGrafico(this.mappa.getComponent()[0],this.xC, this.yC, raggio);
		centro.paint(g);
		
		for(int i=1;i<this.mappa.getComponent().length;i++){
			dE = new EsagonoGrafico(this.mappa.getComponent()[i],this.xC,this.yC,raggio); // il segno "-" indica il cambio di coordinate (asse y invertito)
			dE.paint(g);
		}

	}

}
