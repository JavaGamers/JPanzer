package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import model.Esagono;
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
		Graphics2D g2d= (Graphics2D) g;
		DisegnaEsagono centro, dE;
		double raggio = 100; //raggio degli esagoni
		centro = new DisegnaEsagono(this.xC, this.yC, raggio);
		centro.paint(g);
		double x,y,b,r;
		x=0;
		y=0;
		// int k;
		for(int i=1;i<this.mappa.getComponent().length;i++){
			/*
			b = (-(60/this.mappa.getComponent()[i].getLivello())*(i-Esagono.endLiv(this.mappa.getComponent()[i].getLivello()-1)-1)+90)*Math.PI/180;
			k = Esagono.mod(i-Esagono.endLiv(this.mappa.getComponent()[i].getLivello()-1), this.mappa.getComponent()[i].getLivello());
			r = 2*centro.getApotema()*Math.sqrt(Math.pow((this.mappa.getComponent()[i].getLivello()), 2)-k*this.mappa.getComponent()[i].getLivello()+Math.pow(k, 2));
			x = r*Math.cos(b)+this.xC;
			y = r*Math.sin(b)+this.yC;
			*/
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
			dE = new DisegnaEsagono(x+this.xC,y+this.yC,raggio);
			dE.paint(g);
		}

	}

}
