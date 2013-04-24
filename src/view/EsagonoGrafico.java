package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

public class EsagonoGrafico extends Polygon{
	private double xCentro, yCentro; 
	private double apotema, raggio; // raggio= raggio circonferenza circoscritta all'esagono
	
	 /* le ascisse dei vertici dell'esagono sono in senso antiorario a partire da destra (asse x)
		le ordinate dei vertici dell'esagono sono in senso antiorario a partire da destra (asse x) 
	 	xC, yC coordinate dell'origine, ovvero del centro dell'esagono root (numero 0) */
	public EsagonoGrafico(int s, int l, int p, double xC, double yC, double r){
		super();
		super.npoints=6;
		super.xpoints= new int[6];
		super.ypoints= new int[6];
		this.raggio=r;
		this.apotema=Math.sqrt(3)/2*r;
		
		switch(s){
		case 0: this.xCentro=xC;
				this.yCentro=yC;
				break;
		
		case 1: this.xCentro = 3/2*raggio*p+raggio*p/2 +xC;
				this.yCentro = (2*l-p)*this.apotema+yC;
				break;
				
		case 2: this.xCentro = 3/2*raggio*l+l*raggio/2+xC;
				this.yCentro = (l-2*p)*this.apotema+yC;
				break;	
		
		case 3: this.xCentro = 3/2*raggio*(l-p)+(l-p)*raggio/2+xC;
				this.yCentro = -(l+p)*this.apotema+yC;
				break;	
				
		case 4: this.xCentro = -3/2*raggio*p-raggio*p/2+xC;
				this.yCentro = -(2*l-p)*this.apotema+yC;
				break;	
				
		case 5: this.xCentro = -3/2*raggio*l-l*raggio/2+xC;
				this.yCentro = -(l-2*p)*this.apotema+yC;
				break;
				
		case 6: this.xCentro = -3/2*raggio*(l-p)-(l-p)*raggio/2+xC;
				this.yCentro = (l+p)*this.apotema+yC;
				break;		
		}
		
		//riempio arrays coordinate
				super.xpoints[0]=(int)(this.xCentro+this.raggio);
				super.xpoints[1]=(int)(this.xCentro+this.raggio/2);
				super.xpoints[2]=(int)(this.xCentro-this.raggio/2);
				super.xpoints[3]=(int)(this.xCentro-this.raggio);
				super.xpoints[4]=(int)(this.xCentro-this.raggio/2);
				super.xpoints[5]=(int)(this.xCentro+this.raggio/2);
				
				super.ypoints[0]=(int)this.yCentro;
				super.ypoints[1]=(int)(this.yCentro+this.apotema);
				super.ypoints[2]=(int)(this.yCentro+this.apotema);
				super.ypoints[3]=(int)this.yCentro;
				super.ypoints[4]=(int)(this.yCentro-this.apotema);
				super.ypoints[5]=(int)(this.yCentro-this.apotema);
	}
	
	public double getApotema(){
		return this.apotema;
	}

	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.drawPolygon(this);
	}
	
	public double getX(){
		return this.xCentro;
	}
	
	public double getY(){
		return this.yCentro;
	}
	
	// s= settore -	l= livello	-	p= posizione dell'esagono da disegnare
	public void newSet(int s, int l, int p, double xC, double yC, double r){
		super.npoints=6;
		super.xpoints= new int[6];
		super.ypoints= new int[6];
		this.raggio=r;
		this.apotema=Math.sqrt(3)/2*r;
		
		switch(s){
		case 0: this.xCentro=xC;
				this.yCentro=yC;
				break;
		
		case 1: this.xCentro = 3/2*raggio*p+raggio*p/2 +xC;
				this.yCentro = (2*l-p)*this.apotema+yC;
				break;
				
		case 2: this.xCentro = 3/2*raggio*l+l*raggio/2+xC;
				this.yCentro = (l-2*p)*this.apotema+yC;
				break;	
		
		case 3: this.xCentro = 3/2*raggio*(l-p)+(l-p)*raggio/2+xC;
				this.yCentro = -(l+p)*this.apotema+yC;
				break;	
				
		case 4: this.xCentro = -3/2*raggio*p-raggio*p/2+xC;
				this.yCentro = -(2*l-p)*this.apotema+yC;
				break;	
				
		case 5: this.xCentro = -3/2*raggio*l-l*raggio/2+xC;
				this.yCentro = -(l-2*p)*this.apotema+yC;
				break;
				
		case 6: this.xCentro = -3/2*raggio*(l-p)-(l-p)*raggio/2+xC;
				this.yCentro = (l+p)*this.apotema+yC;
				break;		
		}
		
		//riempio arrays coordinate
				super.xpoints[0]=(int)(this.xCentro+this.raggio);
				super.xpoints[1]=(int)(this.xCentro+this.raggio/2);
				super.xpoints[2]=(int)(this.xCentro-this.raggio/2);
				super.xpoints[3]=(int)(this.xCentro-this.raggio);
				super.xpoints[4]=(int)(this.xCentro-this.raggio/2);
				super.xpoints[5]=(int)(this.xCentro+this.raggio/2);
				
				super.ypoints[0]=(int)this.yCentro;
				super.ypoints[1]=(int)(this.yCentro+this.apotema);
				super.ypoints[2]=(int)(this.yCentro+this.apotema);
				super.ypoints[3]=(int)this.yCentro;
				super.ypoints[4]=(int)(this.yCentro-this.apotema);
				super.ypoints[5]=(int)(this.yCentro-this.apotema);
	}
	
	
}
