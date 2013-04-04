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
		centro = new EsagonoGrafico(this.xC, this.yC, raggio);
		centro.paint(g);
		double x,y;
		x=0;
		y=0;
		
		for(int i=1;i<this.mappa.getComponent().length;i++){
			int s = this.mappa.getComponent()[i].getSettore();
			int l = this.mappa.getComponent()[i].getLivello();
			int p = this.mappa.getComponent()[i].getPosizione();
			switch(s){
			case 1: x = 3/2*raggio*p+raggio*p/2;
					y = (2*l-p)*centro.getApotema();
					break;
					
			case 2: x = 3/2*raggio*l+l*raggio/2;
					y = (l-2*p)*centro.getApotema();
					break;	
			
			case 3: x = 3/2*raggio*(l-p)+(l-p)*raggio/2;
					y = -(l+p)*centro.getApotema();
					break;	
					
			case 4: x = -3/2*raggio*p-raggio*p/2;
					y = -(2*l-p)*centro.getApotema();
					break;	
					
			case 5: x = -3/2*raggio*l-l*raggio/2;
					y = -(l-2*p)*centro.getApotema();
					break;
					
			case 6: x = -3/2*raggio*(l-p)-(l-p)*raggio/2;
					y = (l+p)*centro.getApotema();
					break;		
				
			}
			dE = new EsagonoGrafico(x+this.xC,-y+this.yC,raggio); // il segno - indica il cambio di coordinate (asse y invertito)
			dE.paint(g);
		}

	}

}
